package com.oracle.Angbit.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.service.invest.InvestService;


@Controller
public class ESController {
	
	private static final Logger logger = LoggerFactory.getLogger(ESController.class);
	
	@Autowired
	private InvestService ivs;
	
	@GetMapping("/")
	public String home() {
		
		System.out.println("AngController home Start...");
		
		return "/index";
	}
	
	@GetMapping("invest")
	public String invest(Model model) {
		
		System.out.println("AngController invest Start...");
		List<CoinInfo> coinInfoList =  ivs.coinInfoList();
		model.addAttribute("coinInfoList", coinInfoList);
		return "/invest/invest";
	}


	
	
	
}
