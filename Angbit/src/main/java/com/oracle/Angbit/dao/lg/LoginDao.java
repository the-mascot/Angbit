package com.oracle.Angbit.dao.lg;

import com.oracle.Angbit.model.common.MemberInfo;

public interface LoginDao {

	MemberInfo LoginChk(MemberInfo memberinfo);

	int FindPasswordProcess(MemberInfo memberinfo);

	void tempPw(MemberInfo memberinfo2);

}
