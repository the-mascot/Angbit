package com.oracle.Angbit.service.board;

import java.util.List;

import com.oracle.Angbit.model.common.Board;



public interface BoardService {

	
	int Delete(int empno); //삭제
	int total(); //총페이지
	List<Board> pagingBd(Board board);//페이징 
	Board detailBoard(int b_num);
	int update(Board board);
	
	//Board writeBoard(int b_num);
	
	int insert(Board board);
	int viewCnt(int b_num);
	int instResult(Board board);
	Board levone(int ref);
	int replyDelete(Board board);
	
	Board detailReply(int ref);
	int replyUpdate(Board board);


    List<Board> testBoardList(int startRow, int endRow);

    Board testBoardContent(int b_num);

	List<Board> testBoardContentComm(int b_num);
}
