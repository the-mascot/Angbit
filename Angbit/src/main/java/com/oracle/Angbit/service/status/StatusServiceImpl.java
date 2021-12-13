package com.oracle.Angbit.service.status;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.Angbit.dao.status.StatusDao;
import com.oracle.Angbit.model.common.Coin;
import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.common.Trade;
import com.oracle.Angbit.model.status.CoinCoinInfo;
import com.oracle.Angbit.model.status.TradeCoinInfo;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusDao sd;
//	투자현황
	@Override
	public List<CoinCoinInfo> listStatus(String id) {
		System.out.println("StatusServiceImpl listStatus start...");
		List<CoinCoinInfo> listStatus = sd.listStatus(id);
		return listStatus;
	}

	@Override
	public List<MemberInfo> krwStatus(String id) {
		System.out.println("StatuasServiceImpl krwStatus start...");
		List<MemberInfo> listKrw = sd.listKrw(id);
		return listKrw;
	}

	@Override
	public int totpriceStatus(String id) {
		System.out.println("StatusServiceImpl totpriceStatus start...");
		int result = sd.priceTot(id);
		return result;
	}
//	체결내역(전체)
	@Override
	public List<TradeCoinInfo> yStatus(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusServiceImpl yStatus start...");
		List<TradeCoinInfo> listY = sd.listY(trdCoin, id); 
		System.out.println("StatusServiceImpl yStatus listY.size() -> "+listY.size());
		return listY;
	}
	
	@Override
	public List<TradeCoinInfo> allDateSort7(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusServiceImpl allDateSort7 start...");
		List<TradeCoinInfo> allDateSort7 = sd.allDateSort7(trdCoin, id);
		return allDateSort7;
	}

	@Override
	public List<TradeCoinInfo> allDateSort30(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusServiceImpl allDateSort30 start...");
		List<TradeCoinInfo> allDateSort30 = sd.allDateSort30(trdCoin, id);
		return allDateSort30;
	}

	@Override
	public List<TradeCoinInfo> allDateSort90(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusServiceImpl allDateSort90 start...");
		List<TradeCoinInfo> allDateSort90 = sd.allDateSort90(trdCoin, id);
		return allDateSort90;
	}

	@Override
	public List<TradeCoinInfo> allDateSort180(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusServiceImpl allDateSort180 start...");
		List<TradeCoinInfo> allDateSort180 = sd.allDateSort180(trdCoin, id);
		return allDateSort180;
	}

//	체결내역(매수)
	@Override
	public List<TradeCoinInfo> comBuyList(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusServiceImpl comBuyList start...");
		List<TradeCoinInfo> cbuyList = sd.buyComList(trdCoin, id);
		return cbuyList;
	}

	@Override
	public List<TradeCoinInfo> buyDateSort7(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusServiceImpl buyDateSort7 start...");
		List<TradeCoinInfo> buyDateSort7 = sd.buyDateSort7(trdCoin, id);
		return buyDateSort7;
	}

	@Override
	public List<TradeCoinInfo> buyDateSort30(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusServiceImpl buyDateSort30 start...");
		List<TradeCoinInfo> buyDateSort30 = sd.buyDateSort30(trdCoin, id);
		return buyDateSort30;
	}

	@Override
	public List<TradeCoinInfo> buyDateSort90(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusServiceImpl buyDateSort90 start...");
		List<TradeCoinInfo> buyDateSort90 = sd.buyDateSort90(trdCoin, id);
		return buyDateSort90;
	}

	@Override
	public List<TradeCoinInfo> buyDateSort180(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusServiceImpl buyDateSort180 start...");
		List<TradeCoinInfo> buyDateSort180 = sd.buyDateSort180(trdCoin, id);
		return buyDateSort180;
	}

//	체결내역(매도)
	@Override
	public List<TradeCoinInfo> comSellList(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusServiceImpl comSellList start...");
		List<TradeCoinInfo> csellList = sd.sellComList(trdCoin, id);
		return csellList;
	}
	
	@Override
	public List<TradeCoinInfo> sellDateSort7(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusServiceImpl sellDataSort7 start...");
		List<TradeCoinInfo> sellDateSort7 = sd.sellDateSort7(trdCoin, id);
		return sellDateSort7;
	}

	@Override
	public List<TradeCoinInfo> sellDateSort30(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusServiceImpl sellDataSort30 start...");
		List<TradeCoinInfo> sellDateSort30 = sd.sellDateSort30(trdCoin, id);
		return sellDateSort30;
	}

	@Override
	public List<TradeCoinInfo> sellDateSort90(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusServiceImpl sellDataSort90 start...");
		List<TradeCoinInfo> sellDateSort90 = sd.sellDateSort90(trdCoin, id);
		return sellDateSort90;
	}

	@Override
	public List<TradeCoinInfo> sellDateSort180(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusServiceImpl sellDataSort180 start...");
		List<TradeCoinInfo> sellDateSort180 = sd.sellDateSort180(trdCoin, id);
		return sellDateSort180;
	}





