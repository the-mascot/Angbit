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
	public List<Coin> listStatus(Coin coin) {
		System.out.println("StatusServiceImpl listStatus start...");
		List<Coin> listStatus = sd.listStatus(coin);
		return listStatus;
	}

	@Override
	public List<MemberInfo> krwStatus(MemberInfo member) {
		System.out.println("StatuasServiceImpl krwStatus start...");
		List<MemberInfo> listKrw = sd.listKrw(member);
		return listKrw;
	}

	@Override
	public List<Trade> yStatus(Trade trade) {
		System.out.println("StatusServiceImpl yStatus start...");
		List<Trade> listY = sd.listY(trade); 
		return listY;
	}

	@Override
	public List<Trade> nStatus(Trade trade) {
		System.out.println("StatusServiceImpl nStatus start...");
		List<Trade> listN = sd.listN(trade);
		return listN;
	}



}
