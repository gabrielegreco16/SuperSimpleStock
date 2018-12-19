package com.jpm.lumina.service;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.sql.rowset.spi.TransactionalWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jpm.lumina.exception.BussinessException;
import com.jpm.lumina.model.Stock;
import com.jpm.lumina.util.StockType;
import com.jpm.lumina.util.ValidatorUtil;

@Component
@Service
public class StockServiceImpl implements StockService{

	private static final Logger logger =  Logger.getLogger(StockServiceImpl.class.getName());
	@Autowired
	private HashMap<String, Stock> stockMap;
	private ValidatorUtil validator = new ValidatorUtil();
	@Autowired
	public StockServiceImpl () {
		
	}
	
	
	
	public double calculateDividend(String symbol, Double price) throws BussinessException {
		validator.validatePrice(price);
		Stock stock = getStock(symbol);
		double dividend = 0;
			switch (stock.getType()) {
			case StockType.COMMON:
				dividend = stock.getLastDividend()/price;
				break;
			case StockType.PREFERRED:
				dividend = (stock.getFixedDividend()*stock.getParValue())/price;
				break;
			}
		return dividend;
		
	}

	public Stock getStock(String symbol) throws BussinessException {
		Stock stock=stockMap.get(symbol);
		if(stock!=null) {
			logger.info("Stock"+symbol+"founded");
			return stock;
		}
		else {
			logger.severe("Error: Symbol:"+symbol+"not founded");
			throw new BussinessException("Error: Symbol:"+symbol+"not founded");
		}
	}

	

	public ValidatorUtil getValidator() {
		return validator;
	}

	public void setValidator(ValidatorUtil validator) {
		this.validator = validator;
	}

	public HashMap<String, Stock> getStockMap() {
		return stockMap;
	}

	public void setStockMap(HashMap<String, Stock> stockMap) {
		this.stockMap = stockMap;
	}

	public Double calculatePeRatio(String symbol, Double price) throws BussinessException  {
		Stock stock=null;
		try {
			stock = getStock(symbol);
			validator.validatePrice(price);

		} catch (BussinessException e) {
			logger.severe(e.getMessage());
			throw e;
		}
		return price/stock.getLastDividend();
	}

}
