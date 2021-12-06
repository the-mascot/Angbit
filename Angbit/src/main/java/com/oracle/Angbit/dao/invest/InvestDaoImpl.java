package com.oracle.Angbit.dao.invest;

import java.util.HashMap;
import static org.hamcrest.CoreMatchers.nullValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.oracle.Angbit.model.common.Coin;
import org.apache.ibatis.reflection.ReflectionException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.invest.OrderTrade;

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
	public int selectKRW(String id) {

		System.out.println("InvestDaoImpl selectKRW Start...");
		int krw = 0;
		
		try {
			krw = seesion.selectOne("selectKRW", id);
			System.out.println("InvestDaoImpl selectKRW KRW()->"+krw);
		} catch (Exception e) {
			System.out.println("InvestDaoImpl selectKRW Exception->"+e.getMessage());
			e.printStackTrace();
		}
		
		return krw;
	}

	@Override
	public int insertTrade(OrderTrade orderTrade) {

		System.out.println("InvestDaoImpl insertTrade Start...");
		int result = 0;
		
		try {
			result = seesion.insert("insertTrade", orderTrade);
		} catch (Exception e) {
			System.out.println("InvestDaoImpl insertTrade Exception->"+e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int updateKRW(OrderTrade orderTrade) {

		System.out.println("InvestDaoImpl insertTrade Start...");
		int result = 0;
		
		try {
			result = seesion.update("updateKRW", orderTrade);
		} catch (Exception e) {
			System.out.println("InvestDaoImpl updateKRW Exception->"+e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Transactional
	@Override
	public void buyMarketPrice(OrderTrade orderTrade) {

		System.out.println("InvestDaoImpl upBuyCoin Start...");
			
		seesion.insert("insertTrade", orderTrade);
		seesion.update("updateKRW", orderTrade);
		seesion.update("upBuyCoin", orderTrade);
	}

	@Override
	public void buyLimitsPrice(OrderTrade orderTrade) {

		System.out.println("InvestDaoImpl buyLimitsPrice Start...");
		
		seesion.insert("insertTrade", orderTrade);
		seesion.update("updateKRW", orderTrade);
	}

	@Override
	public Float getUsableCoin(String id, String currCoin) {
		System.out.println("getMyCoin Dao Called.");
		Map vo = new HashMap();
		vo.put("id", id);
		vo.put("currCoin", currCoin);
		Float sel = seesion.selectOne("getUsableCoin", vo);
		if (sel==null) {
			sel = 0f;
		}
		System.out.println(currCoin+" 사용 가능수량->"+sel);
		return sel;
	}
	
	@Override
	public List<CoinInfo> searchCoin(String keyWord) {

		System.out.println("InvestDaoImpl searchCoin Start...");
		List<CoinInfo> coinInfo = null;
		System.out.println("InvestDaoImpl searchCoin keyWord->"+keyWord);
		try {
			coinInfo = seesion.selectList("searchCoin", keyWord);
		} catch (Exception e) {
			System.out.println("InvestDaoImpl searchCoin Exception->"+e.getMessage());
			e.printStackTrace();
		}
		
		
		return coinInfo;
	}

	@Override
	public int sellLimitsPrice(OrderTrade orderTrade) {
		//지정가 매도(trade)
		System.out.println("InvestDao sellLimitsPrice() Called.");
		int result = seesion.insert("insertTrade", orderTrade);
		return result;
	}

	@Override
	@Transactional
	public void sellMarketPrice(OrderTrade orderTrade) {
		//시장가 매도(trade,coin update,increaseKRW)
		seesion.insert("insertTrade", orderTrade);
		seesion.update("upSellCoin", orderTrade);
		seesion.update("increaseKRW", orderTrade);

		// 매도 후 보유 코인량 0개일 시 해당 ROW 초기화
		float result = seesion.selectOne("chkZero", orderTrade);
		if (result == 0) {
			seesion.delete("delCoinRow", orderTrade);
		}
	}
}
