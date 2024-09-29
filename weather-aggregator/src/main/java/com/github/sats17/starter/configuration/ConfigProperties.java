package com.github.sats17.starter.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class ConfigProperties {

	private Map<String, String> weather = new HashMap<>();

	public Map<String, String> getWeather() {
		return weather;
	}

	public void setWeather(Map<String, String> weather) {
		this.weather = weather;
	}

}
