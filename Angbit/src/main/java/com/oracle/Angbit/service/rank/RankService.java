package com.oracle.Angbit.service.rank;

import java.util.List;

import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.status.CoinCoinInfo;

public interface RankService {

	List<MemberInfo> MemberKrw(String id);

	List<CoinCoinInfo> CoinStatus(String id);

	int totpriceStatus(String id);

	List<MemberInfo> memberid();

}
