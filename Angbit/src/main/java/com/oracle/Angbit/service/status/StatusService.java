package com.oracle.Angbit.service.status;

import java.util.List;

import com.oracle.Angbit.model.common.Coin;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.common.Trade;

public interface StatusService {
	List<Coin> listStatus(Coin coin);
	List<MemberInfo> krwStatus(MemberInfo member);
	List<Trade> yStatus(Trade trade);
	List<Trade> nStatus(Trade trade);
	MemberInfo memberLogin(MemberInfo member);

}
