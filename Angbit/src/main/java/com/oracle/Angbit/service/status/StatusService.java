package com.oracle.Angbit.service.status;

import java.util.List;

import com.oracle.Angbit.model.common.Coin;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.common.Trade;

public interface StatusService {
	List<Coin> listStatus(String id);
	List<MemberInfo> krwStatus(String id);
	List<Trade> yStatus(String id);
	List<Trade> nStatus(String id);
	int totpriceStatus(String id);
	List<Trade> comBuyList(String id);
	List<Trade> comSellList(String id);
	MemberInfo memberLogin(MemberInfo member);

}
