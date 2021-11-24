package com.oracle.Angbit.dao.invest;

import java.util.List;

import com.oracle.Angbit.model.common.CoinInfo;

public interface InvestDao {
	
	List<CoinInfo> coinInfoList();

	CoinInfo coinInfo(String coincode);
	
}
