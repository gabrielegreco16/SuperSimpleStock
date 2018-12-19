package com.jpm.lumina.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jpm.lumina.exception.BussinessException;
import com.jpm.lumina.model.Trade;
import com.jpm.lumina.service.TradeService;
import com.jpm.lumina.service.TradeServiceImpl;
import com.jpm.lumina.util.ValidatorUtil;

@Controller
public class TradeController {

	@Autowired
	private List<Trade> tradeList;
	@Autowired 
	private TradeServiceImpl tradeSrv;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static final Logger logger =  Logger.getLogger(TradeController.class.getName());

	
	@PostMapping("/trade")
	@ResponseBody
	public Trade addTrade(@RequestBody Trade trade) throws BussinessException, ParseException {
		logger.info("Arrive to /trade method:POST");
		trade.setTimeStamp(new Date());
		tradeSrv.addTrade(trade);
		logger.info("Trade was added successfully");
		return trade;
	}

	@GetMapping("/trade")
	@ResponseBody
	public List<Trade> getAllTrades() {
		logger.info("Arrive to /trade method:GET to show all Trades");
		return tradeList;

	}
	
	@GetMapping("/vws")
	@ResponseBody
	public double getVws() throws ParseException {
		logger.info("Arrive to /vws method:GET");
		Double vws=tradeSrv.getVws();
		logger.info("Vws of the last five minutes is: "+vws);
		return vws;

	}
	
	@GetMapping("/geomean")
	@ResponseBody
	public double geoMean() throws ParseException {
		logger.info("Arrive to /vws method:GET");
		Double geoMean=tradeSrv.geoMean();
		logger.info("Geometric Mean is: "+geoMean);
		return geoMean;

	}

	public List<Trade> getTradeList() {
		return tradeList;
	}

	public void setTradeList(List<Trade> tradeList) {
		this.tradeList = tradeList;
	}

	public TradeServiceImpl getTradeSrv() {
		return tradeSrv;
	}

	public void setTradeSrv(TradeServiceImpl tradeSrv) {
		this.tradeSrv = tradeSrv;
	}

	

}
