package com.jpm.lumina.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jpm.lumina.model.Trade;
import com.jpm.lumina.service.StockServiceImpl;
import com.jpm.lumina.service.TradeServiceImpl;

@Configuration
public class TradeConfig {

	@Bean
	public List<Trade> tradeList(){
		return new ArrayList<Trade>();
		}
	
	
	@Bean
	public TradeServiceImpl tradeSrv() {
		return new TradeServiceImpl();

	}
}
