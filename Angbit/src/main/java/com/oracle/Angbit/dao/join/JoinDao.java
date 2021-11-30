package com.oracle.Angbit.dao.join;

import com.oracle.Angbit.model.common.MemberInfo;

public interface JoinDao {

	int JoinProcess(MemberInfo memberinfo);

	MemberInfo IDchk(String id);

	MemberInfo chkNickname(String nickname);

}
