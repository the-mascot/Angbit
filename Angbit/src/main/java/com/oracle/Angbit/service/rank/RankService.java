package com.oracle.Angbit.service.rank;

import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.status.CoinCoinInfo;

import java.util.ArrayList;
import java.util.List;

public interface RankService {

    ArrayList<MemberInfo> getRank(int startRow, int endRow);
    
    int getTotalCnt();
    
    List<CoinCoinInfo> getChart(String id);
    
	void updateAsset();
}
