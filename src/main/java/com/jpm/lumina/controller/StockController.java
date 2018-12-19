package com.jpm.lumina.controller;

import java.util.HashMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jpm.lumina.exception.BussinessException;
import com.jpm.lumina.model.Stock;
import com.jpm.lumina.service.StockServiceImpl;

@Controller
public class StockController {

	@Autowired
	private HashMap<String, Stock> stockMap;
	@Autowired
	private StockServiceImpl stockSrv;
	private static final Logger logger =  Logger.getLogger(StockController.class.getName());

	
	@GetMapping("/dividend")
	@ResponseBody
	public String calculateDividendYield(@RequestParam(name = "symbol", required = true) String symbol,
			@RequestParam(name = "price", required = true) Double price) throws BussinessException {
		logger.info("Arrive to /getDividend method:GET");
		Double dividend;
		dividend = stockSrv.calculateDividend(symbol, price);
		logger.info("For Price"+ price +" Dividend is: "+dividend);
		return "For Price "+ price +" and Symbol"+symbol+" Dividend is: "+dividend;
	}
	
	@GetMapping("/per")
	@ResponseBody
	public String calculatePeRatio(@RequestParam(name = "symbol", required = true) String symbol,
			@RequestParam(name = "price", required = true) Double price) throws BussinessException {
		Double peRatio;
		logger.info("Arrive to /per method:GET");
		peRatio = stockSrv.calculatePeRatio(symbol,price);
		logger.info("For Price "+ price +" and Symbol"+symbol+" Pe ratio is: "+ peRatio);
		return "For Price "+ price +" and Symbol"+symbol+" Pe ratio is: "+ peRatio;

	}

	public HashMap<String, Stock> getStockMap() {
		return stockMap;
	}

	public void setStockMap(HashMap<String, Stock> stockMap) {
		this.stockMap = stockMap;
	}

	

	public StockServiceImpl getStockSrv() {
		return stockSrv;
	}

	public void setStockSrv(StockServiceImpl stockSrv) {
		this.stockSrv = stockSrv;
	}
}
