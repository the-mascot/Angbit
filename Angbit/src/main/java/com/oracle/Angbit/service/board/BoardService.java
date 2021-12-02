package com.oracle.Angbit.service.board;

import java.util.List;

import com.oracle.Angbit.model.common.Board;



public interface BoardService {

	List<Board> BoardList(); //보드 요소 가져오기
	//List<MemberInfo> MemberList(); //멤버 요소 가져오기 (NICKNAME)
	int Delete(int empno); //삭제
	int total(); //총페이지
	List<Board> pagingBd(Board board);//페이징 
	Board detailBoard(int b_num);
	int update(Board board);
	
	Board writeBoard(int b_num);
	
	int insert(Board board);
	
	
	
}
