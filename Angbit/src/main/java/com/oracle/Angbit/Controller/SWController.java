package com.oracle.Angbit.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.service.join.JoinService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class SWController {
	
	private static final Logger logger = LoggerFactory.getLogger(SWController.class);
	
	@Autowired
	private JoinService js;
	
	// 메인 페이지에서 오른쪽 위 '회원가입' 버튼을 누르면 회원가입 창으로 이동하는 기능을 하는 컨트롤러
	@RequestMapping("/joinForm")
	public String joinForm(Model model) {
		System.out.println("AngController - joinForm 시작");
		
		return "/join/joinForm";
	}
	
	// 회원가입 페이지(JoinForm.html)에서 회원정보를 입력하고 가입하기 버튼을 눌렀을 때, 회원가입 절차 컨트롤러
	@RequestMapping(value = "/JoinProcess", method = RequestMethod.POST)
	public String JoinProcess(Model model, MemberInfo memberinfo) {
		System.out.println("AngController - JoinProcess 시작");
		
		String returnStr = "";
		int result = js.JoinProcess(memberinfo);
		if(result == 1) {
			returnStr = "/join/joinSuccess";
		} else {
			returnStr = "/join/joinFail";
		}
		return returnStr;
	}
	
	// 회원가입 페이지에서 아이디 중복확인 버튼을 누르면 동작하는 Ajax 컨트롤러
	@ResponseBody
	@RequestMapping(value = "IDchk", produces = "application/text;charset=UTF-8")
	public int IDchk(MemberInfo memberinfo, String id) {
		System.out.println("AngController - IDchk 시작~");
		memberinfo.setId(id);
		System.out.println("AngController - IDchk - id -> " + memberinfo.getId());
		int IDchkNum = js.IDchk(memberinfo);
		
		return IDchkNum;
	}
	
	
	
	
	
}
