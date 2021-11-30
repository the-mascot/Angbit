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

	@Override
	public int Delete(int b_num) {
		int result = 0;
		System.out.println("BoardServiceImpl delete Start...");
		result = bdao.Delete(b_num);
		return result;
	}

	@Override
	public int total() {
		System.out.println("BoardServiceImpl Start total..." );
		int totCnt = bdao.total();
		System.out.println("BoardServiceImpl total totCnt->"+totCnt );
		return totCnt;
	}


	@Override
	public List<Board> pagingBd(Board board) {
		List<Board> pagingbd = null;
		System.out.println("BoardServiceImpl pagingBd Start..." );
		pagingbd = bdao.pagingBd(board);
		System.out.println("BoardServiceImpl pagingBd pagingbd.size()->" +pagingbd.size());
		
		return pagingbd;
	}

	
	//detail,updateForm
	@Override
	public Board detailBoard(int b_num) {
		System.out.println("BoardServiceImpl detailBoard ...");
		Board board = null;
		
		board = bdao.detailBoard(b_num); //dao에서 리턴받은값 여기로
		
		return board;//리턴 컨트롤러로
	}
	@Override
	public MemberInfo detailMember(String nickname) {
		System.out.println("BoardServiceImpl detailMember ...");
		MemberInfo memberInfo = null;
		
		memberInfo = bdao.detailMember(nickname);
		
		return memberInfo;
	}
	
	@Override
	public int update(Board board) {
		System.out.println("BoardServiceImpl update ...");
		int kkk = 0;
		kkk = bdao.update(board);
		return kkk;
	}

	
	
	
	
	
}
