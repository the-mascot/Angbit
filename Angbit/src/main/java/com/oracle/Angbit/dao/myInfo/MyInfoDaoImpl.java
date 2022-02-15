package com.oracle.Angbit.dao.myInfo;

import com.oracle.Angbit.model.common.MemberInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MyInfoDaoImpl implements MyInfoDao {
	
	@Autowired
	private SqlSession session;

	@Override
	public int loginTest(MemberInfo mi) {
		System.out.println("dao id"+mi.getId());
		System.out.println("dao id"+mi.getPassword());

		MemberInfo getmi = session.selectOne("loginTest", mi);
		int result;

		if (getmi!=null) {
			result = 1;
		} else {
			result = 0;
		}

		return result;
	}

	@Override
	public MemberInfo getMyInfo(String id) {
		System.out.println("getMyInfo DAO ID : "+id);
		MemberInfo mi = session.selectOne("getMyInfo", id);
		return mi;
	}

	@Override
	public boolean chkNick(MemberInfo mi) {
		boolean chk = session.selectOne("chkNick", mi);
		System.out.println("닉네임 존재 확인결과 : "+chk);
		// True시 존재하는 닉네임
		return chk;
	}

	@Override
	public int nickChange(MemberInfo mi) {
		System.out.println("nickChange DAO");
		int result = session.update("nickChange", mi);
		System.out.println("닉변후 result ->"+result);
		return result;
	}

	@Override
	public int pwChange(MemberInfo mi) {
		System.out.println("pwChange DAO");
		int result = session.update("pwChange", mi);
		System.out.println("변경 성공?"+result);
		return result;
	}

	@Override
	public boolean chkPw(MemberInfo mi) {
		boolean chk = session.selectOne("chkPw", mi);
		System.out.println("기존 비밀번호? "+chk);
		// True시 사용중인 password
		return chk;
	}

	@Override
	public void widraw(String id) {
		session.update("widraw", id);
	}

	@Override
	public boolean chkWidraw(String id) {
		System.out.println("id는????"+id);
		int chk = session.selectOne("chkWidraw", id);
		System.out.println("회원 탈퇴? "+chk);
		if (chk == 1) {
			return true;
		} else {
			return false;
		}
		// True시 탈퇴
	}

	@Override
	public List getAllId() {
		List<Object> idList = session.selectList("getAllId");
		return idList;
	}

	@Override
	public List getAllCoincode(String id) {
		List<Map> coinList = session.selectList("getAllCoincode", id);

		System.out.println("내부 배열 확인 : "+coinList);
		System.out.println("내부 배열 정보 확인 : "+coinList.size());

		for (int i=0; i<coinList.size(); i++) {
			System.out.println("get(i)번쨰 정보 : "+coinList.get(i).get("COINCODE"));
		}

		return coinList;
	}

	@Override
	public String getId(String nickname) {
		String id = session.selectOne("getId", nickname);
		return id;
	}
}
