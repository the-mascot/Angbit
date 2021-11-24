package com.oracle.Angbit.service.board;

import java.util.List;

import com.oracle.Angbit.model.common.Board;
import com.oracle.Angbit.model.common.MemberInfo;


public interface BoardService {

	List<Board> BoardList();
	List<MemberInfo> MemberList();
	
}
