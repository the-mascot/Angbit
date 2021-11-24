package com.oracle.Angbit.dao.board;

import java.util.List;

import com.oracle.Angbit.model.common.Board;
import com.oracle.Angbit.model.common.MemberInfo;



public interface BoardDao {

	List<Board> BoardList();
	List<MemberInfo> MemberList();
}
