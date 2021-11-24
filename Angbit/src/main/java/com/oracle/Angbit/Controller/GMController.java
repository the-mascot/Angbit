package com.oracle.Angbit.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
			System.out.println(coin);
			model.addAttribute("statusList", statusList1);
			model.addAttribute("krwList", member1);
			return "/status/status";
		}
		
		@GetMapping("/status/y_history")
		public String statusYHistory(Trade trade, Model model) {
			System.out.println("AngController StatusYHistory Start...");
			List<Trade> tradeList = ss.yStatus(trade);
			model.addAttribute("yList", tradeList);
			return "/status/y_history";

		}
		
		@GetMapping("/status/n_history")
		public String statusNHistory(Trade trade, Model model) {
			System.out.println("AngController StatusNHistory Start...");
			List<Trade> tradeList = ss.nStatus(trade);
			model.addAttribute("nList", tradeList);
			return "/status/n_history";
		}
		
		@GetMapping("/status/delete")
		public String statusDelete(int trd_num, Model model) {
			return "redirect:statusList";
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
