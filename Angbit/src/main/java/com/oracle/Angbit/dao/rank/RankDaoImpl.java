package com.oracle.Angbit.dao.rank;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.status.CoinCoinInfo;

@Repository
public class RankDaoImpl implements RankDao {
	
	@Autowired
	private SqlSession session;

	@Override
	public List<MemberInfo> MemberKrw(String id) {
		System.out.println("RankDaoImpl - MemberKrw 시작");
		List<MemberInfo> memberkrw = null;
		memberkrw = session.selectList("memberkrw", id);
		
		return memberkrw;
	}
	
	@Override
	public List<CoinCoinInfo> CoinStatus(String id) {
		System.out.println("RankDaoImpl - CoinStatus 시작");
		List<CoinCoinInfo> coinstatus = null;
		coinstatus = session.selectList("coinstatus", id);
		
		return coinstatus;
	}

	@Override
	public int totpriceStatus(String id) {
		System.out.println("RankDaoImpl - totpriceStatus 시작");
		int result = session.selectOne("totpriceStatus", id);
		System.out.println("RankDaoImpl - totpriceStatus result -> " + result);
		
		return result;
	}

	@Override
	public List<MemberInfo> memberid() {
		System.out.println("RankDaoImpl - totpriceStatus 시작");
		List<MemberInfo> memberid = session.selectList("memberid");
		
		return memberid;
	}
	
	
}
