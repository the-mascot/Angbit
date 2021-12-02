package com.oracle.Angbit.Controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.status.CoinCoinInfo;
import com.oracle.Angbit.model.status.StatusPaging;
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
		
		@ResponseBody
		@RequestMapping("/statusListAjax")
		public String chart(HttpServletRequest request) {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("statusList chart id->"+id);
			
			List<CoinCoinInfo> list = ss.listStatus(id);
			request.setAttribute("list", list);
			HashMap map = new HashMap();
			map.put("list", list);
			String json = null;
			try {
				json = new ObjectMapper().writeValueAsString(map);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return json;
		}
		
		
		@GetMapping("/status_y_history")
		public String statusYHistory(TradeCoinInfo trdCoin, HttpServletRequest request, String currentPage, Model model) {
			System.out.println("GMController StatusYHistory Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history id -> "+id);
			
			int total = ss.total(id);
			System.out.println("total.size -> "+total);
			
			StatusPaging pg = new StatusPaging(total, currentPage);
			trdCoin.setStart(pg.getStart());
			trdCoin.setEnd(pg.getEnd());
			System.out.println("trd.getStart() ->"+trdCoin.getStart());
			System.out.println("trd.getEnd() ->"+trdCoin.getEnd());
			
			List<TradeCoinInfo> tradeList = ss.yStatus(trdCoin, id);
			model.addAttribute("yList", tradeList);
			model.addAttribute("pg", pg);
			return "/status/y_history";
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
		
		// 매수
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
		
		@GetMapping("/status_y_history_buy_sort7d")
		public String statusBuycomSort7d(HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_buy_sort7 Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history_buy id -> "+id);
			
			List<TradeCoinInfo> Buy7List = ss.buyDateSort7(id);
			model.addAttribute("Buy7List", Buy7List);
			return "/status/y_history_buy_sort7d";
		}
		
		@GetMapping("/status_y_history_buy_sort30d")
		public String statusBuycomSort30d(HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_buy_sort30 Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history_buy id -> "+id);
			
			List<TradeCoinInfo> Buy30List = ss.buyDateSort30(id);
			model.addAttribute("Buy30List", Buy30List);
			return "/status/y_history_buy_sort30d";
		}
		
		@GetMapping("/status_y_history_buy_sort90d")
		public String statusBuycomSort90d(HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_buy_sort90 Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history_buy id -> "+id);
			
			List<TradeCoinInfo> Buy90List = ss.buyDateSort90(id);
			model.addAttribute("Buy90List", Buy90List);
			return "/status/y_history_buy_sort90d";
		}
		
		@GetMapping("/status_y_history_buy_sort180d")
		public String statusBuycomSort180d(HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_buy_sort180 Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history_buy id -> "+id);
			
			List<TradeCoinInfo> Buy180List = ss.buyDateSort180(id);
			model.addAttribute("Buy180List", Buy180List);
			return "/status/y_history_buy_sort180d";
		}
		
		// 매도
		@GetMapping("/status_y_history_sell")
		public String statusSellcom(HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_sell Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_n_history_buy id -> "+id);
			
			List<TradeCoinInfo> comSellList = ss.comSellList(id);
			model.addAttribute("csList", comSellList);
			return "/status/y_history_sell";
		}
		
		@GetMapping("/status_y_history_sell_sort7d")
		public String statusSellcomsort7d(HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_sell_sort7 Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_n_history_buy id -> "+id);
			
			List<TradeCoinInfo> Sell7List = ss.sellDateSort7(id);
			model.addAttribute("Sell7List", Sell7List);
			return "/status/y_history_sell_sort7d";
		}
		
		@GetMapping("/status_y_history_sell_sort30d")
		public String statusSellcomsort30d(HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_sell_sort7 Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_n_history_buy id -> "+id);
			
			List<TradeCoinInfo> Sell30List = ss.sellDateSort30(id);
			model.addAttribute("Sell30List", Sell30List);
			return "/status/y_history_sell_sort30d";
		}
		
		@GetMapping("/status_y_history_sell_sort90d")
		public String statusSellcomsort90d(HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_sell_sort7 Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_n_history_buy id -> "+id);
			
			List<TradeCoinInfo> Sell90List = ss.sellDateSort90(id);
			model.addAttribute("Sell90List", Sell90List);
			return "/status/y_history_sell_sort90d";
		}
		
		@GetMapping("/status_y_history_sell_sort180d")
		public String statusSellcomsort180d(HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_sell_sort7 Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_n_history_buy id -> "+id);
			
			List<TradeCoinInfo> Sell180List= ss.sellDateSort180(id);
			model.addAttribute("Sell180List", Sell180List);
			return "/status/y_history_sell_sort180d";
		}
	}
