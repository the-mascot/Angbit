package com.oracle.Angbit.dao.join;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.Angbit.model.common.MemberInfo;

@Repository
public class JoinDaoImpl implements JoinDao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public int JoinProcess(MemberInfo memberinfo) {
		System.out.println("JoinDaoImpl - JoinProcess 시작");
		int result = 0;
		
		try {
			result = session.insert("JoinProcess", memberinfo);
		} catch (Exception e) {
			System.out.println("JoinDaoImpl - insert Exception -> " + e.getMessage());
		}
		return result;
	}

	@Override
	public int IDchk(MemberInfo memberinfo) {
		System.out.println("JoinDaoImpl - JoinProcess 시작");
		System.out.println("JoinDaoImpl - IDchk -> " + memberinfo.getId());
		
		int result = 0;
		try {
			result = session.selectOne("IDchk", memberinfo);
		} catch (Exception e) {
			System.out.println("JoinDaoImpl - IDchk Exception -> " + e.getMessage());
		}
		System.out.println("JoinDaoImpl - IDchk result -> " + result);
		
		return result;
	}
	
	
	
	
}
