package com.oracle.Angbit.service.join;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.Angbit.dao.join.JoinDao;
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
	public int IDchk(MemberInfo memberinfo) {
		System.out.println("JoinServiceImpl - IDchk 시작");
		System.out.println("JoinServiceImpl - IDchk -> " + memberinfo.getId());
		
		int result = 0;
		result = jd.IDchk(memberinfo);
		System.out.println("JoinServiceImpl - IDchk result -> " + result);
		
		return result;
	}


	
	

}