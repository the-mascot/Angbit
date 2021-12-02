package com.oracle.Angbit.service.invest;

import java.util.List;

import com.oracle.Angbit.model.common.Coin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.Angbit.dao.invest.InvestDao;
import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.invest.OrderTrade;

@Service
public class InvestServiceImpl implements InvestService {
	
	@Autowired
	InvestDao ivdao;
	
	@Override
	public List<CoinInfo> coinInfoList() {

		System.out.println("InvestServiceImpl coinInfoList Start...");
		List<CoinInfo> coinInfoList = ivdao.coinInfoList();
		
		return coinInfoList;
	}

	@Override
	public CoinInfo coinInfo(String coincode) {

		System.out.println("InvestServiceImpl CoinInfo Start...");
		CoinInfo coinInfo = ivdao.coinInfo(coincode);
		
		return coinInfo;
	}

	@Override
	public int selectKRW(String id) {

		System.out.println("InvestServiceImpl selectKRW Start...");
		int krw = ivdao.selectKRW(id);
		
		return krw;
	}

	@Override
	public int insertTrade(OrderTrade orderTrade) {
		
		System.out.println("InvestServiceImpl insertTrade Start...");
		int result = ivdao.insertTrade(orderTrade);
		
		return result;
	}

	@Override
	public int updateKRW(OrderTrade orderTrade) {

		System.out.println("InvestServiceImpl updateKRW Start...");
		int result = ivdao.updateKRW(orderTrade);
		
		return result;
	}

	@Override
	public void buyMarketPrice(OrderTrade orderTrade) {
		
		System.out.println("InvestServiceImpl upBuyCoin Start...");
		ivdao.buyMarketPrice(orderTrade);
	}

	@Override
	public void buyLimitsPrice(OrderTrade orderTrade) {

		System.out.println("InvestServiceImpl buyLimitsPrice Start...");
		ivdao.buyLimitsPrice(orderTrade);
	}

	@Override
	public Float getUsableCoin(String id, String currCoin) {
		System.out.println("getMyCoin Service Called.");
		return ivdao.getUsableCoin(id, currCoin);
	}
  
	public List<CoinInfo> searchCoin(String keyWord) {

		System.out.println("InvestServiceImpl buyLimitsPrice Start...");
		List<CoinInfo> coinInfo = ivdao.searchCoin(keyWord);
		
		return coinInfo;
	}

	@Override
	public int sellLimitsPrice(OrderTrade orderTrade) {
		return ivdao.sellLimitsPrice(orderTrade);
	}

}
