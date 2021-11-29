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
	List<TradeCoinInfo> listY(String id);
	List<TradeCoinInfo> listN(String id);
	int priceTot(String id);
	List<TradeCoinInfo> buyComList(String id);
	List<TradeCoinInfo> sellComList(String id);
	List<TradeCoinInfo> allDateSort7(String id);
	List<TradeCoinInfo> allDateSort30(String id);
	List<TradeCoinInfo> allDateSort90(String id);
	List<TradeCoinInfo> allDateSort180(String id);
	MemberInfo memberLogin(MemberInfo member);

}
