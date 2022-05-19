package com.oracle.Angbit.dao.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/*
	 * @Override public int schTotal() { int tot = 0;
	 * System.out.println("BoardDaoImpl schTotal Start ..." ); try { tot =
	 * session.selectOne("dySchTotal"); } catch (Exception e) {
	 * System.out.println("BoardDaoImpl schTotal Exception->"+e.getMessage()); }
	 * 
	 * return tot; }
	 */


	
	//detail,updateForm
	@Override
	public Board detailBoard(Board bd) {
		System.out.println("BoardDaoImpl detailBoard start..");
		Board board = null;
		int ref = bd.getRef();
		System.out.println("detailBoard bd.getRef->"+ref);
		
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
		public int replyDelete(Board board) {
			System.out.println("BoardDaoImpl Delete Start...");
			int result = 0;
			try {
				result  = session.delete("dyReplyDelete",board);
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
			System.out.println("update bnum ->"+board.getB_num());
			System.out.println("update content ->"+board.getContent());
			System.out.println("update title ->"+board.getTitle());
			try {
				result  = session.update("dyBoardUpdate", board);
			} catch (Exception e) {
				System.out.println("BoardDaoImpl update Exception->"+e.getMessage());
			}
			return result;
		}

		@Override
		public Board detailReply(Board board) {
			System.out.println("BoardDaoImpl detailReply start..");
			Board bd = null;
			try {
				//                       mapper ID   ,    Parameter
				bd = session.selectOne("dyReplySelOne",   board);
				
				System.out.println("BoardDaoImpl detailReply getRef->"+board.getRef());
			} catch (Exception e) {
				System.out.println("BoardDaoImpl detailReply Exception->"+e.getMessage());
			}
			return bd;
		}
		
		//reply update db
		@Override
		public int replyUpdate(Board board) {
			System.out.println("BoardDaoImpl replyUpdate start..");
			int result = 0;
			System.out.println(board.getRef());
			System.out.println(board.getContent());

			try {
				result  = session.update("dyReplyUpdate", board);
				System.out.println("업데이트 result -> "+result);
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
		public List<Board> levone(Board board) {
			System.out.println("BoardDaoImpl levone start..");	
			List <Board> levone = null;
			int ref = board.getRef();
			try {
				//                       mapper ID   
				levone = session.selectList("dyLevOne", ref);
				
//			
			} catch (Exception e) {
				System.out.println("BoardDaoImpl levone Exception->"+e.getMessage());
			}
			
			return levone;
		}


		@Override
		public Board scdetailBd(Board bd) {
			System.out.println("BoardDaoImpl detail start..");
			Board board = null;
			
			try {
				//                       mapper ID   ,    Parameter
				board = session.selectOne("dyBoardForReply",   bd);
				
				System.out.println("BoardDaoImpl detail getTitle->"+board.getB_num());
			} catch (Exception e) {
				System.out.println("BoardDaoImpl detail Exception->"+e.getMessage());
			}
			return board;
		}

	@Override
	public List<Board> testBoardList(int startRow, int endRow) {
			Map vo = new HashMap();
			vo.put("startRow", startRow);
			vo.put("endRow", endRow);
			List<Board> list = session.selectList("testBoardList", vo);
		return list;
	}

	@Override
	public Board testBoardContent(int b_num) {
		return session.selectOne("testBoardContent", b_num);
	}


	@Override
	public List<Board> testBoardContentComm(int b_num) {
		return session.selectList("testBoardContentComm", b_num);
	}

	@Override
	public List<Board> schBoard(String schword) {
		System.out.println("검색값 : "+schword);
		List<Board> list = session.selectList("schBoard", schword);
		return list;
	}

	@Override
	public List<Board> testBoardSearch(String keyWord, String condition) {
		Map vo = new HashMap();
		vo.put("keyWord", keyWord);
		vo.put("condition", condition);
		List<Board> list = session.selectList("testBoardSearch", vo);
		return list;
	}
}
