package com.oracle.Angbit.service.myInfo;

import com.oracle.Angbit.dao.invest.InvestDao;
import com.oracle.Angbit.dao.myInfo.myInfoDao;
import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.service.invest.InvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class myInfoServiceImpl implements myInfoService {
	
	@Autowired
	private myInfoDao midao;

	@Override
	public int loginTest(String id, String pw) {
		MemberInfo mi = new MemberInfo();
		mi.setId(id);
		mi.setPassword(pw);

		System.out.println("service id"+mi.getId());
		System.out.println("service id"+mi.getPassword());

		int result = midao.loginTest(mi);
		return result;
	}

	@Override
	public MemberInfo getMyInfo(String id) {
		System.out.println("MemberInfoService getMyInfo() Called.");
		return midao.getMyInfo(id);
	}

	@Override
	public int nickChange(MemberInfo mi) {
		System.out.println("MemberInfoService nickChange() Called.");
		return midao.nickChange(mi);
	}

	@Override
	public boolean chkNick(MemberInfo mi) {
		return midao.chkNick(mi);
	}

	@Override
	public int pwChange(MemberInfo mi) {
		return midao.pwChange(mi);
	}

	@Override
	public boolean chkPw(MemberInfo mi) {
		return midao.chkPw(mi);
	}

	@Override
	public void widraw(String id) {
		midao.widraw(id);
	}
}
