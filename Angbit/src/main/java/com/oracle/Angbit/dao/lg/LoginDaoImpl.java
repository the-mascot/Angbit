package com.oracle.Angbit.dao.lg;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.Angbit.model.common.MemberInfo;

@Repository
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public MemberInfo LoginChk(MemberInfo memberinfo) {
		System.out.println("LoginDaoImpl - LoginChk 시작");
		MemberInfo memberinfo1 = null;
		
		try {
			memberinfo1 = session.selectOne("LoginChk", memberinfo);
		} catch (Exception e) {
			System.out.println("JoinDaoImpl - insert Exception -> " + e.getMessage());
		}
		return memberinfo1;
	}

}
