package com.oracle.Angbit.Controller;

import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.service.invest.InvestService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class SHController {

    private static final Logger logger = LoggerFactory.getLogger(ESController.class);

    @Autowired
    private InvestService ivs;

    @RequestMapping("/myPage")
    public String myPageForm(Model model) {
        System.out.println("myPageForm Called.");
        return "myInfo/myInfo";
    }

    @RequestMapping("/chart")
    public String chartTest(Model model) {
        System.out.println("chartTest Called.");
        List<CoinInfo> coinInfoList = ivs.coinInfoList();
        model.addAttribute("coinInfoList", coinInfoList);
        return "myInfo/chartTest";
    }

    @ResponseBody
    @GetMapping("chartApi")
    public void chartApi(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("AngController chartAPI Start...");
//		String currCoin = request.getParameter("currCoin"); // 캔들 호출된 코인명
//		String currCoin = (String) request.getAttribute("currCoin");
        String currCoin = "KRW-BTC"; // 캔들 호출된 코인명
        System.out.println("AngController chartApi currCoin->"+currCoin); // 코인명 콘솔 출력

        RestTemplate restTemplate = new RestTemplate(); // ?
        HttpHeaders headers = new HttpHeaders(); //
        headers.set("Accept", "application/json");
        HttpEntity<?> entity = new HttpEntity<>(headers);

//		String minCandle = "https://api.upbit.com/v1/candles/days?market="+currCoin+"&count=200"; // 일봉 현재일자 기준 200개 요청
        String minCandle = "https://api.upbit.com/v1/candles/minutes/1?market="+currCoin+"&count=200"; // 분봉 현재시간 기준 200개 요청

        System.out.println("HTTP URL : "+minCandle);

        ResponseEntity<String> candleResponse 	= restTemplate.exchange(minCandle, HttpMethod.GET, entity, String.class);

        try {
            String minCandleStr = candleResponse.getBody();

            System.out.println("minCandleStr : "+minCandleStr);

            String jsonStr = minCandleStr.toString();

            System.out.println(jsonStr);

            JSONParser parser = new JSONParser();
            JSONArray json = (JSONArray) parser.parse(jsonStr);
            System.out.println("json 객체->"+json);

            JSONArray chartdata = new JSONArray(); // ajax에서 리턴받을 객체
            for(int i=0; i<json.size(); i++) {
                JSONObject conv = (JSONObject) json.get(i);

                String time = (String) conv.get("candle_date_time_kst"); // 캔들 기준 시(KST), String
                time = time.replace("T"," "); // Timestamp 변경하기 위해 T를 공백으로 변경
                Timestamp timestamp = Timestamp.valueOf(time); // String값을 timestamp로 변경
                long timeconv = (timestamp.getTime()+32400000); // 유닉스타임(13자리)으로 변경시 KST 적용하기 위해 9시간 더하기
                String repl = String.valueOf(timeconv);
                String repl2 = repl.substring(0,10); // 차트에서 Timestamp 10자리만 인식하므로 뒤 3자리 자름
                Long timee = Long.parseLong(repl2); // 차트 인식용 Long Type 변환
                String open = String.valueOf(new BigDecimal((Double)conv.get("opening_price")));
                String high = String.valueOf(new BigDecimal((Double)conv.get("high_price")));
                String low = String.valueOf(new BigDecimal((Double)conv.get("low_price")));
                String close = String.valueOf(new BigDecimal((Double)conv.get("trade_price")));
                LinkedHashMap addJSON = new LinkedHashMap(); // 객체에 값 추가할 시 순서 유지용으로 LinkedHashmap 객체 사용
                addJSON.put("time", timee);
                addJSON.put("open", open);
                addJSON.put("high", high);
                addJSON.put("low", low);
                addJSON.put("close", close);
                chartdata.add(addJSON); // 사용할 값 입력 뒤 JSONArray에 추가
            }

            for(int i=0; i<chartdata.size(); i++) {
                System.out.println("json index "+i+":"+chartdata.get(i));
            }

            PrintWriter out = response.getWriter();
            out.print(chartdata);


        } catch (Exception e1) {
            e1.printStackTrace();
        }


    } // 11-24 11:26 PUSH

}
