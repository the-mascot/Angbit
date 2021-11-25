package com.oracle.Angbit.dao.status;

import java.util.List;

import com.oracle.Angbit.model.common.Coin;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.common.Trade;

public interface StatusDao {
	List<Coin> listStatus(String id);
	List<MemberInfo> listKrw(String id);
	List<Trade> listY(String id);
	List<Trade> listN(String id);
	int priceTot(String id);
	List<Trade> buyComList(String id);
	List<Trade> sellComList(String id);
	MemberInfo memberLogin(MemberInfo member);

}
