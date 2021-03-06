package com.oracle.Angbit.dao.invest;

import java.util.HashMap;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.common.Trade;
import com.oracle.Angbit.model.invest.OrderTrade;
import com.oracle.Angbit.model.invest.TradeList;

@Repository
public class InvestDaoImpl implements InvestDao {
	
	@Autowired
	private SqlSession seesion;
	@Autowired
	private SimpMessagingTemplate template;
	
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
		Map<String, String> vo = new HashMap<String, String>();
		vo.put("id", id);
		vo.put("currCoin", currCoin);
		Float sel = seesion.selectOne("getUsableCoin", vo);
		if (sel==null) {
			sel = 0f;
		}
		System.out.println(currCoin+" ?????? ????????????->"+sel);
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
	@Transactional
	public void checkBuyLimits() {
		System.out.println("InvestDaoImpl checkBuyLimits Start...");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		System.out.println("????????????->"+sdf.format(date));
		List<CoinInfo> coinList = coinInfoList();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		HttpEntity<?> entity = new HttpEntity<>(headers);

		for(int i =0; i < coinList.size(); i++) {
			try {
				Thread.sleep(500);	// UPBIT API ????????? ???????????? 1 ??? ??????(?????? 10?????? ?????? ??????)
				String tickerUrl 	= "https://api.upbit.com/v1/candles/minutes/1?market=KRW-"+coinList.get(i).getCoincode()+"&to="+sdf.format(date)+"&count=1";
				ResponseEntity<String> tickerResponse = restTemplate.exchange(tickerUrl, HttpMethod.GET, entity, String.class);
				String tickerStr = tickerResponse.getBody();
				JSONParser parser = new JSONParser();
				JSONArray jsonArray = (JSONArray) parser.parse(tickerStr);
				JSONObject json = (JSONObject) jsonArray.get(0);
				NumberFormat nf = NumberFormat.getInstance();
				nf.setGroupingUsed(false);
				Map<String, String> map = new HashMap<String, String>();
				map.put("coincode", coinList.get(i).getCoincode());
				map.put("lowPrice", (String) nf.format(json.get("low_price")));
				map.put("highPrice", (String) nf.format(json.get("high_price")));
				List<OrderTrade> sellOrderList = seesion.selectList("checkSellLimits", map);
				List<OrderTrade> buyOrderList = seesion.selectList("checkBuyLimits", map);
				if (buyOrderList.size() > 0) {
					for(OrderTrade orderTrade : buyOrderList) {
						int result = updateBuyLimits(orderTrade);
						if(result > 0) {
							template.convertAndSend("/topic/"+orderTrade.getId(), orderTrade.getTrd_num()+"??? ????????? ?????? ???????????????!");
						}
					}
				}
				if (sellOrderList.size() > 0) {
					for(OrderTrade orderTrade : sellOrderList) {
						int result = updateSellLimits(orderTrade);
						if(result > 0) {
							template.convertAndSend("/topic/"+orderTrade.getId(), orderTrade.getTrd_num()+"??? ????????? ?????? ???????????????!");
						}
					}
				}

			} catch (Exception e) {
				System.out.println("InvestDaoImpl upCoinListApi Exception->"+e.getMessage());
				e.printStackTrace();
			}
		}

	}
	
	@Transactional
	private int updateBuyLimits(OrderTrade orderTrade) {
		
		int result1 = seesion.update("upBuyCoin", orderTrade);
		int result2 = seesion.update("updateTrdStu", orderTrade);
		System.out.println("coincode : "+orderTrade.getCoincode()+"id : "+orderTrade.getId()+" result1 : "+result1+" result2 : "+result2);
		
		return result1 * result2;
	}
	
	@Transactional
	private int updateSellLimits(OrderTrade orderTrade) {
		
		int result1 = seesion.update("upSellCoin", orderTrade);
		int result2 = seesion.update("updateTrdStu", orderTrade);
		// ?????? ??? ?????? ????????? 0?????? ??? ?????? ROW ?????????
		float result3 = seesion.selectOne("chkZero", orderTrade);
		if (result3 <= 0) {
			seesion.delete("delCoinRow", orderTrade);
			System.out.println("?????? ?????? ?????? ROW??????");
		}
		int result4 = seesion.update("increaseKRW", orderTrade);
		
		return result1 * result2 * result4;
	}
	
	public int sellLimitsPrice(OrderTrade orderTrade) {
		//????????? ??????(trade)
		System.out.println("InvestDao sellLimitsPrice() Called.");
		int result = seesion.insert("insertTrade", orderTrade);
		return result;
	}

	@Override
	@Transactional
	public void sellMarketPrice(OrderTrade orderTrade) {
		//????????? ??????(trade,coin update,increaseKRW)
		seesion.insert("insertTrade", orderTrade);
		seesion.update("upSellCoin", orderTrade);
		seesion.update("increaseKRW", orderTrade);

		// ?????? ??? ?????? ????????? 0?????? ??? ?????? ROW ?????????
		float result = seesion.selectOne("chkZero", orderTrade);
		if (result <= 0) {
			seesion.delete("delCoinRow", orderTrade);
		}
	}

	@Override
	public List<TradeList> selectTradeList(String id) {

		System.out.println("InvestDaoImpl selectTradeList Start...");
		List<TradeList> tradeList =null;
		try {
			tradeList = seesion.selectList("selectTradeList", id);
		} catch (Exception e) {
			System.out.println("InvestDaoImpl selectTradeList Exception->"+e.getMessage());
			e.printStackTrace();
		}
		return tradeList;
	}

	@Override
	public Trade selectTrade(int trd_num) {
		
		System.out.println("InvestDaoImpl cancelOrder Start...");
		Trade trade =seesion.selectOne("selectTrade", trd_num);
		
		return trade;
	}
	
	@Override
	@Transactional
	public void cancelOrder(Trade trade) {
		
		System.out.println("InvestDaoImpl cancelBuyOrder Start...");
		if(trade.getTrd_div() == 0) {
			seesion.update("cancelKRW", trade);
			seesion.update("cancelTrade", trade.getTrd_num());
		} else {
			seesion.update("cancelTrade", trade.getTrd_num());
		}
	}
}
