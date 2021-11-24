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
		MemberInfo getmi = session.selectOne("loginTest", mi);
		return 0;
	}
}
