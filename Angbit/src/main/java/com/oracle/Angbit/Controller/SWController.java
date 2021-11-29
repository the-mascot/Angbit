package com.oracle.Angbit.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.service.join.JoinService;
import com.oracle.Angbit.service.lg.LoginService;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class SWController {
	
	private static final Logger logger = LoggerFactory.getLogger(SWController.class);
	
	@Autowired
	private JoinService js;
	@Autowired
	private LoginService ls;
	@Autowired
	private JavaMailSender mailSender;
	
	// 메인 페이지에서 오른쪽 위 '회원가입' 버튼을 누르면 회원가입 창으로 이동하는 기능을 하는 컨트롤러
	@RequestMapping("/joinForm")
	public String joinForm(Model model) {
		System.out.println("SWController - joinForm 시작");
		
		return "/join/joinForm";
	}
	
	// 회원가입 페이지(JoinForm.html)에서 회원정보를 입력하고 가입하기 버튼을 눌렀을 때, 회원가입 절차 컨트롤러
	@RequestMapping(value = "/JoinProcess", method = RequestMethod.POST)
	public String JoinProcess(Model model, MemberInfo memberinfo) {
		System.out.println("SWController - JoinProcess 시작");
		
		String returnStr = "";
		int result = js.JoinProcess(memberinfo);
		if(result == 1) {
			returnStr = "/join/joinSuccess";
		} else {
			returnStr = "/join/joinFail";
		}
		return returnStr;
	}
	
	// 회원가입 페이지에서 아이디 '중복확인' 버튼을 누르면 동작하는 Ajax 컨트롤러
	@ResponseBody
	@GetMapping(value = "IDchk", produces = "application/text;charset=UTF-8")
	public String IDchk(String id) {
		System.out.println("SWController - IDchk 시작~");
		String msg = "";
		
		MemberInfo memberInfo = js.IDchk(id);
		if (memberInfo != null) {
			msg = "중복된 이메일 아이디 입니다.";
		} else {
			msg = "사용 가능한 이메일 아이디입니다.";
		}
		System.out.println("msg의 값은? -> " + msg);
		return msg;
	}
	
	// 메인페이지 우측 상단에 '로그인' 버튼을 누르면 loginForm 페이지로 이동하는 컨트롤러
	@RequestMapping(value = "loginForm")
	public String loginForm(Model model) {
		System.out.println("SWController - loginForm");
		
		return "lg/loginForm";
	}
	
	// 로그인 페이지에서 올바른 아이디, 비밀번호를 입력 후 '로그인' 버튼을 눌렀을 때 동작하는 페이지
	@PostMapping(value = "LoginProcess")
	public String LoginProcess(Model model, MemberInfo memberinfo, HttpServletRequest request) {
		System.out.println("SWController - LoginProcess 시작");
        String id = memberinfo.getId();
        String pw = memberinfo.getPassword();
		
        MemberInfo memberinfo1 = ls.LoginChk(id, pw);
        
        if (memberinfo1 != null) {
            HttpSession session = request.getSession();
            session.setAttribute("sessionID", id);
            session.setAttribute("sessionNickName", memberinfo1.getNickname());
        } else {
            return "lg/loginFail";
        }
        return "lg/loginSuccess";
    }
	
	// 로그인 상태에서 로그아웃 버튼을 누르면 로그아웃 되는 컨트롤러
    @RequestMapping(value = "LogOutProcess")
    public String logoutTest(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        session.removeAttribute("id");
        
        session.invalidate();
        return "/index";
    }
    
    // 로그인 페이지에서 '비밀번호 찾기' 버튼을 누르면 비밀번호 찾기 페이지로 이동하는 컨트롤러
    @RequestMapping(value = "findPassword")
    public String findPassword(Model model) {
    	System.out.println("SWController - findPassword");
    	
    	return "lg/findPasswordForm";
    }
    
    // 비밀번호 찾기 페이지에서 이메일과 닉네임을 입력하고 '비밀번호 찾기' 버튼을 누르면 처리되는 컨트롤러
    @RequestMapping(value = "FindPasswordProcess")
    public String FindPasswordProcess(Model model, MemberInfo memberinfo) {
    	
    	System.out.println("SWController - FindPasswordProcess 시작~");
    	System.out.println("memberinfo.getId() -> " + memberinfo.getId());
    	System.out.println("memberinfo.getNickname() -> " + memberinfo.getNickname());
    	String returnStr = "";
    	
    	int result = ls.FindPasswordProcess(memberinfo);
    	System.out.println("SWController - FindPasswordProcess - result의 값은? -> " + result);
    	
    	// 입력한 이메일과 닉네임이 DB와 일치하면 1을 리턴한다.
    	if(result == 1) {
    		String tomail = memberinfo.getId();                 // 받는 사람 이메일
    		String setfrom = "angbit456@gmail.com";             // 보내는 사람 
    		String title = "AngBit 비밀번호 재설정 메일입니다.";       // 제목
    		try {
    			MimeMessage message = mailSender.createMimeMessage();
    			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    			messageHelper.setFrom(setfrom);    // 보내는사람 생략하거나 하면 정상작동을 안함
    			messageHelper.setTo(tomail);       // 받는사람 이메일
    			messageHelper.setSubject(title);   // 메일 제목은 생략이 가능하다
    			String tempPassword = (int) (Math.random() * 99999999) + 1 + "";
    			messageHelper.setText("임시 비밀번호는 : " + tempPassword + " 입니다."); // 메일 내용
    			System.out.println("임시 비밀번호입니다 : " + tempPassword);
    			
    			mailSender.send(message);
    			System.out.println("이메일 보내는 데 성공했습니다. ^^");
    			
    			ls.tempPw(memberinfo.getId(), tempPassword)  ;// db에 비밀번호를 임시비밀번호로 업데이트
    			
    		} catch (Exception e) {
    			System.out.println("이메일 보내는 데 실패하고 오류 메시지 -> " + e.getMessage());
    			System.out.println("이메일 보내는 데 실패했습니다.ㅠㅠㅠㅠㅠ");
    		}
    		returnStr = "lg/findPassSuccess";
    	} else {
    		System.out.println("이메일과 닉네임이 일치하지 않습니다.");
    		returnStr = "lg/findPassFail";
    	}
    	return returnStr;
    }
    
	
	
}
