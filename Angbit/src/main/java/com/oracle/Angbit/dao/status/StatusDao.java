package com.oracle.Angbit.dao.status;

import java.util.List;

import com.oracle.Angbit.model.common.Coin;
import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.common.Trade;
import com.oracle.Angbit.model.status.CoinCoinInfo;
import com.oracle.Angbit.model.status.TradeCoinInfo;

public interface StatusDao {
	List<CoinCoinInfo> listStatus(String id);
	List<MemberInfo> listKrw(String id);
	List<TradeCoinInfo> listY(TradeCoinInfo trdCoin, String id);
	List<TradeCoinInfo> listN(String id);
	int priceTot(String id);
	List<TradeCoinInfo> buyComList(TradeCoinInfo trdCoin, String id);
	List<TradeCoinInfo> sellComList(TradeCoinInfo trdCoin, String id);
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
	int buyTotal(String id);
	int buyTotal7(String id);
	int buyTotal30(String id);
	int buyTotal90(String id);
	int buyTotal180(String id);
	int sellTotal(String id);
	int sellTotal7(String id);
	int sellTotal30(String id);
	int sellTotal90(String id);
	int sellTotal180(String id);

}
