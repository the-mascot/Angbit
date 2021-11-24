package com.oracle.Angbit.dao.invest;

import java.util.List;

import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.invest.MemberCoin;

public interface InvestDao {
	
	List<CoinInfo> coinInfoList();

	CoinInfo coinInfo(String coincode);

	MemberCoin memberCoin(MemberCoin paraMemberCoin);
	
}
