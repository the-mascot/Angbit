package com.oracle.Angbit.dao.board;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.Angbit.model.common.Board;


@Repository
public class BoardJpaRepositoryImpl implements BoardJpaRepository {

	
	private final EntityManager em;
	
	public BoardJpaRepositoryImpl(EntityManager em) {
    	this.em = em;
	}
	
	@Override
	public List<Board> findByTitles(String schword) {
		System.out.println(" BoardJpaRepositoryImpl findByTitles start...");
		String kname = '%' + schword +'%';
		System.out.println(" BoardJpaRepositoryImpl findByTitles kname->"+kname);
		
		List<Board> jpaBdList = em.createQuery("select a from Board a where title Like :kkword", Board.class)
								 .setParameter("kkword", kname)
                				 .getResultList();
		System.out.println(" BoardJpaRepositoryImpl findtt size ->"+jpaBdList.size());
		
		return jpaBdList;
	}

}
