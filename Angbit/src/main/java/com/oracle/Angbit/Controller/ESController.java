package com.oracle.Angbit.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.common.Trade;
import com.oracle.Angbit.model.invest.OrderTrade;
import com.oracle.Angbit.model.invest.TradeList;
import com.oracle.Angbit.service.invest.InvestService;


@Controller
public class ESController {
	
	private static final Logger logger = LoggerFactory.getLogger(ESController.class);
	
	@Autowired
	private InvestService ivs;
	
	@GetMapping("/test")
	public String home() {
		
		System.out.println("ESController home Start...");
		
		return "index";
	}
	
	@GetMapping("/")
	public String invest(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("ESController invest Start...");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sessionID");
		
		List<CoinInfo> coinInfoList =  ivs.coinInfoList();
		
		if(id != null) {
			List<TradeList> tradeList = ivs.selectTradeList(id);
			model.addAttribute("tradeList", tradeList);
		}
		model.addAttribute("coinInfoList", coinInfoList);
		
		return "invest/invest";
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
			System.out.println("ESController investApi json ??????->"+json);
			
			PrintWriter out = response.getWriter();
			out.print(json);
		} catch (Exception e) {
			System.out.println("ESController investApi Exception->"+e.getMessage());
			e.printStackTrace();
		}

	}
	
	@ResponseBody
	@GetMapping("invest/tradePriceApi")
	public String tradePriceApi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("ESController tradePriceApi Start...");
		String currCoin = request.getParameter("currCoin");
		System.out.println("ESController tradePriceApi currCoin->"+currCoin);
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		String tickerUrl 	= "https://api.upbit.com/v1/ticker?markets="+currCoin;
		ResponseEntity<String> tickerResponse = restTemplate.exchange(tickerUrl, HttpMethod.GET, entity, String.class);
		String tradePrice = null;
		try {
			String tickerStr = tickerResponse.getBody();
			JSONParser parser = new JSONParser();
			JSONArray jsonArray = (JSONArray) parser.parse(tickerStr);
			JSONObject json = (JSONObject) jsonArray.get(0);
			NumberFormat nf = NumberFormat.getInstance();
			nf.setGroupingUsed(false);
			tradePrice = nf.format(json.get("trade_price"));
			System.out.println("ESController tradePriceApi trade_price->"+tradePrice);
		} catch (Exception e) {
			System.out.println("ESController tradePriceApi Exception->"+e.getMessage());
			e.printStackTrace();
		}
		
		return tradePrice;
	}
	
	
	
	@ResponseBody
	@GetMapping("invest/coinInfoAjax")
	public CoinInfo coinInfoAjax(String coincode) {
		
		System.out.println("ESController coinInfoAjax Start...");
		System.out.println("ESController coinInfoAjax coincode->"+coincode);
		
		CoinInfo coinInfo = ivs.coinInfo(coincode);
		
		return coinInfo;
	}
	
	@ResponseBody
	@PostMapping("invest/selectKRW")
	public int selectKRW(HttpServletRequest request, HttpServletResponse response, String coincode) {
		
		System.out.println("ESController selectKRW Start...");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sessionID");
		int krw = 0;
		
		if(id != null) {
			krw = ivs.selectKRW(id);
		}
		
		return krw;
	}
	
	@ResponseBody
	@PostMapping("invest/buyCoin")
	public String buyCoin(OrderTrade orderTrade, HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("ESController buyCoin Start...");
		HttpSession session = request.getSession();
		System.out.println(orderTrade.getCoincode());
		orderTrade.setId((String) session.getAttribute("sessionID"));
		orderTrade.setTrd_div(0);
		int result = 0;
		String msg = null;
		System.out.println(orderTrade.getTrd_amt());
		
		if(orderTrade.getTrd_method().equals("limits")) {
			try {
				orderTrade.setTrd_stu(0);
				ivs.buyLimitsPrice(orderTrade);
				msg = "???????????? ???????????????";
			} catch (Exception e) {
				System.out.println("ESController buyCoin limits Exception->"+e.getMessage());
				msg = "??????????????? ?????????????????????";
			}
		} else {
			try {
				orderTrade.setTrd_stu(1);
				ivs.buyMarketPrice(orderTrade);
				msg = "???????????? ???????????????";
			} catch (Exception e) {
				System.out.println("ESController buyCoin limits Exception->"+e.getMessage());
				System.out.println("market result->"+result);
				msg = "??????????????? ?????????????????????";
			}
		}
		
		return msg;
	}
	
	@ResponseBody
	@GetMapping("invest/searchCoin")
	public List<CoinInfo> searchCoin(String keyWord) {
		
		System.out.println("ESController searchCoin Start...");
		System.out.println("keyWord"+keyWord);
		List<CoinInfo> coinInfo = ivs.searchCoin(keyWord);
		
		System.out.println("ESController searchCoin end...");
		return coinInfo;
	}
	
	@ResponseBody
	@GetMapping("invest/upCoinListApi")
	public void upCoinListApi(@RequestParam(value = "coinList[]") List<String> coinList, HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("ESController upCoinListApi Start...");
		String coinListStr = "";
		System.out.println("coinList.size->"+coinList.size());
		for(int i = 0; i < coinList.size();  i++) {
			if(i == coinList.size()-1)
				coinListStr += coinList.get(i);
			else
				coinListStr += coinList.get(i)+", ";
		}
		System.out.println("ESController upCoinListApi coinListStr->"+coinListStr);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		String tickerUrl 	= "https://api.upbit.com/v1/ticker?markets="+coinListStr;
		System.out.println(tickerUrl);
		ResponseEntity<String> tickerResponse = restTemplate.exchange(tickerUrl, HttpMethod.GET, entity, String.class);
		try {
			String tickerStr 	= tickerResponse.getBody();
			
			JSONParser parser = new JSONParser();
			JSONArray json = (JSONArray) parser.parse(tickerStr);
			System.out.println("ESController upCoinListApi json ??????->"+json);
			
			PrintWriter out = response.getWriter();
			out.print(json);
		} catch (Exception e) {
			System.out.println("ESController upCoinListApi Exception->"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@GetMapping("invest/tradeList")
	public List<TradeList> tradeList(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("ESController tradeList Start...");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sessionID");
		List<TradeList> tradeList = null;
		if(id != null) {
			tradeList = ivs.selectTradeList(id);
		}
		
		return tradeList;
	}
	
	@ResponseBody
	@PostMapping("invest/cancelOrder")
	public String cancelOrder(int trd_num) {
		
		System.out.println("ESController cancelOrder Start...");
		String msg = null;
		try {
			Trade trade = ivs.selectTrade(trd_num);
			if(trade.getTrd_stu() == 0) {
				ivs.cancelOrder(trade);
				msg = "????????? ?????????????????????.";
			} else if(trade.getTrd_stu() == 1) {
				msg = "????????? ????????? ???????????????.";
			} else {
				msg = "?????? ????????? ???????????????.";
			}
		} catch (Exception e) {
			System.out.println("ESController cancelOrder Exception->"+e.getMessage());
			msg = "?????? ????????? ?????????????????????.";
		}
		
		return msg;
	}
	@Autowired
	private SimpMessagingTemplate template;
	
	@MessageMapping("/good")
	public void sendMessageTo(OrderTrade orderTrade) {
		System.out.println("good ??????");
		orderTrade.setId("dmstn1812@naver.com");
		template.convertAndSend("/topic/dmstn1812@naver.com", "???????????????");
	}
	
	
}
