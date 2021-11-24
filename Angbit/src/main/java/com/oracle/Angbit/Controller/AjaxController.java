package com.oracle.Angbit.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.service.invest.InvestService;

@RestController
public class AjaxController {
	
	@Autowired
	private InvestService ivs;
	
	@GetMapping("invest/investApi")
	public void investApi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("AngController investApi Start...");
		String currCoin = request.getParameter("currCoin");
		System.out.println("AngController investApi currCoin->"+currCoin);
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		String orderbookUrl = "https://api.upbit.com/v1/orderbook?markets="+currCoin;
		String tickerUrl 	= "https://api.upbit.com/v1/ticker?markets="+currCoin;
		String tradeUrl 	= "https://api.upbit.com/v1/trades/ticks?market="+currCoin+"&count=10";
		ResponseEntity<String> orderResponse 	= restTemplate.exchange(orderbookUrl, HttpMethod.GET, entity, String.class);
		ResponseEntity<String> tickerResponse 	= restTemplate.exchange(tickerUrl, HttpMethod.GET, entity, String.class);
		ResponseEntity<String> tradeResponse 	= restTemplate.exchange(tradeUrl, HttpMethod.GET, entity, String.class);
		try {
			String orderbookStr = orderResponse.getBody();
			String tickerStr 	= tickerResponse.getBody();
			String tradeStr		= tradeResponse.getBody();	
			String jsonStr = orderbookStr.substring(0, (orderbookStr.length()-1)) + ',' + tickerStr.substring(1, (tickerStr.length()-1)) + "," + tradeStr + "]";
			
			System.out.println(jsonStr);
			
			JSONParser parser = new JSONParser();
			JSONArray json = (JSONArray) parser.parse(jsonStr);
			System.out.println("json 객체->"+json);
			
			PrintWriter out = response.getWriter();
			out.print(json);
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	
	@GetMapping("invest/coinInfoAjax")
	public CoinInfo coinInfoAjax(String coincode) {
		
	//	String coincode = request.getParameter("coincode");
		System.out.println("code"+coincode);
		
		//String coincode = (String) model.getAttribute("coincode");
		JSONParser paser = new JSONParser();
		CoinInfo coinInfo = ivs.coinInfo(coincode);
		
		return coinInfo;
	}
	
	
}
