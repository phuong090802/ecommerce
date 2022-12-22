package com.ute.ecwebapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = "classpath:messages/email.properties", encoding = "UTF-8")
public class EmailConfiguration {

	@Autowired
	Environment environment;

	@Bean
	public String getMessageBody() {
		return environment.getProperty("messageBody");
	}

	@Bean
	public String getEmailName() {
		return environment.getProperty("emailName");
	}

	@Bean
	public String getSubject() {
		return environment.getProperty("subject");
	}
}
