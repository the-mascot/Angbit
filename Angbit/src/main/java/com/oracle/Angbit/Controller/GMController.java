package com.oracle.Angbit.Controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.status.CoinCoinInfo;
import com.oracle.Angbit.model.status.TradeCoinInfo;
import com.oracle.Angbit.service.status.StatusPaging;
import com.oracle.Angbit.service.status.StatusService;

@Controller
public class GMController {
	private static final Logger logger = LoggerFactory.getLogger(GMController.class);

		@Autowired
		private StatusService ss;

//		투자현황
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
		
//		chart.js ajax
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
				System.out.println("ajax ->" + json);
				if(json == null)
					System.out.println("비어있습니다.");
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return json;
		}
		
//		체결내역 - 전체 - 전체
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
			System.out.println("trd.currentPage -> "+ currentPage);
			
			List<TradeCoinInfo> tradeList = ss.yStatus(trdCoin, id);
			model.addAttribute("yList", tradeList);
			System.out.println("y_history tradeList.size() -> "+tradeList.size());
			model.addAttribute("pg", pg);
			System.out.println("currentPage -> "+pg.getCurrentPage());
			System.out.println("startPage -> " +pg.getStartPage());
			System.out.println("endPage -> " +pg.getEndPage());
			System.out.println("pageBlock -> " +pg.getPageBlock());
			System.out.println("totalPage -> "+pg.getTotalPage());
			return "/status/y_history";
		}
		
//		체결내역 - 전체 - 7일
		@GetMapping("/status_y_history_sort7d")
		public String allDateSort7(TradeCoinInfo trdCoin, String currentPage, HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_dateSort Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history_dateSort id -> "+id);
			
			int total = ss.total7(id);
			System.out.println("total7.size() -> "+total);
			
			StatusPaging pg = new StatusPaging(total, currentPage);
			trdCoin.setStart(pg.getStart());
			trdCoin.setEnd(pg.getEnd());
			
			List<TradeCoinInfo> All7List = ss.allDateSort7(trdCoin, id);
			model.addAttribute("All7List", All7List);
			model.addAttribute("pg", pg);
			
			return "/status/y_history_sort7d"; 
		}
		
//		체결내역 - 전체 - 30일
		@GetMapping("/status_y_history_sort30d")
		public String allDateSort30(TradeCoinInfo trdCoin, String currentPage, HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_dateSort Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history_dateSort id -> "+id);
			
			int total = ss.total30(id);
			System.out.println("total30.size() ->"+ total);
			
			StatusPaging pg = new StatusPaging(total, currentPage);
			trdCoin.setStart(pg.getStart());
			trdCoin.setEnd(pg.getEnd());
			
			List<TradeCoinInfo> All30List = ss.allDateSort30(trdCoin, id);
			model.addAttribute("All30List", All30List);
			model.addAttribute("pg", pg);
			
			return "/status/y_history_sort30d"; 
		}
		
//		체결내역 - 전체 - 90일
		@GetMapping("/status_y_history_sort90d")
		public String allDateSort90(TradeCoinInfo trdCoin, String currentPage, HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_dateSort Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history_dateSort id -> "+id);
			
			int total = ss.total90(id);
			System.out.println("total90.size() -> "+total);
			
			StatusPaging pg = new StatusPaging(total, currentPage);
			trdCoin.setStart(pg.getStart());
			trdCoin.setEnd(pg.getEnd());
						
			List<TradeCoinInfo> All90List = ss.allDateSort90(trdCoin, id);
			model.addAttribute("All90List", All90List);
			model.addAttribute("pg", pg);
			
			return "/status/y_history_sort90d"; 
		}
//		체결내역 - 전체 - 180일
		@GetMapping("/status_y_history_sort180d")
		public String allDateSort180(TradeCoinInfo trdCoin, String currentPage, HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_dateSort Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history_dateSort id -> "+id);
			
			int total = ss.total180(id);
			
			StatusPaging pg = new StatusPaging(total, currentPage);
			trdCoin.setStart(pg.getStart());
			trdCoin.setEnd(pg.getEnd());
			
			List<TradeCoinInfo> All180List = ss.allDateSort180(trdCoin, id);
			model.addAttribute("All180List", All180List);
			model.addAttribute("pg", pg);
			
			return "/status/y_history_sort180d"; 
		}
		
//		미체결내역 - 전체
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
		
