package com.oracle.Angbit.dao.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.Angbit.model.common.Board;
import com.oracle.Angbit.model.common.MemberInfo;


@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession session;
	
	
	@Override
	public List<Board> BoardList() {
		System.out.println("BoardDaoImpl BoardList Start...");
		List<Board> boardList = null;
		
		try {
			boardList = session.selectList("boardList");
			System.out.println("BoardDaoImpl BoardList boardList.size()->"+boardList.size());
		} catch (Exception e) {
			System.out.println("BoardDaoImpl BoardList Exception->"+e.getMessage());
			e.printStackTrace();
		}
		
		return boardList;
	}


	@Override
	public List<MemberInfo> MemberList() {
		System.out.println("BoardDaoImpl MemberList Start...");
		List<MemberInfo> memberList = null;
		
		try {
			memberList = session.selectList("memberList");
			System.out.println("BoardDaoImpl MemberList memberList.size()->"+memberList.size());
		} catch (Exception e) {
			System.out.println("BoardDaoImpl MemberList Exception->"+e.getMessage());
			e.printStackTrace();
		}
		
		return memberList;
	}

}
