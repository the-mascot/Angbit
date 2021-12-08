package com.oracle.Angbit.service.myInfo;

import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.common.MemberInfo;
import org.springframework.ui.Model;

import java.util.List;

public interface myInfoService {
	int loginTest(String id, String pw);
    MemberInfo getMyInfo(String id);
    int nickChange(MemberInfo mi);
    boolean chkNick(MemberInfo mi);
    int pwChange(MemberInfo mi);
    boolean chkPw(MemberInfo mi);
    void widraw(String id);
    boolean chkWidraw(String id);
}
