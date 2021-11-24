package com.oracle.Angbit.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.Angbit.dao.board.BoardDao;
import com.oracle.Angbit.model.common.Board;
import com.oracle.Angbit.model.common.MemberInfo;



@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao bdao;

	@Override
	public List<Board> BoardList() {
		System.out.println("BoardServiceImpl BoardList Start...");
		List<Board> boardList = bdao.BoardList();
		return boardList;
	}

	@Override
	public List<MemberInfo> MemberList() {
		System.out.println("BoardServiceImpl MemberList Start...");
		List<MemberInfo> memberList = bdao.MemberList();
		return memberList;
	}
	
	
	
}