//		체결내역 - 매수 - 전체
		@GetMapping("/status_y_history_buy")
		public String statusBuycom(TradeCoinInfo trdCoin, String currentPage, HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_buy Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history_buy id -> "+id);
			
			int total = ss.buytotal(id);
			
			StatusPaging pg = new StatusPaging(total, currentPage);
			trdCoin.setStart(pg.getStart());
			trdCoin.setEnd(pg.getEnd());
			
			List<TradeCoinInfo> comBuyList = ss.comBuyList(trdCoin, id);
			model.addAttribute("cbList", comBuyList);
			model.addAttribute("pg", pg);
			return "/status/y_history_buy";
		}
		
//		체결내역 - 매수 - 7일
		@GetMapping("/status_y_history_buy_sort7d")
		public String statusBuycomSort7d(TradeCoinInfo trdCoin, String currentPage, HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_buy_sort7 Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history_buy id -> "+id);
			
			int total = ss.buytotal7(id);
			
			StatusPaging pg = new StatusPaging(total, currentPage);
			trdCoin.setStart(pg.getStart());
			trdCoin.setEnd(pg.getEnd());
			
			List<TradeCoinInfo> Buy7List = ss.buyDateSort7(trdCoin, id);
			model.addAttribute("Buy7List", Buy7List);
			model.addAttribute("pg", pg);
			return "/status/y_history_buy_sort7d";
		}
		
//		체결내역 - 매수 - 30일
		@GetMapping("/status_y_history_buy_sort30d")
		public String statusBuycomSort30d(TradeCoinInfo trdCoin, String currentPage, HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_buy_sort30 Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history_buy id -> "+id);
			
			int total = ss.buytotal30(id);
			
			StatusPaging pg = new StatusPaging(total, currentPage);
			trdCoin.setStart(pg.getStart());
			trdCoin.setEnd(pg.getEnd());
			
			List<TradeCoinInfo> Buy30List = ss.buyDateSort30(trdCoin, id);
			model.addAttribute("Buy30List", Buy30List);
			model.addAttribute("pg", pg);
			return "/status/y_history_buy_sort30d";
		}
		
//		체결내역 - 매수 - 90일
		@GetMapping("/status_y_history_buy_sort90d")
		public String statusBuycomSort90d(TradeCoinInfo trdCoin, String currentPage, HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_buy_sort90 Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history_buy id -> "+id);
			
			int total = ss.buytotal90(id);
			
			StatusPaging pg = new StatusPaging(total, currentPage);
			trdCoin.setStart(pg.getStart());
			trdCoin.setEnd(pg.getEnd());
			
			List<TradeCoinInfo> Buy90List = ss.buyDateSort90(trdCoin, id);
			model.addAttribute("Buy90List", Buy90List);
			model.addAttribute("pg", pg);
			return "/status/y_history_buy_sort90d";
		}
		
//		체결내역 - 매수 - 180일
		@GetMapping("/status_y_history_buy_sort180d")
		public String statusBuycomSort180d(TradeCoinInfo trdCoin, String currentPage, HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_buy_sort180 Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_y_history_buy id -> "+id);
			
			int total = ss.buytotal180(id);
			
			StatusPaging pg = new StatusPaging(total, currentPage);
			trdCoin.setStart(pg.getStart());
			trdCoin.setEnd(pg.getEnd());
			
			List<TradeCoinInfo> Buy180List = ss.buyDateSort180(trdCoin, id);
			model.addAttribute("Buy180List", Buy180List);
			model.addAttribute("pg", pg);
			return "/status/y_history_buy_sort180d";
		}
		
//		 체결내역 - 매도 - 전체
		@GetMapping("/status_y_history_sell")
		public String statusSellcom(TradeCoinInfo trdCoin, String currentPage, HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_sell Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_n_history_buy id -> "+id);
			
			int total = ss.sellTotal(id);
			
			StatusPaging pg = new StatusPaging(total, currentPage);
			trdCoin.setStart(pg.getStart());
			trdCoin.setEnd(pg.getEnd());
			
			List<TradeCoinInfo> comSellList = ss.comSellList(trdCoin, id);
			model.addAttribute("csList", comSellList);
			model.addAttribute("pg", pg);
			return "/status/y_history_sell";
		}
		
