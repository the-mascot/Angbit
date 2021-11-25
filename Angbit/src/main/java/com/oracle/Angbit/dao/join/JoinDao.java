package com.oracle.Angbit.dao.join;

import com.oracle.Angbit.model.common.MemberInfo;

public interface JoinDao {

	int JoinProcess(MemberInfo memberinfo);

	int IDchk(MemberInfo memberinfo);

}
