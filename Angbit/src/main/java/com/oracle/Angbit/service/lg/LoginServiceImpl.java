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
	public MemberInfo LoginChk(String id, String pw) {
		MemberInfo memberinfo = new MemberInfo();
		memberinfo.setId(id);
		memberinfo.setPassword(pw);

		MemberInfo memberinfo1 = ld.LoginChk(memberinfo);
		return memberinfo1;
	}
	
	@Override
	public int FindPasswordProcess(MemberInfo memberinfo) {
		System.out.println("LoginServiceImpl - FindPasswordProcess 시작~");
		int result = 0;
		result = ld.FindPasswordProcess(memberinfo);
		
		return result;
	}

	@Override
	public void tempPw(String id, String tempPassword) {
		System.out.println("LoginServiceImpl - tempPw");
		MemberInfo memberinfo2 = new MemberInfo();
		memberinfo2.setId(id);
		memberinfo2.setPassword(tempPassword);
		
		ld.tempPw(memberinfo2);
	}

	@Override
	public MemberInfo findById(String id) {
		return ld.findById(id);
	}

}
