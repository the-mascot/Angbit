package com.oracle.Angbit.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.Angbit.dao.board.BoardDao;
import com.oracle.Angbit.model.common.Board;






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
	
	//update
	@Override
	public int update(Board board) {
		System.out.println("BoardServiceImpl update start...");
		int result = bdao.update(board);
		return result;
	}

	@Override
	public Board writeBoard(int b_num) {
		
		System.out.println("BoardServiceImpl writeBoard start...");
		Board board = bdao.writeBoard(b_num);
		return board;
	}

	@Override
	public int insert(Board board) {
		System.out.println("BoardServiceImpl insert start...");
		int kkk=0;
		kkk = bdao.insert(board);
		
		return kkk;
	}
	
	

	
	
	
	
	
}
