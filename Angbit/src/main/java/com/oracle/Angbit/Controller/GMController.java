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

import com.oracle.Angbit.model.common.Coin;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.common.Trade;
import com.oracle.Angbit.service.status.StatusService;

@Controller
public class GMController {
	private static final Logger logger = LoggerFactory.getLogger(GMController.class);

		@Autowired
		private StatusService ss;

//		Status Controller
		@GetMapping("/statusList")
		public String statusList(Coin coin, MemberInfo member, Model model) {
			System.out.println("AngController statusList start...");
			List<MemberInfo> member1 = ss.krwStatus(member);
			List<Coin> statusList1 = ss.listStatus(coin);
			int result = ss.totpriceStatus();
			System.out.println(coin);
			model.addAttribute("statusList", statusList1);
			model.addAttribute("krwList", member1);
			model.addAttribute("totPrice", result);
			return "/status/status";
		}
		
		@GetMapping("/status_y_history")
		public String statusYHistory(Trade trade, Model model) {
			System.out.println("AngController StatusYHistory Start...");
			List<Trade> tradeList = ss.yStatus(trade);
			model.addAttribute("yList", tradeList);
			return "/status/y_history";

		}
		
		@GetMapping("/status_n_history")
		public String statusNHistory(Trade trade, Model model) {
			System.out.println("AngController StatusNHistory Start...");
			List<Trade> tradeList = ss.nStatus(trade);
			model.addAttribute("nList", tradeList);
			return "/status/n_history";
		}
		
		@GetMapping("/status_delete")
		public String statusDelete(int trd_num, Model model) {
			return "redirect:status_n_history";
		}
		
		@ResponseBody
		@GetMapping("/status_tickerApi")
		public void tickerApi(HttpServletRequest request, HttpServletResponse response) throws IOException {
			
			System.out.println("GMController tickerApi Start...");
			String currCoin = request.getParameter("currCoin");
			System.out.println("GMController investApi currCoin->"+currCoin);
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", "application/json");
			HttpEntity<?> entity = new HttpEntity<>(headers);
			
			String tickerUrl 	= "https://api.upbit.com/v1/ticker?markets="+currCoin;
			ResponseEntity<String> tickerResponse 	= restTemplate.exchange(tickerUrl, HttpMethod.GET, entity, String.class);
			try {
				String tickerStr 	= tickerResponse.getBody();
				String jsonStr = tickerStr.substring(1, (tickerStr.length()-1));
				
				System.out.println(jsonStr);
				
				JSONParser parser = new JSONParser();
				JSONArray json = (JSONArray) parser.parse(jsonStr);
				System.out.println("json 객체->"+json);
				
				PrintWriter out = response.getWriter();
				out.print(json);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@GetMapping("test")
		public String test() {
			
			System.out.println("AngController home Start...");
			
			return "/status/tickerApi";
		}
		
		// 로그인 폼
		@GetMapping("/loginForm")
		public String loginForm() {
			System.out.println("LoginForm Start...");
			return "/common/loginForm";
		}
		// 로그인
		@GetMapping("/login")
		public String login(MemberInfo memberInfo, HttpServletRequest request, Model model) {
			System.out.println("AngController login start...");
			HttpSession session = request.getSession();
			MemberInfo member = ss.memberLogin(memberInfo);
			
			if(member == null) {
				session.setAttribute("member", null);
				model.addAttribute("member", member);
			} else {
				session.setAttribute("member", member);
			}
			return "redirect:";
		}
	}
