package com.ute.ecwebapp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ute.ecwebapp.dto.Principal;
import com.ute.ecwebapp.util.JwtUtil;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	private final String TOKEN_PREFIX = "Bearer";

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}
		final String token = authorizationHeader.substring(TOKEN_PREFIX.length() + 1);
		if (!jwtUtil.validate(token)) {
			filterChain.doFilter(request, response);
			return;
		}
		var jwtTokenDTO = jwtUtil.getJwtDto(token);
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
		grantedAuthorityList.add(new SimpleGrantedAuthority(jwtTokenDTO.getRole()));
		Principal principal = new Principal();
		principal.setToken(token);
		principal.setAccountName(jwtTokenDTO.getSubject());
		principal.setRole(jwtTokenDTO.getRole());

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal,
				null, grantedAuthorityList);

		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);
	}

}
