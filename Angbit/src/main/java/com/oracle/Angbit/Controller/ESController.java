package com.oracle.Angbit.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.invest.MemberCoin;
import com.oracle.Angbit.service.invest.InvestService;


@Controller
public class ESController {
	
	private static final Logger logger = LoggerFactory.getLogger(ESController.class);
	
	@Autowired
	private InvestService ivs;
	
	@GetMapping("/")
	public String home() {
		
		System.out.println("ESController home Start...");
		
		return "/index";
	}
	
	@GetMapping("invest")
	public String invest(Model model) {
		
		System.out.println("ESController invest Start...");
		List<CoinInfo> coinInfoList =  ivs.coinInfoList();
		model.addAttribute("coinInfoList", coinInfoList);
		return "/invest/invest";
	}
	
	@ResponseBody
	@GetMapping("invest/investApi")
	public void investApi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("ESController investApi Start...");
		String currCoin = request.getParameter("currCoin");
		System.out.println("ESController investApi currCoin->"+currCoin);
		
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
			
			JSONParser parser = new JSONParser();
			JSONArray json = (JSONArray) parser.parse(jsonStr);
			System.out.println("ESController investApi json 객체->"+json);
			
			PrintWriter out = response.getWriter();
			out.print(json);
		} catch (Exception e) {
			System.out.println("ESController investApi Exception->"+e.getMessage());
			e.printStackTrace();
		}

	}
	
	@ResponseBody
	@GetMapping("invest/coinInfoAjax")
	public CoinInfo coinInfoAjax(String coincode) {
		
		System.out.println("ESController coinInfoAjax Start...");
		System.out.println("ESController coinInfoAjax coincode->"+coincode);
		
		JSONParser paser = new JSONParser();
		CoinInfo coinInfo = ivs.coinInfo(coincode);
		
		return coinInfo;
	}
	
	@ResponseBody
	@GetMapping("invest/orderInfo")
	public MemberCoin orderInfo(HttpServletRequest request, HttpServletResponse response, String coincode) {
		
		System.out.println("ESController orderInfo Start...");
		HttpSession session = request.getSession();
		
		session.setAttribute("sessionID", "dmstn1812@naver.com");
		
		MemberCoin paraMemberCoin = new MemberCoin();
		paraMemberCoin.setId((String) session.getAttribute("sessionID"));
		paraMemberCoin.setCoincode(coincode);
		
		MemberCoin memberCoin = ivs.memberCoin(paraMemberCoin);
		
		return memberCoin;
	}
	
	
}
