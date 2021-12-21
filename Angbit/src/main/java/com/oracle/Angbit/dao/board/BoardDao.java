package com.oracle.Angbit.dao.board;

import java.util.List;

import com.oracle.Angbit.model.common.Board;




public interface BoardDao {

	
	int Delete(int empno);
	int total();
	List<Board> pagingBd(Board board);
	Board detailBoard(Board board);
	int update(Board board);
	
	//Board writeBoard(int b_num);
	
	int insert(Board board);
	int viewCnt(int b_num);
	int instResult(Board board);
	List<Board> levone(Board board);
	int replyDelete(Board board);
	
	Board detailReply(Board board);
	int replyUpdate(Board board);

	Board scdetailBd(Board bd);


    List<Board> testBoardList(int startRow, int endRow);

    Board testBoardContent(int b_num);

	List<Board> testBoardContentComm(int b_num);

}
