package com.oracle.Angbit.dao.invest;

import java.util.List;

import com.oracle.Angbit.model.common.Coin;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.common.Trade;
import com.oracle.Angbit.model.invest.OrderTrade;
import com.oracle.Angbit.model.invest.TradeList;

public interface InvestDao {
	
	List<CoinInfo> coinInfoList();

	CoinInfo coinInfo(String coincode);

	int selectKRW(String id);

	int insertTrade(OrderTrade orderTrade);

	int updateKRW(OrderTrade orderTrade);
	
	void buyMarketPrice(OrderTrade orderTrade);

	void buyLimitsPrice(OrderTrade orderTrade);

	Float getUsableCoin(String id, String currCoin);
	
	List<CoinInfo> searchCoin(String keyWord);

	void checkBuyLimits();
	
	int sellLimitsPrice(OrderTrade orderTrade);

	void sellMarketPrice(OrderTrade orderTrade);

	List<TradeList> selectTradeList(String id);

	Trade selectTrade(int trd_num);
	
	void cancelOrder(Trade trade);
	
	
}
