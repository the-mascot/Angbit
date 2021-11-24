package com.oracle.Angbit.service.invest;

import java.util.List;

import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.invest.MemberCoin;

public interface InvestService {

	List<CoinInfo> coinInfoList();

	CoinInfo coinInfo(String coincode);

	MemberCoin memberCoin(MemberCoin paraMemberCoin);
	
	
	
}
