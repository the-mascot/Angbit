package com.oracle.Angbit.service.board;

import java.util.List;

import com.oracle.Angbit.model.common.Board;
import com.oracle.Angbit.model.common.MemberInfo;


public interface BoardService {

	List<Board> BoardList(); //보드 요소 가져오기
	List<MemberInfo> MemberList(); //멤버 요소 가져오기 (NICKNAME)
	int Delete(int empno); //삭제
	int total(); //
	List<MemberInfo> pgMemberList(MemberInfo memberInfo); //페이징 
	
	
	
}
