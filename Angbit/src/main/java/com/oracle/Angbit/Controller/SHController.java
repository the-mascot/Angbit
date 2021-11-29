package com.oracle.Angbit.Controller;

import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.service.invest.InvestService;
import com.oracle.Angbit.service.myInfo.myInfoService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
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
    @Autowired
    private myInfoService mis;

    @RequestMapping("/myInfo")
    public String myPageForm(Model model, HttpServletRequest request) {
        System.out.println("myPageForm Called.");
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("sessionID");
        System.out.println("myInfo ID? "+id);

        MemberInfo mi = mis.getMyInfo(id);
        System.out.println("mi ID"+mi.getId());
        System.out.println("mi PW"+mi.getPassword());
        System.out.println("mi NICK"+mi.getNickname());
        System.out.println("mi JOIN"+mi.getJoindate());
        System.out.println("mi FINAL"+mi.getFinaldate());

        model.addAttribute("mi", mi);
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

		String currCoin = request.getParameter("currCoin"); // 캔들 호출된 코인명
        String currCandle = request.getParameter("currCandle"); // 캔들 호출된 봉

        // 최초 호출시 비트코인, 1분봉 세팅
        if (currCoin==null || currCoin=="") {
            currCoin = "KRW-BTC";
        }
        if (currCandle==null || currCandle=="") {
            currCandle = "minutes/30";
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String Candle = "https://api.upbit.com/v1/candles/"+currCandle+"?market="+currCoin+"&count=200";

        ResponseEntity<String> candleResponse 	= restTemplate.exchange(Candle, HttpMethod.GET, entity, String.class);

        try {
            String minCandleStr = candleResponse.getBody();

            String jsonStr = minCandleStr.toString();

            JSONParser parser = new JSONParser();
            JSONArray json = (JSONArray) parser.parse(jsonStr);

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

            PrintWriter out = response.getWriter();
            out.print(chartdata);

        } catch (Exception e1) {
            e1.printStackTrace();
        }


    } // 11-24 11:26 PUSH

    @RequestMapping("gologin")
    public String goLogin(Model model) {
        System.out.println("msg?"+model.getAttribute("msg"));
//        model.addAttribute("msg", model.getAttribute("msg"));
        return "myInfo/loginForm";
    }

    @PostMapping("logintest")
    public String loginTest(Model model, HttpServletRequest request, HttpServletResponse response) {
        logger.info("SHController LoginTest() Called.");
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        int result = mis.loginTest(id, pw);
        System.out.println("Controller result ?" + result);
        if (result==1) {
            HttpSession session = request.getSession();
            session.setAttribute("sessionID", id);
        }  else {
            model.addAttribute("msg", "아이디 혹은 비밀번호가 틀립니다.");
            return "forward:gologin";
        }

        return "forward:myInfo";
    }

    @RequestMapping("logout")
    public String logoutTest(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        session.removeAttribute("id");
        session.invalidate();
        return "redirect:chart";
    }

    @ResponseBody
    @PostMapping(value = "nickChange", produces = "application/text;charset=utf8")
    public void nickChange(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("API nickChange called.");
        int result;
        MemberInfo mi = new MemberInfo();
        mi.setNickname(request.getParameter("nickname"));
        mi.setId((String)request.getSession().getAttribute("sessionID"));

        if(mis.chkNick(mi)==true) {
            result = 0;
        } else {
            System.out.println("닉네임 변경 실행");
            result = mis.nickChange(mi);
            MemberInfo chg = mis.getMyInfo(mi.getId());
            request.getSession().setAttribute("sessionNickName", chg.getNickname());
        }
        PrintWriter out = response.getWriter();
        out.print(result);
    }

    @ResponseBody
    @PostMapping(value = "pwChange", produces = "application/text;charset=utf8")
    public void pwChange(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("API pwChange called.");
        int result;
        MemberInfo mi = new MemberInfo();
        mi.setPassword(request.getParameter("password"));
        mi.setId((String)request.getSession().getAttribute("sessionID"));

        if(mis.chkPw(mi)==true) {
            result = 0;
        } else {
            System.out.println("Password 변경 실행");
            result = mis.pwChange(mi);
        }
        PrintWriter out = response.getWriter();
        out.print(result);
    }

    @PostMapping("widraw")
    public String widraw(HttpServletRequest request, Model model) {
        System.out.println("widrawal Request.");
        String id = (String)request.getSession().getAttribute("sessionID");
        mis.widraw(id);
        request.getSession().invalidate();
        return "myInfo/loginForm";
    }

}


// AJAX String 리턴시 한글 깨짐 방지(PrintWriter 객체보다 상위로 선언)
//        response.setContentType("text/html; charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
