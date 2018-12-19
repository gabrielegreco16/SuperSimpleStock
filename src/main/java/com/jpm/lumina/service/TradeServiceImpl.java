package com.jpm.lumina.service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jpm.lumina.exception.BussinessException;
import com.jpm.lumina.model.Trade;
import com.jpm.lumina.util.ValidatorUtil;

public class TradeServiceImpl implements TradeService {

	@Autowired
	private List<Trade> tradeList;
	private ValidatorUtil validator = new ValidatorUtil();
	private static final Logger logger =  Logger.getLogger(TradeServiceImpl.class.getName());
	@Override
	public void addTrade(Trade trade) throws BussinessException {
		try {
			validator.validateTrade(trade);
			tradeList.add(trade);
		} catch (Exception e) {
			logger.severe(e.getMessage());
			throw e;
		}
		
	}

	public List<Trade> getTradeList() {
		return tradeList;
	}

	public void setTradeList(List<Trade> tradeList) {
		this.tradeList = tradeList;
	}

	public ValidatorUtil getValidator() {
		return validator;
	}

	public void setValidator(ValidatorUtil validator) {
		this.validator = validator;
	}

	public Double getVws() {
		List<Trade> list = getAllLastFiveMinutesTrades();
		if(list.size()==0) {
			return 0D;
		}
		double sum1 = 0;
		double sum2 = 0;
		for (Trade trade : list) {
			sum1 += trade.getPrice() * trade.getQuantity();

			sum2 += trade.getQuantity();
		}
		return sum1 / sum2;
	}

	public List<Trade> getAllLastFiveMinutesTrades() {

		Date fiveMinutesAgo = new Date(new Date().getTime() - 300000);
		return tradeList.stream().filter(o -> o.getTimeStamp().after(fiveMinutesAgo)).collect(Collectors.toList());

	}

	public double geoMean() {
		
		List<Trade> list = getAllLastFiveMinutesTrades();

		if (list.size() == 0) {
			return 0.0;
		}

		double gm = 1.0;
		for (Trade trade : list) {
			gm*=trade.getPrice();
		}
		gm = Math.pow(gm, 1.0 / (double) list.size());

		return gm;
	}

}
