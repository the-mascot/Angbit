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
	
	@Override
	public int arrange() {
		System.out.println("BoardDaoImpl pagingBd Start ..." );
		int arrange = 0;
		
		try {
			arrange  = session.update("dyArrange");
		} catch (Exception e) {
			System.out.println("BoardDaoImpl arrange Exception->"+e.getMessage());
		}
		return arrange;
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

	

	
	//detail,updateForm
	@Override
	public Board detailBoard(int ref) {
		System.out.println("BoardDaoImpl detail start..");
		Board board = new Board();
		try {
			//                       mapper ID   ,    Parameter
			board = session.selectOne("dyBoardSelOne",    ref);
			
			System.out.println("BoardDaoImpl detail getTitle->"+board.getB_num());
		} catch (Exception e) {
			System.out.println("BoardDaoImpl detail Exception->"+e.getMessage());
		}
		return board;
	}
	
		//삭제기능
		@Override
		public int Delete(int ref) {
			System.out.println("BoardDaoImpl Delete Start...");
			int result = 0;
			try {
				result  = session.delete("delete",ref);
				System.out.println("BoardDaoImpl delete result->"+result);
			} catch (Exception e) {
				System.out.println("BoardDaoImpl delete Exception->"+e.getMessage());
			}
			return result;
		}
		
		@Override
		public int replyDelete(int ref) {
			System.out.println("BoardDaoImpl Delete Start...");
			int result = 0;
			try {
				result  = session.delete("dyReplyDelete",ref);
				System.out.println("BoardDaoImpl replyDelete result->"+result);
			} catch (Exception e) {
				System.out.println("BoardDaoImpl replyDelete Exception->"+e.getMessage());
			}
			return result;
		}
		
		//수정기능
		@Override
		public int update(Board board) {
			System.out.println("BoardDaoImpl update start..");
			int result = 0;
			System.out.println(board.getB_num());
			System.out.println(board.getContent());
			System.out.println(board.getTitle());
			try {
				result  = session.update("dyBoardUpdate", board);
			} catch (Exception e) {
				System.out.println("BoardDaoImpl update Exception->"+e.getMessage());
			}
			return result;
		}

		/*
		 * @Override public Board writeBoard(int b_num) { // TODO Auto-generated method
		 * stub System.out.println("BoardDaoImpl writeBoard start..."); Board
		 * board=session.selectOne("writeBoard",b_num); return board; }
		 */
		
		//write
		@Override
		public int insert(Board board) {
			System.out.println("BoardDaoImpl insert start..");
			int result = 0;
			try {
				result  = session.insert("dyBoardInsert",board);  //삽입된 행의 갯수를 반환
			} catch (Exception e) {
				System.out.println("BoardDaoImpl insert Exception->"+e.getMessage());
			}
			return result;
		}
		//조회수
		@Override
		public int viewCnt(int ref) {
			System.out.println("BoardDaoImpl viewCnt start..");
			int result = 0;
			try {
				result  = session.insert("dyViewCnt",ref);  //삽입된 행의 갯수를 반환
			} catch (Exception e) {
				System.out.println("BoardDaoImpl viewCnt Exception->"+e.getMessage());
			}
			return result;
		}


		//댓글부분
		@Override
		public int instResult(Board board) {
			System.out.println("BoardDaoImpl instResult start..");
			int result = 0;
			try {
				result  = session.insert("dyReplyResult",board);  //삽입된 행의 갯수를 반환
			} catch (Exception e) {
				System.out.println("BoardDaoImpl instResult Exception->"+e.getMessage());
			}
			return result;
		}

		@Override
		public Board levone(int ref) {
			System.out.println("BoardDaoImpl levone start..");	
			Board board = new Board();
			try {
				//                       mapper ID   
				board = session.selectOne("dyLevOne");
				
				System.out.println("BoardDaoImpl detail getTitle->"+board.getB_num());
			} catch (Exception e) {
				System.out.println("BoardDaoImpl detail Exception->"+e.getMessage());
			}
			
			return board;
		}




	




		


		

		
		
	
}
