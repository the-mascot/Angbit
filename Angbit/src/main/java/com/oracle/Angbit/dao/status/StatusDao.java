package com.oracle.Angbit.dao.status;

import java.util.List;

import com.oracle.Angbit.model.common.Coin;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.common.Trade;

public interface StatusDao {
	List<Coin> listStatus(Coin coin);
	List<MemberInfo> listKrw(MemberInfo member);
	List<Trade> listY(Trade trade);
	List<Trade> listN(Trade trade);
	int priceTot();
	MemberInfo memberLogin(MemberInfo member);

}
