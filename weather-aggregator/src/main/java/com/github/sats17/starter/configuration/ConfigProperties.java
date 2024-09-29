package com.github.sats17.starter.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class ConfigProperties {

	private Map<String, String> forecast = new HashMap<>();
	private Map<String, String> airQuality = new HashMap<>();

	public Map<String, String> getForecast() {
		return forecast;
	}

	public void setWeather(Map<String, String> forecast) {
		this.forecast = forecast;
	}

	public Map<String, String> getAirQuality() {
		return airQuality;
	}

	public void setAirQuality(Map<String, String> airQuality) {
		this.airQuality = airQuality;
	}

}
