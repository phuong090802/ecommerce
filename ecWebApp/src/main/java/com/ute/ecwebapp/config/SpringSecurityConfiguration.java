package com.ute.ecwebapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ute.ecwebapp.filter.JwtTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration {

	private static final String[] WHITE_LIST_URL = { "/api/login", "/api/user", "/api/forget", "/api/user/forget",
			"/api/image/source/*"};

	@Autowired
	private JwtTokenFilter jwtTokenFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity = httpSecurity.cors().and().csrf().disable();
		httpSecurity = httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
		httpSecurity.authorizeRequests().antMatchers(WHITE_LIST_URL).permitAll().anyRequest().authenticated();
		httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
}
