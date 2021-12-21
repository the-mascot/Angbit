package com.oracle.Angbit.service.board;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.Angbit.dao.board.BoardJpaRepository;
import com.oracle.Angbit.model.common.Board;


@Transactional
@Service
public class BoardJpaService {

	private final BoardJpaRepository boardJpaRepository; //dao
	
	@Autowired//autowired생략가능
	public BoardJpaService(BoardJpaRepository boardJpaRepository) {
		this.boardJpaRepository = boardJpaRepository;
	}
	
	public List<Board> getListAllBoard(String schword) {
		System.out.println("BoardJpaService getListAllBoard schword->"+schword);
		List<Board> listBoard  = boardJpaRepository.findByTitles (schword);
		System.out.println("BoardJpaService getListAllBoard listBoard.size()->"
		                    +listBoard.size());
		return listBoard;
	}
	
	
	
}
