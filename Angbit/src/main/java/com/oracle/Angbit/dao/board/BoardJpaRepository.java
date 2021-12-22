package com.oracle.Angbit.dao.board;

import java.util.List;

import com.oracle.Angbit.model.common.Board;


public interface BoardJpaRepository {
	List<Board>	findByTitles(String schword);
}
