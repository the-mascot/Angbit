package com.oracle.Angbit.dao.board;

import java.util.List;

import com.oracle.Angbit.model.common.Board;




public interface BoardDao {

	
	int Delete(int empno);
	int total();
	List<Board> pagingBd(Board board);
	Board detailBoard(int b_num);
	int update(Board board);
	
	//Board writeBoard(int b_num);
	
	int insert(Board board);
	int viewCnt(int b_num);
	int instResult(Board board);
	Board levone(int ref);
	int replyDelete(int ref);
	int arrange();
	Board detailReply(int ref);
	
	
	
}
