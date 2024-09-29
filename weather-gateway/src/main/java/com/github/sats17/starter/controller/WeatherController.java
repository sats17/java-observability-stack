package com.github.sats17.starter.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.sats17.starter.model.response.Error;
import com.github.sats17.starter.model.response.FinalResponse;
import com.github.sats17.starter.service.WeatherService;
import com.github.sats17.starter.utility.ApiResponseUtility;

@RestController
@RequestMapping("/v1/api")
public class WeatherController {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

	@Autowired
	WeatherService weatherService;

	@GetMapping("/weather")
	public ResponseEntity<FinalResponse> getForecast(@RequestParam("lat") String lat, @RequestParam("lon") String lon) {
		ResponseEntity<FinalResponse> resp = weatherService.getForecastByLatLon(lat, lon);
		return resp;
	}

	/*
	 * Shows how you can create success response of any list
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/error")
	public ResponseEntity<FinalResponse> testError() {
		List<Error> errors = new ArrayList<>();
		Error error = new Error();
		error.setResultCode(40001);
		error.setResultType("HeaderValidatoinException");
		error.setHttpRequestURI("/api/error");
		error.setMessage("Please enter correct API key");
		error.setHttpMethod("GET");
		errors.add(error);
		return ApiResponseUtility.validationErrorResponseCreator(errors, "message");

	}

}
