package com.oracle.Angbit.dao.invest;

import java.util.HashMap;
import static org.hamcrest.CoreMatchers.nullValue;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.oracle.Angbit.model.common.Coin;
import org.apache.ibatis.reflection.ReflectionException;
import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

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
	public Float getMyCoin(String id, String currCoin) {
		System.out.println("getMyCoin Dao Called.");
		Map vo = new HashMap();
		vo.put("id", id);
		vo.put("currCoin", currCoin);
		Float sel = seesion.selectOne("getMyCoin", vo);
		if (sel==null) {
			sel = 0f;
		}
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
	public void checkBuyLimits() {

		System.out.println("InvestDaoImpl checkBuyLimits Start...");
		List<CoinInfo> coinList = coinInfoList();
		String coinListStr = "";
		for(int i = 0; i < coinList.size();  i++) {
			if(i == coinList.size()-1)
				coinListStr += "KRW-"+coinList.get(i).getCoincode();
			else
				coinListStr += "KRW-"+coinList.get(i).getCoincode()+", ";
		}
		System.out.println("ESController upCoinListApi coinListStr->"+coinListStr);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		String tickerUrl 	= "https://api.upbit.com/v1/candles/minutes/1?market="+coinListStr;
		System.out.println(tickerUrl);
		ResponseEntity<String> tickerResponse 	= restTemplate.exchange(tickerUrl, HttpMethod.GET, entity, String.class);
		try {
			String tickerStr 	= tickerResponse.getBody();
			
			JSONParser parser = new JSONParser();
			JSONArray json = (JSONArray) parser.parse(tickerStr);
			System.out.println("ESController upCoinListApi json 객체->"+json);
		} catch (Exception e) {
			System.out.println("ESController upCoinListApi Exception->"+e.getMessage());
			e.printStackTrace();
		}
		
	}

}
