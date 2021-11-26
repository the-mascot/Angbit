package com.oracle.Angbit.service.join;

import com.oracle.Angbit.model.common.MemberInfo;

public interface JoinService {

	int JoinProcess(MemberInfo memberinfo);

	int IDchk(MemberInfo memberinfo);

}
