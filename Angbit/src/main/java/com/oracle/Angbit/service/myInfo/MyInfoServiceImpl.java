package com.oracle.Angbit.service.myInfo;

import com.oracle.Angbit.dao.myInfo.MyInfoDao;
import com.oracle.Angbit.model.common.MemberInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyInfoServiceImpl implements MyInfoService {
	
	@Autowired
	private MyInfoDao midao;

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

	@Override
	public boolean chkWidraw(String id) {
		return midao.chkWidraw(id);
	}

	@Override
	public List getAllId() {
		return midao.getAllId();
	}

	@Override
	public List getAllCoincode(String id) {
		return midao.getAllCoincode(id);
	}

	@Override
	public String getId(String nickname) {
		return midao.getId(nickname);
	}
}
