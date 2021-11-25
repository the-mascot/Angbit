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
}
