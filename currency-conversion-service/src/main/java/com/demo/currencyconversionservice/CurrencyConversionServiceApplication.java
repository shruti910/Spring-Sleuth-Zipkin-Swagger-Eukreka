package com.demo.currencyconversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
public class CurrencyConversionServiceApplication {

	private static final String API_MIME_TYPE = "application/json";
	 private static final String API_BASE_URL = "http://localhost:8000";


	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}

	
	 	@Bean
	    public WebClient initWebClient(WebClient.Builder webClientBuilder) {
	        return webClientBuilder
	                .baseUrl(API_BASE_URL)
	                .defaultHeader(HttpHeaders.CONTENT_TYPE, API_MIME_TYPE)
	                .build();
	    }
}
