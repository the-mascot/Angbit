package com.oracle.Angbit.service.rank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.Angbit.dao.rank.RankDao;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.status.CoinCoinInfo;

@Service
public class RankServiceImpl implements RankService {
	
	@Autowired
	private RankDao rd;
	
	@Override
	public List<MemberInfo> MemberKrw(String id) {
		System.out.println("RankServiceImpl - MemberKrw 시작");
		List<MemberInfo> memberkrw = rd.MemberKrw(id);
		
		return memberkrw;
	}

	@Override
	public List<CoinCoinInfo> CoinStatus(String id) {
		System.out.println("RankServiceImpl - CoinStatus 시작");
		List<CoinCoinInfo> coinstatus = rd.CoinStatus(id);
		
		return coinstatus;
	}

	@Override
	public int totpriceStatus(String id) {
		System.out.println("RankServiceImpl - totpriceStatus 시작");
		int result = rd.totpriceStatus(id);
		
		return result;
	}

	@Override
	public List<MemberInfo> memberid() {
		System.out.println("RankServiceImpl - memberid 시작");
		List<MemberInfo> memberid = rd.memberid();
		
		return memberid;
	}

}
