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
	List<TradeCoinInfo> yStatus(TradeCoinInfo trdCoin, String id);
	List<TradeCoinInfo> nStatus(String id);
	int totpriceStatus(String id);
	List<TradeCoinInfo> comBuyList(TradeCoinInfo trdCoin, String id);
	List<TradeCoinInfo> comSellList(TradeCoinInfo trdCoin, String id);
	List<TradeCoinInfo> allDateSort7(TradeCoinInfo trdCoin, String id);
	List<TradeCoinInfo> allDateSort30(TradeCoinInfo trdCoin, String id);
	List<TradeCoinInfo> allDateSort90(TradeCoinInfo trdCoin, String id);
	List<TradeCoinInfo> allDateSort180(TradeCoinInfo trdCoin, String id);
	List<TradeCoinInfo> buyDateSort7(TradeCoinInfo trdCoin, String id);
	List<TradeCoinInfo> buyDateSort30(TradeCoinInfo trdCoin, String id);
	List<TradeCoinInfo> buyDateSort90(TradeCoinInfo trdCoin, String id);
	List<TradeCoinInfo> buyDateSort180(TradeCoinInfo trdCoin, String id);
	List<TradeCoinInfo> sellDateSort7(TradeCoinInfo trdCoin, String id);
	List<TradeCoinInfo> sellDateSort30(TradeCoinInfo trdCoin, String id);
	List<TradeCoinInfo> sellDateSort90(TradeCoinInfo trdCoin, String id);
	List<TradeCoinInfo> sellDateSort180(TradeCoinInfo trdCoin, String id);
	int total(String id);
	int total7(String id);
	int total30(String id);
	int total90(String id);
	int total180(String id);
	int buytotal(String id);
	int buytotal7(String id);
	int buytotal30(String id);
	int buytotal90(String id);
	int buytotal180(String id);
	int sellTotal(String id);
	int sellTotal7(String id);
	int sellTotal30(String id);
	int sellTotal90(String id);
	int sellTotal180(String id);

}
