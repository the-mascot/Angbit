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

	@Override
	public MemberInfo memberLogin(MemberInfo member) {
		System.out.println("StatusServiceImpl login start...");
		MemberInfo member1 = sd.memberLogin(member);
		return member1;
	}

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
	public List<TradeCoinInfo> yStatus(String id) {
		System.out.println("StatusServiceImpl yStatus start...");
		List<TradeCoinInfo> listY = sd.listY(id); 
		return listY;
	}

	@Override
	public List<TradeCoinInfo> nStatus(String id) {
		System.out.println("StatusServiceImpl nStatus start...");
		List<TradeCoinInfo> listN = sd.listN(id);
		return listN;
	}

	@Override
	public int totpriceStatus(String id) {
		System.out.println("StatusServiceImpl totpriceStatus start...");
		int result = sd.priceTot(id);
		return result;
	}

	@Override
	public List<TradeCoinInfo> comBuyList(String id) {
		System.out.println("StatusServiceImpl comBuyList start...");
		List<TradeCoinInfo> cbuyList = sd.buyComList(id);
		return cbuyList;
	}

	@Override
	public List<TradeCoinInfo> comSellList(String id) {
		System.out.println("StatusServiceImpl comSellList start...");
		List<TradeCoinInfo> csellList = sd.sellComList(id);
		return csellList;
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




}
