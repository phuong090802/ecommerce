package com.ute.ecwebapp.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationUtil {
	@Value("${real.email.apikey}")
	private String emailApiKey;

	@Value("${numlookupapi.phone.apikey}")
	private String phoneApiKey;

	public boolean validationEmail(String email) throws IOException, InterruptedException {

		var client = HttpClient.newBuilder().build();
		String jsonResult = client.send(
				HttpRequest.newBuilder().GET()
						.uri(URI.create("https://isitarealemail.com/api/email/validate?email=" + email))
						.setHeader("Authorization", "Bearer " + emailApiKey).build(),
				HttpResponse.BodyHandlers.ofString()).body();
		String status = new JSONObject(jsonResult).getString("status");
		System.out.println(status);
		if (status.equals("valid")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validationPhone(String phone) throws IOException, InterruptedException {

		HttpClient client = HttpClient.newBuilder().build();
		String jsonResult = client.send(
				HttpRequest.newBuilder().GET()
						.uri(URI.create("https://api.numlookupapi.com/v1/validate/" + phone + "?apikey=" + phoneApiKey))
						.setHeader("Authorization", "Bearer " + phoneApiKey).build(),
				HttpResponse.BodyHandlers.ofString()).body();
		Boolean valid = new JSONObject(jsonResult).getBoolean("valid");
		if (valid) {
			return true;
		} else {
			return false;
		}
	}
}
