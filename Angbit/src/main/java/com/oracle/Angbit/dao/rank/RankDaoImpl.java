package com.oracle.Angbit.dao.rank;

import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.status.CoinCoinInfo;
import com.oracle.Angbit.service.invest.InvestService;
import com.oracle.Angbit.service.myInfo.MyInfoService;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RankDaoImpl implements RankDao{

    @Autowired
    private SqlSession session;
    @Autowired
    private InvestService ivs;
    @Autowired
    private MyInfoService mis;

    @Override
    public ArrayList<MemberInfo> getRank(int startRow, int endRow) {
        Map vo = new HashMap();
        vo.put("startRow", startRow);
        vo.put("endRow", endRow);
        List<MemberInfo> list = new ArrayList<>();
        list = session.selectList("getRank", vo);
        return (ArrayList<MemberInfo>) list;
    }

    @Override
    public int getTotalCnt() {
        int totCnt = session.selectOne("getTotCntMember");
        return totCnt;
    }

    @Override
    public List<CoinCoinInfo> getChart(String id) {
        List<CoinCoinInfo> chardata = null;
        chardata = session.selectList("getChart", id);
        return chardata;
    }
    
	// 15개 코인 trade_price 리턴 method
    public HashMap tradePriceList() {
        String [] coinlist = {"KRW-BTC", "KRW-ETH", "KRW-ADA", "KRW-XRP", "KRW-DOT",
                "KRW-DOGE", "KRW-MANA", "KRW-LTC", "KRW-SAND", "KRW-VET", "KRW-AXS",
                "KRW-SOL", "KRW-BCH", "KRW-XLM", "KRW-ETC"};
        String [] coincode = {"BTC", "ETH", "ADA", "XRP", "DOT",
                "DOGE", "MANA", "LTC", "SAND", "VET", "AXS",
                "SOL", "BCH", "XLM", "ETC"};
        String arrToString = String.join(",",coinlist); // 배열을 문자열로
        float price = 0f;
        int i = 0;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HashMap obj = new HashMap();
        try {
            String ticker = "https://api.upbit.com/v1/ticker?markets="+arrToString;
            ResponseEntity<String> candleResponse = restTemplate.exchange(ticker, HttpMethod.GET, entity, String.class);
            JSONParser parser = new JSONParser();
            JSONArray json = (JSONArray) parser.parse(candleResponse.getBody().toString());
            for (String coin : coincode) {
                JSONObject conv = (JSONObject) json.get(i);
                price = Float.valueOf(String.valueOf(new BigDecimal((Double) conv.get("trade_price")))); // 지수표현 제거
                obj.put(coin, price);
                i++;
                System.out.println("테스트용 KEY 호출 "+coin+" :"+obj.get(coin));
            }
            // 여기까지 KEY(코인명):VALUE(가격) 객체 생성

        } catch (Exception e) {
            System.out.println("tradePriceList Error!"+e.getMessage());
        }
        return obj;
    }

    // TABLE용 최신가 업데이트 method
    @Override
    public void updateAsset() {
        HashMap trp = tradePriceList(); // KEY(코인명) : VALUE(현재가)
        List idList = mis.getAllId(); // idList -> 각 인덱스마다 id 배열
        for (Object id : idList) {
            BigDecimal sum = new BigDecimal(0); // 총 자산으로 리턴할 자산 합계
            List<Map> coinlist = mis.getAllCoincode(id.toString()); // id가 보유한 {COINCODE : 코인명, COIN_AMT : 코인량} 형태의 MAP

            for (Map coin : coinlist) { // 보유코인 합산
                float coin_price = (float) trp.get(coin.get("COINCODE"));
                double coin_amt = (double) coin.get("COIN_AMT");
                BigDecimal coin_totprice = new BigDecimal(coin_price * coin_amt);
                System.out.println("totprice! : "+coin_totprice);
                System.out.println(id+"가 보유중인 "+coin.get("COINCODE")+"의 총 가격 : "+coin_totprice);
                sum = sum.add(coin_totprice);
            }

            BigDecimal KRW = new BigDecimal(ivs.selectKRW(id.toString()));
            System.out.println(id+"가 보유중인 KRW : " + KRW);
            sum = sum.add(KRW);
            System.out.println(id+"가 보유중인 총 자산 : "+sum);
            
    		Map vo = new HashMap();
    		vo.put("id", id.toString());
    		vo.put("asset", sum.intValue());
    		session.update("updateAsset", vo);
        }
    }
    
}
