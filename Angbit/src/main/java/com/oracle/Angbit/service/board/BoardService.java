package com.oracle.Angbit.service.board;

import java.util.List;

import com.oracle.Angbit.model.common.Board;
import com.oracle.Angbit.model.common.MemberInfo;


public interface BoardService {

	List<Board> BoardList(); //보드 요소 가져오기
	List<MemberInfo> MemberList(); //멤버 요소 가져오기 (NICKNAME)
	int Delete(int empno); //삭제
	int total(); //
 //페이징 
	List<Board> pagingBd(Board board);
	Board detailBoard(int b_num);
	int update(Board board);
	MemberInfo detailMember(String nickname);
	
	
	
}
