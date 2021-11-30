package com.oracle.Angbit.service.join;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.Angbit.dao.join.JoinDao;
import com.oracle.Angbit.dao.lg.LoginDao;
import com.oracle.Angbit.model.common.MemberInfo;


@Service
public class JoinServiceImpl implements JoinService {
	
	@Autowired
	private JoinDao jd;

	@Override
	public int JoinProcess(MemberInfo memberinfo) {
		System.out.println("JoinServiceImpl - JoinProcess 시작");
		int result = 0;
		result = jd.JoinProcess(memberinfo);
		System.out.println("EmpServiceImpl - insert result -> " + result);
		
		return result;
	}

	@Override
	public MemberInfo IDchk(String id) {
		System.out.println("JoinServiceImpl - IDchk 시작");
		
		MemberInfo memberInfo = jd.IDchk(id);
		
		return memberInfo;
	}

	@Override
	public MemberInfo chkNickname(String nickname) {
		System.out.println("JoinServiceImpl - chkNickname 시작");
		
		MemberInfo memberInfo = jd.chkNickname(nickname);
		
		return memberInfo;
	}


	
	

}