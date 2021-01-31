package com.demo.currencyconversionservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import io.swagger.annotations.ApiOperation;

@RestController
public class CurrencyConversionController {
		
	@Autowired
	private WebClient webClient;
	
	Logger log=LoggerFactory.getLogger(this.getClass());
	
	
	@ApiOperation(value = "Convert currencies from one to another")
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		log.info("Calling currency_exchange service with from : {} and to: {}", from,to);
				
		CurrencyConversion currencyConversion = webClient.get()
				.uri(uriBuilder->uriBuilder.path("/exchange/from/")
						.path(from).path("/to/").path(to).build())
				.retrieve()
				.bodyToMono(CurrencyConversion.class)
				.block();
		
		log.info("Response::{}", currencyConversion.toString());
		
		return new CurrencyConversion(currencyConversion.getId(), 
				from, to, quantity, 
				currencyConversion.getConversionMultiple(), 
				quantity.multiply(currencyConversion.getConversionMultiple()));
		
	}
	
}
