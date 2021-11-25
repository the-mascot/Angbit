package com.oracle.Angbit.dao.myInfo;

import com.oracle.Angbit.model.common.MemberInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class myInfoDaoImpl implements myInfoDao {
	
	@Autowired
	private SqlSession session;

	@Override
	public int loginTest(MemberInfo mi) {
		System.out.println("dao id"+mi.getId());
		System.out.println("dao id"+mi.getPassword());

		MemberInfo getmi = session.selectOne("loginTest", mi);
		int result;

		if (getmi!=null) {
			result = 1;
		} else {
			result = 0;
		}

		return result;
	}

	@Override
	public MemberInfo getMyInfo(String id) {
		System.out.println("getMyInfo DAO ID : "+id);
		MemberInfo mi = session.selectOne("getMyInfo", id);
		return mi;
	}

	@Override
	public boolean chkNick(MemberInfo mi) {
		boolean chk = session.selectOne("chkNick", mi);
		System.out.println("닉네임 존재 확인결과 : "+chk);
		// True시 존재하는 닉네임
		return chk;
	}

	@Override
	public int nickChange(MemberInfo mi) {
		System.out.println("nickChange DAO");
		int result = session.update("nickChange", mi);
		System.out.println("닉변후 result ->"+result);
		return result;
	}

	@Override
	public int pwChange(MemberInfo mi) {
		System.out.println("pwChange DAO");
		int result = session.update("pwChange", mi);
		System.out.println("변경 성공?"+result);
		return result;
	}

	@Override
	public boolean chkPw(MemberInfo mi) {
		boolean chk = session.selectOne("chkPw", mi);
		System.out.println("기존 비밀번호? "+chk);
		// True시 사용중인 password
		return chk;
	}
}