//		체결내역 - 매도 - 7일
		@GetMapping("/status_y_history_sell_sort7d")
		public String statusSellcomsort7d(TradeCoinInfo trdCoin, String currentPage, HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_sell_sort7 Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_n_history_buy id -> "+id);
			
			int total = ss.sellTotal7(id);
			
			StatusPaging pg = new StatusPaging(total, currentPage);
			trdCoin.setStart(pg.getStart());
			trdCoin.setEnd(pg.getEnd());
			
			List<TradeCoinInfo> Sell7List = ss.sellDateSort7(trdCoin, id);
			model.addAttribute("Sell7List", Sell7List);
			model.addAttribute("pg", pg);
			return "/status/y_history_sell_sort7d";
		}
		
//		체결내역 - 매도 - 30일
		@GetMapping("/status_y_history_sell_sort30d")
		public String statusSellcomsort30d(TradeCoinInfo trdCoin, String currentPage, HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_sell_sort7 Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_n_history_buy id -> "+id);
			
			int total = ss.sellTotal30(id);	
			
			StatusPaging pg = new StatusPaging(total, currentPage);
			trdCoin.setStart(pg.getStart());
			trdCoin.setEnd(pg.getEnd());
			
			List<TradeCoinInfo> Sell30List = ss.sellDateSort30(trdCoin, id);
			model.addAttribute("Sell30List", Sell30List);
			model.addAttribute("pg", pg);
			return "/status/y_history_sell_sort30d";
		}
		
//		체결내역 - 매도 - 90일
		@GetMapping("/status_y_history_sell_sort90d")
		public String statusSellcomsort90d(TradeCoinInfo trdCoin, String currentPage, HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_sell_sort7 Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_n_history_buy id -> "+id);
			
			int total = ss.sellTotal90(id);	
			
			StatusPaging pg = new StatusPaging(total, currentPage);
			trdCoin.setStart(pg.getStart());
			trdCoin.setEnd(pg.getEnd());
			
			List<TradeCoinInfo> Sell90List = ss.sellDateSort90(trdCoin, id);
			model.addAttribute("Sell90List", Sell90List);
			model.addAttribute("pg", pg);
			return "/status/y_history_sell_sort90d";
		}
		
//		체결내역 - 매도 - 180일
		@GetMapping("/status_y_history_sell_sort180d")
		public String statusSellcomsort180d(TradeCoinInfo trdCoin, String currentPage, HttpServletRequest request, Model model) {
			System.out.println("GMController status_y_history_sell_sort7 Start...");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("status_n_history_buy id -> "+id);
			
			int total = ss.sellTotal180(id);	
			
			StatusPaging pg = new StatusPaging(total, currentPage);
			trdCoin.setStart(pg.getStart());
			trdCoin.setEnd(pg.getEnd());
			
			List<TradeCoinInfo> Sell180List= ss.sellDateSort180(trdCoin, id);
			model.addAttribute("Sell180List", Sell180List);
			model.addAttribute("pg", pg);
			return "/status/y_history_sell_sort180d";
		}
		
//		체결내역 - 검색 - 전체
		@GetMapping("/y_history_search")
		public String historySearch(TradeCoinInfo trdCoin, String currentPage, HttpServletRequest request, Model model) {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			String search = request.getParameter("search");
			System.out.println("id -> "+id);
			System.out.println("search -> "+search);
			
			int total = ss.searchTotal(id, search);
			System.out.println("total.size() ->"+total);
			
			StatusPaging pg = new StatusPaging(total, currentPage);
			trdCoin.setStart(pg.getStart());
			trdCoin.setEnd(pg.getEnd());
			System.out.println("search.start -> "+pg.getStart());
			System.out.println("search.end -> "+pg.getEnd());
			
			
			List<TradeCoinInfo> searchList = ss.searchList(trdCoin, search, id);
			System.out.println("searchList.size() ->"+searchList.size());
			System.out.println("search ->"+search);
			
			model.addAttribute("searchList", searchList);
			model.addAttribute("pg", pg);
			model.addAttribute("search", search);
			return "/status/y_history_search";
		}
		