//	미체결내역
	@Override
	public List<TradeCoinInfo> nStatus(String id) {
		System.out.println("StatusServiceImpl nStatus start...");
		List<TradeCoinInfo> listN = sd.listN(id);
		return listN;
	}

//	페이지네이션 총 글 갯수
	@Override
	public int total(String id) {
		int totCnt = sd.total(id);
		return totCnt;
	}

	@Override
	public int total7(String id) {
		int tot7Cnt = sd.total7(id);
		return tot7Cnt;
	}

	@Override
	public int total30(String id) {
		int tot30Cnt = sd.total30(id);
		return tot30Cnt;
	}

	@Override
	public int total90(String id) {
		int tot90Cnt = sd.total90(id);
		return tot90Cnt;
	}

	@Override
	public int total180(String id) {
		int tot180Cnt = sd.total180(id);
		return tot180Cnt;
	}
//	페이지네이션 - 매수
	@Override
	public int buytotal(String id) {
		int buyTotCnt = sd.buyTotal(id);
		return buyTotCnt;
	}

	@Override
	public int buytotal7(String id) {
		int buyTot7Cnt = sd.buyTotal7(id);
		return buyTot7Cnt;
	}

	@Override
	public int buytotal30(String id) {
		int buyTot30Cnt = sd.buyTotal30(id);
		return buyTot30Cnt;
	}

	@Override
	public int buytotal90(String id) {
		int buyTot90Cnt = sd.buyTotal90(id);
		return buyTot90Cnt;
	}

	@Override
	public int buytotal180(String id) {
		int buyTot180Cnt = sd.buyTotal180(id);
		return buyTot180Cnt;
	}

	@Override
	public int sellTotal(String id) {
		int sellTotCnt = sd.sellTotal(id);
		return sellTotCnt;
	}

	@Override
	public int sellTotal7(String id) {
		int sellTot7Cnt = sd.sellTotal7(id);
		return sellTot7Cnt;
	}

	@Override
	public int sellTotal30(String id) {
		int sellTot30Cnt = sd.sellTotal30(id);
		return sellTot30Cnt;
	}

	@Override
	public int sellTotal90(String id) {
		int sellTot90Cnt = sd.sellTotal90(id);
		return sellTot90Cnt;
	}

	@Override
	public int sellTotal180(String id) {
		int sellTot180Cnt = sd.sellTotal180(id);
		return sellTot180Cnt;
	}

	@Override
	public List<TradeCoinInfo> searchList(TradeCoinInfo trdCoin, String search, String id) {
		List<TradeCoinInfo> searchList = sd.searchList(trdCoin, search, id);
		return searchList;
	}

	@Override
	public int searchTotal(String id,String search) {
		int searchTotal = sd.searchTotal(id, search);
		return searchTotal;
	}

	@Override
	public List<TradeCoinInfo> searchBuyList(TradeCoinInfo trdCoin, String search, String id) {
		List<TradeCoinInfo> searchBuyList = sd.searchBuyList(trdCoin, search, id);
		return searchBuyList;
	}

	@Override
	public int searchBuy(String id, String search) {
		int searchBuy = sd.searchBuy(id, search);
		return searchBuy;
	}

	@Override
	public int searchSell(String id, String search) {
		int searchSell = sd.searchSell(id, search);
		return searchSell;
	}

	@Override
	public List<TradeCoinInfo> searchSellList(TradeCoinInfo trdCoin, String search, String id) {
		List<TradeCoinInfo> searchSellList = sd.searchSellList(trdCoin, search, id);
		return searchSellList;
	}

	@Override
	public List<TradeCoinInfo> nWaitList(String id) {
		List<TradeCoinInfo> nWaitList = sd.nWaitList(id);
		return nWaitList;
	}

	@Override
	public List<TradeCoinInfo> nCancleList(String id) {
		List<TradeCoinInfo> nCancleList = sd.nCancleList(id);
		return nCancleList;
	}



}
