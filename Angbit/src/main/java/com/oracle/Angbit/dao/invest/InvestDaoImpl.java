package com.oracle.Angbit.dao.invest;

import java.util.HashMap;
import java.math.BigDecimal;
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
import com.oracle.Angbit.service.invest.InvestService;
import com.oracle.Angbit.service.myInfo.myInfoService;

@Repository
public class InvestDaoImpl implements InvestDao {
	
	@Autowired
	private SqlSession seesion;
	@Autowired
	private myInfoService mis;
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
	public void checkBuyLimits() {
		System.out.println("InvestDaoImpl checkBuyLimits Start...");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		System.out.println("요청시간->"+sdf.format(date));
		List<CoinInfo> coinList = coinInfoList();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		HttpEntity<?> entity = new HttpEntity<>(headers);

		for(int i =0; i < coinList.size(); i++) {
			try {
				Thread.sleep(500);	// UPBIT API 요청수 제한으로 1 초 대기(초당 10회만 요청 가능)
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
				//System.out.println("lowPrice->"+map.get("lowPrice"));
				List<OrderTrade> sellOrderList = seesion.selectList("checkSellLimits", map);
				List<OrderTrade> buyOrderList = seesion.selectList("checkBuyLimits", map);
				if (buyOrderList.size() > 0) {
					for(OrderTrade orderTrade : buyOrderList) {
						int result1 = seesion.update("upBuyCoin", orderTrade);
						int result2 = seesion.update("updateTrdStu", orderTrade);
						System.out.println("coincode : "+orderTrade.getCoincode()+"id : "+orderTrade.getId()+" result1 : "+result1+" result2 : "+result2);
						if(result1 > 0 && result2 > 0) {
							template.convertAndSend("/topic/"+orderTrade.getId(), orderTrade.getTrd_num()+"번 거래가 체결 되었습니다!");
						}
					}
				}
				if (sellOrderList.size() > 0) {
					for(OrderTrade orderTrade : sellOrderList) {
						int result1 = seesion.update("upSellCoin", orderTrade);
						int result2 = seesion.update("updateTrdStu", orderTrade);
						// 매도 후 보유 코인량 0개일 시 해당 ROW 초기화
						float result3 = seesion.selectOne("chkZero", orderTrade);
						if (result3 <= 0) {
							seesion.delete("delCoinRow", orderTrade);
							System.out.println("전량 매도 코인 ROW삭제");
						}
						int result4 = seesion.update("increaseKRW", orderTrade);
						if(result1 > 0 && result2 > 0 && result4 > 0) {
							template.convertAndSend("/topic/"+orderTrade.getId(), orderTrade.getTrd_num()+"번 거래가 체결 되었습니다!");
						}
						System.out.println("코인명 : "+orderTrade.getCoincode()+"\nid : "+orderTrade.getId()+" 지정가 매도 성공? : "+result1);
						System.out.println("지정가 매도 성공?->"+result2);
					}
				}

			} catch (Exception e) {
				System.out.println("InvestDaoImpl upCoinListApi Exception->"+e.getMessage());
				e.printStackTrace();
			}
		}

	}


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
