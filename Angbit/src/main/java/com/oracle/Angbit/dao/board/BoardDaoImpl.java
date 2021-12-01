package com.oracle.Angbit.dao.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.Angbit.model.common.Board;





@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession session;
	
//게시판 양식 가져오기	
	@Override
	public List<Board> BoardList() {
		System.out.println("BoardDaoImpl BoardList Start...");
		List<Board> boardList = null;
		
		try {
			boardList = session.selectList("boardList"); 					//Board.xml에서 가져갈 이름
			System.out.println("BoardDaoImpl BoardList boardList.size()->"+boardList.size());
			
		} catch (Exception e) {
			System.out.println("BoardDaoImpl BoardList Exception->"+e.getMessage());
			e.printStackTrace();
		}
		
		return boardList;
	}

//페이징
	@Override
	public int total() {
		int tot = 0;
		System.out.println("BoardDaoImpl total Start ..." );
		try {
			//    Mapper    ------>   Map ID (Naming Rule)
			tot = session.selectOne("dyboardTotal");
		} catch (Exception e) {
			System.out.println("BoardDaoImpl total Exception->"+e.getMessage());
		}
		
		return tot;
	}

	@Override
	public List<Board> pagingBd(Board board) {
		List<Board> pagingbd = null;
		System.out.println("BoardDaoImpl pagingBd Start ..." );
		try {
			//                             Naming Rule 
			pagingbd = session.selectList("pagingbd", board);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl pagingBd Exception->"+e.getMessage());
		}
		return pagingbd;
	}

	
	//detail,updateForm
	@Override
	public Board detailBoard(int b_num) {
		System.out.println("BoardDaoImpl detail start..");
		Board board = new Board();
		try {
			//                       mapper ID   ,    Parameter
			board = session.selectOne("dyBoardSelOne",    b_num);
			System.out.println("BoardDaoImpl detail getTitle->"+board.getB_num());
		} catch (Exception e) {
			System.out.println("BoardDaoImpl detail Exception->"+e.getMessage());
		}
		return board;
	}
	
	//삭제기능
		@Override
		public int Delete(int b_num) {
			System.out.println("BoardDaoImpl Delete Start...");
			int result = 0;
			try {
				result  = session.delete("delete",b_num);
				System.out.println("BoardDaoImpl delete result->"+result);
			} catch (Exception e) {
				System.out.println("BoardDaoImpl delete Exception->"+e.getMessage());
			}
			return result;
		}

		@Override
		public int update(Board board) {
			System.out.println("BoardDaoImpl update start..");
			int kkk = 0;
			try {
				kkk  = session.update("dyBoardUpdate",board);
			} catch (Exception e) {
				System.out.println("BoardDaoImpl update Exception->"+e.getMessage());
			}
			return kkk;
		}

		@Override
		public List<Board> listManager() {
			// TODO Auto-generated method stub
			return null;
		}

		
	
}
