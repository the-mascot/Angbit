package com.oracle.Angbit.dao.status;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.Angbit.model.common.Coin;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.common.Trade;

@Repository
public class StatusDaoImpl implements StatusDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public MemberInfo memberLogin(MemberInfo member) {
		System.out.println("StatusDaoImpl login start...");
		MemberInfo member2 = session.selectOne("login", member);
		return member2;
	}

	@Override
	public List<Coin> listStatus(Coin coin) {
		System.out.println("StatusDaoImpl listStatus start...");
		List<Coin> listStatus = null;
		listStatus = session.selectList("StatusList", coin);
		return listStatus;
	}

	@Override
	public List<MemberInfo> listKrw(MemberInfo member) {
		System.out.println("StatusDaoImpl listKrw start...");
		List<MemberInfo> krwList = null;
		krwList = session.selectList("KrwList", member);
		return krwList;
	}

	@Override
	public List<Trade> listY(Trade trade) {
		System.out.println("StatusDaoImpl listY start...");
		List<Trade> yList = null;
		yList = session.selectList("YList", trade);
		System.out.println("YList.id->"+yList.get(3).getTrd_amt());
		return yList;
	}

	@Override
	public List<Trade> listN(Trade trade) {
		System.out.println("StatusDaoImpl listN start...");
		List<Trade> nList = null;
		nList = session.selectList("NList", trade);
		return nList;
	}

	@Override
	public int priceTot() {
		System.out.println("StatusDaoImpl priceTot start...");
		int result = session.selectOne("TotPrice");
		return result;
	}



}
