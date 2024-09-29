package com.github.sats17.starter.service;

import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.sats17.starter.configuration.DownstreamEndpoint;
import com.github.sats17.starter.configuration.LoggingInterceptor;
import com.github.sats17.starter.model.response.Error;
import com.github.sats17.starter.model.response.FinalResponse;
import com.github.sats17.starter.utility.ApiResponseUtility;

@Service
public class WeatherService {

	@Autowired
	DownstreamEndpoint forecastHttpConfig;
	
	@Autowired
	DownstreamEndpoint airQualityHttpConfig;
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
	
	private final ObjectMapper objectMapper = new ObjectMapper();

	public ResponseEntity<FinalResponse> getForecastByLatLon(String lat, String lon) {
		Map<String, String> forecastProperties = forecastHttpConfig.getConfigProperties();
		Map<String, String> airQualityProperties = airQualityHttpConfig.getConfigProperties();
		
		
		try {
			
			logger.info("Fetching forecast data for lat: {} and lon: {} ", lat,lon);
			HttpResponse<String> forecastResp = fetchForecastData(lat, lon, forecastProperties);
			logger.info("Forecast data feched for lat: {} and lon: {} ", lat,lon);
			logger.info("Fetching air quality data for lat: {} and lon: {} ", lat,lon);
			HttpResponse<String> airQualityResp = fetchForecastData(lat, lon, forecastProperties);
			logger.info("Air quality data feched for lat: {} and lon: {} ", lat,lon);
			
			JsonNode forecastJson = objectMapper.readTree(forecastResp.body());
	        JsonNode airQualityJson = objectMapper.readTree(airQualityResp.body());
	        
	        ObjectNode customResponse = objectMapper.createObjectNode();
	        customResponse.set("forecast", forecastJson);
	        customResponse.set("airQuality", airQualityJson);

			return ApiResponseUtility.successResponseCreator(customResponse, "OK");
		} catch (NumberFormatException e) {
			List<Error> errors = new ArrayList<>();
			Error error = new Error();
			error.setHttpMethod("GET");
			error.setMessage(e.getMessage());
			error.setResultCode(5000);
			error.setResultType("Internal Server Error");
			errors.add(error);
			return ApiResponseUtility.serverErrorResponseCreator(errors, "Server error happend");
		} catch (Exception e) {
			List<Error> errors = new ArrayList<>();
			Error error = new Error();
			error.setHttpMethod("GET");
			error.setMessage(e.getMessage());
			error.setResultCode(5000);
			error.setResultType("Internal Server Error");
			errors.add(error);
			return ApiResponseUtility.serverErrorResponseCreator(errors, "Server error happend");
		}
	}

	private HttpResponse<String> fetchForecastData(String lat, String lon, Map<String, String> properties)
			throws Exception {
		String baseUrl = properties.get("protocol") + "://" + properties.get("host") + ":" + properties.get("port")
				+ properties.get("forecastApiPath");
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("latitude", lat);
		queryParams.put("longitude", lon);
		HttpResponse<String> resp = forecastHttpConfig.get(baseUrl, null, queryParams,
				Duration.ofMillis(Long.parseLong(properties.get("timeOutInMillis"))));
		return resp;
	}

	private HttpResponse<String> fetchAirQualityData(String lat, String lon, Map<String, String> properties)
			throws Exception {
		String baseUrl = properties.get("protocol") + "://" + properties.get("host") + ":" + properties.get("port")
				+ properties.get("airQualityApiPath");
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("latitude", lat);
		queryParams.put("longitude", lon);
		HttpResponse<String> resp = forecastHttpConfig.get(baseUrl, null, queryParams,
				Duration.ofMillis(Long.parseLong(properties.get("timeOutInMillis"))));
		return resp;
	}

	
}
