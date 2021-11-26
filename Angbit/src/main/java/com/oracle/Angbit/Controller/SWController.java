package com.oracle.Angbit.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.service.join.JoinService;
import com.oracle.Angbit.service.lg.LoginService;

import javax.servlet.http.HttpServletRequest;
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
	@RequestMapping(value = "LoginProcess")
	public String LoginProcess(Model model, MemberInfo memberinfo, HttpServletRequest request) {
		System.out.println("SWController - LoginProcess 시작");
		System.out.println("memberinfo.getId() -> " + memberinfo.getId());
		System.out.println("memberinfo.getPassword(); -> " + memberinfo.getPassword());
		
		HttpSession session=request.getSession();
		String LoginResult = "";
		
		MemberInfo memberinfo1;
		memberinfo1 = ls.LoginChk(memberinfo);
		if(memberinfo1 != null) {
			session.setAttribute("SessionID", memberinfo1.getId());
			session.setAttribute("Sessionnickname", memberinfo1.getNickname());
			session.setAttribute("SessionKrw", memberinfo1.getKrw());
			LoginResult = "/lg/loginSuccess";
		} else {
			System.out.println("로그인 실패닷!!!!!!!!!!!!!");
			LoginResult = "/lg/loginFail";
		}
		
		return LoginResult;
	}
	
	
	
}
