package com.oracle.Angbit.dao.myInfo;

import com.oracle.Angbit.model.common.MemberInfo;

import java.util.List;

public interface myInfoDao {

    int loginTest(MemberInfo mi);
    MemberInfo getMyInfo(String id);
    int nickChange(MemberInfo mi);
    boolean chkNick(MemberInfo mi);
    int pwChange(MemberInfo mi);
    boolean chkPw(MemberInfo mi);
    void widraw(String id);
    boolean chkWidraw(String id);
    List getAllId();
    List getAllCoincode(String id);
    void updateAsset(String id, int asset);
}
