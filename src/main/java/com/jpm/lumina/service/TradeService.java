package com.jpm.lumina.service;

import java.util.List;

import com.jpm.lumina.exception.BussinessException;
import com.jpm.lumina.model.Trade;

public interface TradeService {
	public void addTrade(Trade trade ) throws BussinessException;
	public List<Trade> getAllLastFiveMinutesTrades();
}
