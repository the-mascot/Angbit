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
	public List<TradeCoinInfo> yStatus(String id) {
		System.out.println("StatusServiceImpl yStatus start...");
		List<TradeCoinInfo> listY = sd.listY(id); 
		return listY;
	}
	
	@Override
	public List<TradeCoinInfo> allDateSort7(String id) {
		System.out.println("StatusServiceImpl allDateSort7 start...");
		List<TradeCoinInfo> allDateSort7 = sd.allDateSort7(id);
		return allDateSort7;
	}

	@Override
	public List<TradeCoinInfo> allDateSort30(String id) {
		System.out.println("StatusServiceImpl allDateSort30 start...");
		List<TradeCoinInfo> allDateSort30 = sd.allDateSort30(id);
		return allDateSort30;
	}

	@Override
	public List<TradeCoinInfo> allDateSort90(String id) {
		System.out.println("StatusServiceImpl allDateSort90 start...");
		List<TradeCoinInfo> allDateSort90 = sd.allDateSort90(id);
		return allDateSort90;
	}

	@Override
	public List<TradeCoinInfo> allDateSort180(String id) {
		System.out.println("StatusServiceImpl allDateSort180 start...");
		List<TradeCoinInfo> allDateSort180 = sd.allDateSort180(id);
		return allDateSort180;
	}

//	체결내역(매수)
	@Override
	public List<TradeCoinInfo> comBuyList(String id) {
		System.out.println("StatusServiceImpl comBuyList start...");
		List<TradeCoinInfo> cbuyList = sd.buyComList(id);
		return cbuyList;
	}

	@Override
	public List<TradeCoinInfo> buyDateSort7(String id) {
		System.out.println("StatusServiceImpl buyDateSort7 start...");
		List<TradeCoinInfo> buyDateSort7 = sd.buyDateSort7(id);
		return buyDateSort7;
	}

	@Override
	public List<TradeCoinInfo> buyDateSort30(String id) {
		System.out.println("StatusServiceImpl buyDateSort30 start...");
		List<TradeCoinInfo> buyDateSort30 = sd.buyDateSort30(id);
		return buyDateSort30;
	}

	@Override
	public List<TradeCoinInfo> buyDateSort90(String id) {
		System.out.println("StatusServiceImpl buyDateSort90 start...");
		List<TradeCoinInfo> buyDateSort90 = sd.buyDateSort90(id);
		return buyDateSort90;
	}

	@Override
	public List<TradeCoinInfo> buyDateSort180(String id) {
		System.out.println("StatusServiceImpl buyDateSort180 start...");
		List<TradeCoinInfo> buyDateSort180 = sd.buyDateSort180(id);
		return buyDateSort180;
	}

//	체결내역(매도)
	@Override
	public List<TradeCoinInfo> comSellList(String id) {
		System.out.println("StatusServiceImpl comSellList start...");
		List<TradeCoinInfo> csellList = sd.sellComList(id);
		return csellList;
	}
	
	@Override
	public List<TradeCoinInfo> sellDateSort7(String id) {
		System.out.println("StatusServiceImpl sellDataSort7 start...");
		List<TradeCoinInfo> sellDateSort7 = sd.sellDateSort7(id);
		return sellDateSort7;
	}

	@Override
	public List<TradeCoinInfo> sellDateSort30(String id) {
		System.out.println("StatusServiceImpl sellDataSort30 start...");
		List<TradeCoinInfo> sellDateSort30 = sd.sellDateSort30(id);
		return sellDateSort30;
	}

	@Override
	public List<TradeCoinInfo> sellDateSort90(String id) {
		System.out.println("StatusServiceImpl sellDataSort90 start...");
		List<TradeCoinInfo> sellDateSort90 = sd.sellDateSort90(id);
		return sellDateSort90;
	}

	@Override
	public List<TradeCoinInfo> sellDateSort180(String id) {
		System.out.println("StatusServiceImpl sellDataSort180 start...");
		List<TradeCoinInfo> sellDateSort180 = sd.sellDateSort180(id);
		return sellDateSort180;
	}





//	미체결내역
	@Override
	public List<TradeCoinInfo> nStatus(String id) {
		System.out.println("StatusServiceImpl nStatus start...");
		List<TradeCoinInfo> listN = sd.listN(id);
		return listN;
	}

	@Override
	public int total(String id) {
		int totCnt = sd.total(id);
		return totCnt;
	}



}
