package com.oracle.Angbit.dao.myInfo;

import com.oracle.Angbit.model.common.MemberInfo;

public interface myInfoDao {

    int loginTest(MemberInfo mi);
    MemberInfo getMyInfo(String id);
    int nickChange(MemberInfo mi);
    boolean chkNick(MemberInfo mi);
}
