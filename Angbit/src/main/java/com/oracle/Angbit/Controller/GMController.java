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

import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.status.CoinCoinInfo;
import com.oracle.Angbit.model.status.TradeCoinInfo;
import com.oracle.Angbit.service.status.StatusService;

@Controller
public class GMController {
	private static final Logger logger = LoggerFactory.getLogger(GMController.class);

		@Autowired
		private StatusService ss;

//		Status Controller
		@GetMapping("/statusList")
		public String statusList(HttpServletRequest request, Model model) {
			System.out.println("GMController statusList start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("statusList id -> "+id);
			
			List<MemberInfo> member1 = ss.krwStatus(id);
			List<CoinCoinInfo> statusList1 = ss.listStatus(id);
			int result = ss.totpriceStatus(id);

			model.addAttribute("statusList", statusList1);
			model.addAttribute("krwList", member1);
			if(result == 0) {
				model.addAttribute("totPrice", 0);
				return "/status/status";
			} else {
				model.addAttribute("totPrice", result);
				return "/status/status";
			}
		}
		
		@GetMapping("/status_y_history")
		public String statusYHistory(HttpServletRequest request, Model model) {
			System.out.println("GMController StatusYHistory Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history id -> "+id);
			
			List<TradeCoinInfo> tradeList = ss.yStatus(id);
			model.addAttribute("yList", tradeList);
			return "/status/y_history";
		}
		
		@GetMapping("/status_n_history")
		public String statusNHistory(HttpServletRequest request, Model model) {
			System.out.println("GMController StatusNHistory Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_n_history id -> "+id);
			
			List<TradeCoinInfo> tradeList = ss.nStatus(id);
			System.out.println("GMController n_history tradeList.size ->"+tradeList.size());
			model.addAttribute("nList", tradeList);
			return "/status/n_history";
		}
		
		@GetMapping("/status_y_history_buy")
		public String statusBuycom(HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_buy Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history_buy id -> "+id);
			
			List<TradeCoinInfo> comBuyList = ss.comBuyList(id);
			model.addAttribute("cbList", comBuyList);
			return "/status/y_history_buy";
		}
		
		@GetMapping("/status_y_history_sell")
		public String statusSellcom(HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_buy Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_n_history_buy id -> "+id);
			
			List<TradeCoinInfo> comSellList = ss.comSellList(id);
			model.addAttribute("csList", comSellList);
			return "/status/y_history_sell";
		}
		
		@GetMapping("/status_y_history_sort7d")
		public String allDateSort7(HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_dateSort Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history_dateSort id -> "+id);
			
			List<TradeCoinInfo> All7List = ss.allDateSort7(id);
			model.addAttribute("All7List", All7List);
			
			return "/status/y_history_sort7d"; 
		}
		
		@GetMapping("/status_y_history_sort30d")
		public String allDateSort30(HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_dateSort Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history_dateSort id -> "+id);
			
			List<TradeCoinInfo> All30List = ss.allDateSort30(id);
			model.addAttribute("All30List", All30List);
			
			return "/status/y_history_sort30d"; 
		}
		
		@GetMapping("/status_y_history_sort90d")
		public String allDateSort90(HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_dateSort Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history_dateSort id -> "+id);
			
			List<TradeCoinInfo> All90List = ss.allDateSort90(id);
			model.addAttribute("All90List", All90List);
			
			return "/status/y_history_sort90d"; 
		}
		
		@GetMapping("/status_y_history_sort180d")
		public String allDateSort180(HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_dateSort Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history_dateSort id -> "+id);
			
			List<TradeCoinInfo> All180List = ss.allDateSort180(id);
			model.addAttribute("All180List", All180List);
			
			return "/status/y_history_sort180d"; 
		}
		
	}
