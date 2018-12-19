package com.jpm.lumina.util;

import java.util.logging.Logger;

import com.jpm.lumina.exception.BussinessException;
import com.jpm.lumina.model.Trade;
import com.jpm.lumina.service.TradeServiceImpl;

public class ValidatorUtil {
	
	private static final Logger logger =  Logger.getLogger(ValidatorUtil.class.getName());

	
	public void validatePrice(Double price) throws BussinessException {
		if (price <= 0) {
			throw new BussinessException("Error price can not be minus or equal to 0");
		}
	}

	public void validateTrade(Trade trade) throws BussinessException {
		validatePrice(trade.getPrice());
		validateQuantity(trade.getQuantity());
		validateOperation(trade.getOperationType());
	}

	private void validateOperation(String operationType) throws BussinessException {
		if(!(operationType.equals("sell")||operationType.equals("buy"))) {
			throw new BussinessException("Error: type "+operationType+" unrecognized");
		}
	}

	private void validateQuantity(int quantity) throws BussinessException {
		if (quantity <= 0) {
			throw new BussinessException("Error quantity can not be minus or equal to 0");
		}
	}

}
