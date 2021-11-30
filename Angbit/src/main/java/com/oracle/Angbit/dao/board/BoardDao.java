package com.oracle.Angbit.dao.board;

import java.util.List;

import com.oracle.Angbit.model.common.Board;




public interface BoardDao {

	List<Board> BoardList();
	//List<MemberInfo> MemberList();
	int Delete(int empno);
	int total();
	
	List<Board> pagingBd(Board board);
	Board detailBoard(int b_num);
	int update(Board board);
	List<Board> listManager();
	
}
