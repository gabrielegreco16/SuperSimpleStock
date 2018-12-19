package com.jpm.lumina.config;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jpm.lumina.model.Stock;
import com.jpm.lumina.service.StockServiceImpl;
import com.jpm.lumina.util.StockSymbol;
import com.jpm.lumina.util.StockType;

@Configuration
public class StockConfig {

	private HashMap<String, Stock> stocks = new HashMap<>();

	@Bean
	public HashMap<String, Stock> stockMap() {
		putTea();
		putPop();
		putAle();
		putGin();
		putGoe();

		return stocks;
	}

	@Bean
	public StockServiceImpl stockSrv() {
		return new StockServiceImpl();

	}

	private void putGoe() {
		Stock stock = new Stock();
		stock.setSymbol(StockSymbol.JOE);
		stock.setType(StockType.COMMON);
		stock.setLastDividend(13);
		stock.setParValue(250);
		stocks.put(StockSymbol.JOE, stock);
	}

	private void putGin() {
		Stock stock = new Stock();
		stock.setSymbol(StockSymbol.GIN);
		stock.setType(StockType.PREFERRED);
		stock.setLastDividend(8);
		stock.setFixedDividend(2);
		stock.setParValue(100);
		stocks.put(StockSymbol.GIN, stock);
	}

	private void putAle() {
		Stock stock = new Stock();
		stock.setSymbol(StockSymbol.ALE);
		stock.setType(StockType.COMMON);
		stock.setLastDividend(23);
		stock.setParValue(60);
		stocks.put(StockSymbol.ALE, stock);
	}

	private void putPop() {
		Stock stock = new Stock();
		stock.setSymbol(StockSymbol.POP);
		stock.setType(StockType.COMMON);
		stock.setLastDividend(8);
		stock.setParValue(100);
		stocks.put(StockSymbol.POP, stock);
	}

	private void putTea() {
		Stock stock = new Stock();
		stock.setSymbol(StockSymbol.TEA);
		stock.setType(StockType.COMMON);
		stock.setLastDividend(0);
		stock.setParValue(100);
		stocks.put(StockSymbol.TEA, stock);
	}
}
