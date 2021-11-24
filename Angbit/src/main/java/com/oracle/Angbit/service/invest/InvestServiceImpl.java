package com.oracle.Angbit.service.invest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.Angbit.dao.invest.InvestDao;
import com.oracle.Angbit.model.common.CoinInfo;

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
	
	
	
}
