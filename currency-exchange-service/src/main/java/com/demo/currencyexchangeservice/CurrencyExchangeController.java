package com.demo.currencyexchangeservice;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@ApiOperation(value = "Get Exchange rate of 2 currencies")
	@GetMapping("/exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from,@PathVariable String to) {
		
		logger.info("retrieveExchangeValue called with {} to {}", from, to);
		CurrencyExchange currencyExchange= new CurrencyExchange() ;
		try {
			currencyExchange = repository.findByFromAndTo(from, to);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return currencyExchange;
		
	}


}
