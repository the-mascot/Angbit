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
		System.out.println("dao id" + memberinfo.getId());
		System.out.println("dao id" + memberinfo.getPassword());

		MemberInfo memberinfo1 = session.selectOne("LoginChk", memberinfo);

		return memberinfo1;
	}

	@Override
	public int FindPasswordProcess(MemberInfo memberinfo) {
		System.out.println("LoginDaoImpl - FindPasswordProcess 시작~");
		int result = 0;
		try {
			result = session.selectOne("FindPasswordProcess", memberinfo);
		} catch (Exception e) {
			System.out.println("LoginDaoImpl - FindPasswordProcess Exception -> " + e.getMessage());
		}
		return result;
	}

	@Override
	public void tempPw(MemberInfo memberinfo2) {
		System.out.println("LoginDaoImpl - tempPw 시작");
		try {
			session.update("tempPw", memberinfo2);
		} catch (Exception e) {
			System.out.println("LoginDaoImpl - tempPw Exception -> " + e.getMessage());
		}
	}


	
	
	

}
