package com.github.sats17.starter.service;

import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sats17.starter.configuration.DownstreamEndpoint;
import com.github.sats17.starter.model.response.Error;
import com.github.sats17.starter.model.response.FinalResponse;
import com.github.sats17.starter.utility.ApiResponseUtility;

@Service
public class WeatherService {

	@Autowired
	DownstreamEndpoint weatherHttpConfig;

	private final ObjectMapper objectMapper = new ObjectMapper();
	
	public ResponseEntity<FinalResponse> getForecastByLatLon(String lat, String lon) {
		Map<String, String> properties = weatherHttpConfig.getConfigProperties();
		String baseUrl = properties.get("protocol") + "://" + properties.get("host") + ":" + properties.get("port")
				+ properties.get("forecastApiPath");
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("lat", lat);
		queryParams.put("lon", lon);
		try {
			HttpResponse<String> resp = weatherHttpConfig.get(baseUrl, null, queryParams,
					Duration.ofMillis(Long.parseLong(properties.get("timeOutInMillis"))));
			JsonNode jsonResp = objectMapper.readTree(resp.body());
			return ApiResponseUtility.successResponseCreator(jsonResp.get("response"), "OK");
		} catch (NumberFormatException e) {
			List<Error> errors = new ArrayList<>();
			Error error = new Error();
			error.setHttpMethod("GET");
			error.setHttpRequestURI(properties.get("forecastApiPath"));
			error.setMessage(e.getMessage());
			error.setResultCode(5000);
			error.setResultType("Internal Server Error");
			errors.add(error);
			return ApiResponseUtility.serverErrorResponseCreator(errors, "Server error happend");
		} catch (Exception e) {
			List<Error> errors = new ArrayList<>();
			Error error = new Error();
			error.setHttpMethod("GET");
			error.setHttpRequestURI(properties.get("forecastApiPath"));
			error.setMessage(e.getMessage());
			error.setResultCode(5000);
			error.setResultType("Internal Server Error");
			errors.add(error);
			return ApiResponseUtility.serverErrorResponseCreator(errors, "Server error happend");
		}
	}

}