//		체결내역 - 검색 - 매수
		@GetMapping("/y_history_search_buy")
		public String buySearch(TradeCoinInfo trdCoin, String currentPage, HttpServletRequest request, Model model) {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			String search = request.getParameter("search");
			System.out.println("id -> "+id);
			System.out.println("search -> "+search);
			
			int total = ss.searchBuy(id, search);
			System.out.println("total.size() ->"+total);
			
			StatusPaging pg = new StatusPaging(total, currentPage);
			trdCoin.setStart(pg.getStart());
			trdCoin.setEnd(pg.getEnd());
			System.out.println("search.start -> "+pg.getStart());
			System.out.println("search.end -> "+pg.getEnd());
			
			
			List<TradeCoinInfo> searchBuy = ss.searchBuyList(trdCoin, search, id);
			System.out.println("searchList.size() ->"+searchBuy.size());
			System.out.println("search ->"+search);
			
			model.addAttribute("searchBuy", searchBuy);
			model.addAttribute("pg", pg);
			model.addAttribute("search", search);
			return "/status/y_history_search_buy";
		}
		
//		체결내역 - 검색 - 매도
		@GetMapping("/y_history_search_sell")
		public String sellSearch(TradeCoinInfo trdCoin, String currentPage, HttpServletRequest request, Model model) {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			String search = request.getParameter("search");
			System.out.println("id -> "+id);
			System.out.println("search -> "+search);
			
			int total = ss.searchSell(id, search);
			System.out.println("total.size() ->"+total);
			
			StatusPaging pg = new StatusPaging(total, currentPage);
			trdCoin.setStart(pg.getStart());
			trdCoin.setEnd(pg.getEnd());
			System.out.println("search.start -> "+pg.getStart());
			System.out.println("search.end -> "+pg.getEnd());
			
			
			List<TradeCoinInfo> searchSell = ss.searchSellList(trdCoin, search, id);
			System.out.println("searchList.size() ->"+searchSell.size());
			System.out.println("search ->"+search);
			
			model.addAttribute("searchSell", searchSell);
			model.addAttribute("pg", pg);
			model.addAttribute("search", search);
			return "/status/y_history_search_sell";
		}
		
//		미체결내역 - 대기내역
		@GetMapping("/status_n_history_wait")
		public String nhistoryWait(HttpServletRequest request, Model model) {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			System.out.println("id-> "+id);
			
			List<TradeCoinInfo> nWaitList = ss.nWaitList(id);
			model.addAttribute("nWaitList", nWaitList);
			
			return "/status/n_history_wait";
		}
		
//		미체결내역 - 취소내역
		@GetMapping("/status_n_history_cancle")
		public String nhistoryCancle(HttpServletRequest request, Model model) {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("sessionID");
			
			List<TradeCoinInfo> nCancleList = ss.nCancleList(id);
			model.addAttribute("nCancleList", nCancleList);
			
			return "/status/n_history_cancle";
		}
		
//		승훈이 전수 - 테스트용 ajax
//		@GetMapping("testmethod")
//		@ResponseBody
//		public List<TradeCoinInfo> testmethod(HttpServletRequest request, HttpServletResponse response, Model model) {
//			System.out.println("AJAX status_y_history_dateSort Start...");
//			String days = request.getParameter("days");
//			System.out.println("받아온 days"+days);
//			
//			HttpSession session = request.getSession();
//			String id = (String) session.getAttribute("sessionID");
//			System.out.println("status_y_history_dateSort id -> "+id);
//			switch (days) {
//				case 7 : 
//			}
//			List<TradeCoinInfo> All7List = ss.allDateSort7(id); \
//			
//			model.addAttribute("pg", paging(id));
//			return "/status/y_history_sort7d"; 
//			return All7List;
//		}
		
//		@GetMapping("goTest")
//		public String goTest() {
//			return "/status/y_history_ajax";
//		}
		
//		public StatusPaging paging(String id) {
//			System.out.println("GMController StatusYHistory Start...");
//			System.out.println("status_y_history id -> "+id);
//			
//			int total = ss.total(id);
//			System.out.println("total.size -> "+total);
//			
//			StatusPaging pg = new StatusPaging(total, currentPage);
//			trdCoin.setStart(pg.getStart());
//			trdCoin.setEnd(pg.getEnd());
//			System.out.println("trd.getStart() ->"+trdCoin.getStart());
//			System.out.println("trd.getEnd() ->"+trdCoin.getEnd());
//			System.out.println("trd.currentPage -> "+ currentPage);
//			
//			List<TradeCoinInfo> tradeList = ss.yStatus(trdCoin, id);
//			System.out.println("startPage -> " +pg.getStartPage());
//			System.out.println("endPage -> " +pg.getEndPage());
//			System.out.println("pageBlock -> " +pg.getPageBlock());
//			System.out.println("totalPage -> "+pg.getTotalPage());
//			return pg;
//		}
	}
