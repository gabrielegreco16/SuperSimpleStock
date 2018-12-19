package com.jpm.lumina.service;

import com.jpm.lumina.exception.BussinessException;
import com.jpm.lumina.model.Stock;

public interface StockService {

	public double calculateDividend(String symbol, Double price) throws BussinessException;
	public Stock getStock(String symbol) throws BussinessException;
	public Double calculatePeRatio(String symbol, Double price) throws BussinessException;

}
