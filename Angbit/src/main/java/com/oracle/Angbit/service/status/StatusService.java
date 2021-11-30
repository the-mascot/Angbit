package com.oracle.Angbit.service.status;

import java.util.List;

import com.oracle.Angbit.model.common.Coin;
import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.common.Trade;
import com.oracle.Angbit.model.status.CoinCoinInfo;
import com.oracle.Angbit.model.status.TradeCoinInfo;

public interface StatusService {
	List<CoinCoinInfo> listStatus(String id);
	List<MemberInfo> krwStatus(String id);
	List<TradeCoinInfo> yStatus(String id);
	List<TradeCoinInfo> nStatus(String id);
	int totpriceStatus(String id);
	List<TradeCoinInfo> comBuyList(String id);
	List<TradeCoinInfo> comSellList(String id);
	List<TradeCoinInfo> allDateSort7(String id);
	List<TradeCoinInfo> allDateSort30(String id);
	List<TradeCoinInfo> allDateSort90(String id);
	List<TradeCoinInfo> allDateSort180(String id);
	List<TradeCoinInfo> buyDateSort7(String id);
	List<TradeCoinInfo> buyDateSort30(String id);
	List<TradeCoinInfo> buyDateSort90(String id);
	List<TradeCoinInfo> buyDateSort180(String id);
	List<TradeCoinInfo> sellDateSort7(String id);
	List<TradeCoinInfo> sellDateSort30(String id);
	List<TradeCoinInfo> sellDateSort90(String id);
	List<TradeCoinInfo> sellDateSort180(String id);
	int total(String id);

}
