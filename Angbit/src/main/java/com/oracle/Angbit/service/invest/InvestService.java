package com.oracle.Angbit.service.invest;

import java.util.List;

import com.oracle.Angbit.model.common.CoinInfo;

public interface InvestService {

	List<CoinInfo> coinInfoList();

	CoinInfo coinInfo(String coincode);
	
	
	
}
