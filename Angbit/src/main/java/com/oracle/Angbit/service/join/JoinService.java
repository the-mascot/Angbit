package com.oracle.Angbit.service.join;

import com.oracle.Angbit.model.common.MemberInfo;

public interface JoinService {

	int JoinProcess(MemberInfo memberinfo);

	MemberInfo IDchk(String id);

}
