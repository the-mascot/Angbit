package com.oracle.Angbit.service.lg;

import com.oracle.Angbit.model.common.MemberInfo;

public interface LoginService {

	MemberInfo LoginChk(String id, String pw);

	int FindPasswordProcess(MemberInfo memberinfo);

	void tempPw(String id, String tempPassword);

	MemberInfo findById(String id);
}
