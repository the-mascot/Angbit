package com.oracle.Angbit.service.invest;

import java.util.List;

import com.oracle.Angbit.model.common.Coin;
import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.invest.OrderTrade;

public interface InvestService {

	List<CoinInfo> coinInfoList();

	CoinInfo coinInfo(String coincode);

	int selectKRW(String id);

	int insertTrade(OrderTrade orderTrade);

	int updateKRW(OrderTrade orderTrade);

	void buyMarketPrice(OrderTrade orderTrade);

	void buyLimitsPrice(OrderTrade orderTrade);

	Float getMyCoin(String id, String currCoin);
	
	List<CoinInfo> searchCoin(String keyWord);
	
	void checkBuyLimits();
	
}
