package com.oracle.Angbit.service.invest;

import java.util.List;

import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.invest.OrderTrade;
import com.oracle.Angbit.model.invest.TradeList;

public interface InvestService {

	List<CoinInfo> coinInfoList();

	CoinInfo coinInfo(String coincode);

	int selectKRW(String id);

	int insertTrade(OrderTrade orderTrade);

	int updateKRW(OrderTrade orderTrade);

	void buyMarketPrice(OrderTrade orderTrade);

	void buyLimitsPrice(OrderTrade orderTrade);

	Float getUsableCoin(String id, String currCoin);	

	void checkBuyLimits();
	
	List<CoinInfo> searchCoin(String keyWord);

	int sellLimitsPrice(OrderTrade orderTrade);

	void sellMarketPrice(OrderTrade orderTrade);

	List<TradeList> selectTradeList(String id);

	void cancelOrder(int trd_num);

}
