package com.oracle.Angbit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.service.myInfo.MyInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class CustomAuthenticationProvider { //implements AuthenticationProvider {
	
//	@Autowired
//	private MyInfoService mis;
//	
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//	
//	@Override
//	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
//		// AuthenticationFilter에서 생성된 토큰으로 부터 아이디와 비밀번호를 조회함
//		String userId = token.getName();
//		String userPw = (String) token.getCredentials();
//		MemberInfo memberInfo = mis.getMyInfo(userId);
//		
//		if(!passwordEncoder.matches(userPw, memberInfo.getPassword()))
//			throw new BadCredentialsException("로그인 정보가 일치하지 않습니다");
//		
//		return new UsernamePasswordAuthenticationToken(memberInfo, userPw, memberInfo.get);
//	}
//
//	@Override
//	public boolean supports(Class<?> authentication) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
