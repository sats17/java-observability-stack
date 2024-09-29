package com.github.sats17.starter.configuration;

import java.net.http.HttpClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfig {

	@Autowired
	ConfigProperties configProperties;

	@Bean
	DownstreamEndpoint weatherHttpConfig() {
		return new DownstreamEndpoint(configProperties.getWeather(), HttpClient.newHttpClient());
	}

}