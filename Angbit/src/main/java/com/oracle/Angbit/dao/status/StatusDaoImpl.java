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
	public List<Coin> listStatus(String id) {
		System.out.println("StatusDaoImpl listStatus start...");
		List<Coin> listStatus = null;
		listStatus = session.selectList("StatusList", id);
		return listStatus;
	}

	@Override
	public List<MemberInfo> listKrw(String id) {
		System.out.println("StatusDaoImpl listKrw start...");
		List<MemberInfo> krwList = null;
		krwList = session.selectList("KrwList", id);
		return krwList;
	}

	@Override
	public List<Trade> listY(String id) {
		System.out.println("StatusDaoImpl listY start...");
		List<Trade> yList = null;
		yList = session.selectList("YList", id);
		System.out.println("YList.id->"+yList.get(3).getTrd_amt());
		return yList;
	}

	@Override
	public List<Trade> listN(String id) {
		System.out.println("StatusDaoImpl listN start...");
		List<Trade> nList = null;
		nList = session.selectList("NList", id);
		return nList;
	}

	@Override
	public int priceTot(String id) {
		System.out.println("StatusDaoImpl priceTot start...");
		int result = session.selectOne("TotPrice", id);
		return result;
	}

	@Override
	public List<Trade> buyComList(String id) {
		System.out.println("StatusDaoImpl buyComList start...");
		List<Trade> buycList = session.selectList("CBuyList", id);
		return buycList;
	}

	@Override
	public List<Trade> sellComList(String id) {
		System.out.println("StatusDaoImpl sellComList start...");
		List<Trade> sellList = session.selectList("CSellList", id);
		return sellList;
	}




}
