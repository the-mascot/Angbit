package com.oracle.Angbit.dao.invest;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.invest.MemberCoin;

@Repository
public class InvestDaoImpl implements InvestDao {
	
	@Autowired
	private SqlSession seesion;
	
	@Override
	public List<CoinInfo> coinInfoList() {
		
		System.out.println("InvestDaoImpl coinInfoList Start...");
		List<CoinInfo> coinInfoList = null;
		
		try {
			coinInfoList = seesion.selectList("coinInfoList");
			System.out.println("InvestDaoImpl coinInfoList coinInfoList.size()->"+coinInfoList.size());
		} catch (Exception e) {
			System.out.println("InvestDaoImpl coinInfoList Exception->"+e.getMessage());
			e.printStackTrace();
		}
		
		return coinInfoList;
	}

	@Override
	public CoinInfo coinInfo(String coincode) {

		System.out.println("InvestDaoImpl coinInfo Start...");
		CoinInfo coinInfo = new CoinInfo();
		
		try {
			coinInfo = seesion.selectOne("coinInfo", coincode);
			System.out.println("InvestDaoImpl coinInfo coinInfo.getCoinname()->"+coinInfo.getCoinname());
		} catch (Exception e) {
			System.out.println("InvestDaoImpl coinInfo Exception->"+e.getMessage());
			e.printStackTrace();
		}
		
		return coinInfo;
	}

	@Override
	public MemberCoin memberCoin(MemberCoin paraMemberCoin) {

		System.out.println("InvestDaoImpl memberCoin Start...");
		MemberCoin memberCoin = new MemberCoin();
		
		try {
			memberCoin = seesion.selectOne("memberCoin", paraMemberCoin);
		} catch (Exception e) {
			System.out.println("InvestDaoImpl memberCoin Exception->"+e.getMessage());
			e.printStackTrace();
		}
		
		return memberCoin;
	}

}
