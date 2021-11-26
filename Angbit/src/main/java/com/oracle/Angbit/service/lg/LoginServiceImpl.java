package com.oracle.Angbit.service.lg;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.Angbit.dao.lg.LoginDao;
import com.oracle.Angbit.model.common.MemberInfo;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDao ld;
	
	@Override
	public MemberInfo LoginChk(MemberInfo memberinfo) {
		System.out.println("LoginServiceImpl - LoginChk 시작");
		
		MemberInfo memberinfo1;
		memberinfo1 = ld.LoginChk(memberinfo);
		
		return memberinfo1;
	}

}
