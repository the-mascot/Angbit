package com.oracle.Angbit.service.status;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.Angbit.dao.status.StatusDao;
import com.oracle.Angbit.model.common.Coin;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.common.Trade;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusDao sd;

	@Override
	public MemberInfo memberLogin(MemberInfo member) {
		System.out.println("StatusServiceImpl login start...");
		MemberInfo member1 = sd.memberLogin(member);
		return member1;
	}

	@Override
	public List<Coin> listStatus(String id) {
		System.out.println("StatusServiceImpl listStatus start...");
		List<Coin> listStatus = sd.listStatus(id);
		return listStatus;
	}

	@Override
	public List<MemberInfo> krwStatus(String id) {
		System.out.println("StatuasServiceImpl krwStatus start...");
		List<MemberInfo> listKrw = sd.listKrw(id);
		return listKrw;
	}

	@Override
	public List<Trade> yStatus(String id) {
		System.out.println("StatusServiceImpl yStatus start...");
		List<Trade> listY = sd.listY(id); 
		return listY;
	}

	@Override
	public List<Trade> nStatus(String id) {
		System.out.println("StatusServiceImpl nStatus start...");
		List<Trade> listN = sd.listN(id);
		return listN;
	}

	@Override
	public int totpriceStatus(String id) {
		System.out.println("StatusServiceImpl totpriceStatus start...");
		int result = sd.priceTot(id);
		return result;
	}

	@Override
	public List<Trade> comBuyList(String id) {
		System.out.println("StatusServiceImpl comBuyList start...");
		List<Trade> cbuyList = sd.buyComList(id);
		return cbuyList;
	}

	@Override
	public List<Trade> comSellList(String id) {
		System.out.println("StatusServiceImpl comSellList start...");
		List<Trade> csellList = sd.sellComList(id);
		return csellList;
	}
	




}
