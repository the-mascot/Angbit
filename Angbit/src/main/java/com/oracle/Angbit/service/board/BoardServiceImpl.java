package com.oracle.Angbit.service.board;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.Angbit.dao.board.BoardDao;
import com.oracle.Angbit.model.common.Board;


@Transactional
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao bdao;

	
		@Override
		public List<Board> pagingBd(Board board) {
			List<Board> pagingbd = null;
			System.out.println("BoardServiceImpl pagingBd Start..." );
			pagingbd = bdao.pagingBd(board);
			System.out.println("BoardServiceImpl pagingBd pagingbd.size()->" +pagingbd.size());
			
			return pagingbd;
		}
	
	
	  
	 

	@Override
	public int total() {
		System.out.println("BoardServiceImpl Start total..." );
		int totCnt = bdao.total();
		System.out.println("BoardServiceImpl total totCnt->"+totCnt );
		return totCnt;
	}


	

	
	//detail,boardupdateForm
	@Override
	public Board detailBoard(Board bd) {
		System.out.println("BoardServiceImpl detailBoard ...");
		Board board = null;
		
		board = bdao.detailBoard(bd); //dao에서 리턴받은값 여기로
		
		return board;//리턴 컨트롤러로
	}
	
	//update
	@Override
	public int update(Board board) {
		System.out.println("BoardServiceImpl update start...");
		int result = bdao.update(board);
		return result;
	}
	//replyUpdateform
	@Override
	public Board detailReply(Board board) {
		System.out.println("BoardServiceImpl detailReply ...");
		Board bd = null;
		bd = bdao.detailReply(board);
		
		return bd;
	}
	//reply update db
	@Override
	public int replyUpdate(Board board) {
		System.out.println("BoardServiceImpl replyUpdate ...");
		int result = bdao.replyUpdate(board);
		System.out.println("service Impl result -> "+result);
		return result;
	}
	
	
	
	
	
	//writeForm
	@Override
	public int insert(Board board) {
		System.out.println("BoardServiceImpl insert start...");
		int result=0;
		result = bdao.insert(board);
		
		return result;
	}
	//detail
	@Override
	public int Delete(int ref) {
		int result = 0;
		System.out.println("BoardServiceImpl delete Start...");
		result = bdao.Delete(ref);
		return result;
	}
	
	@Override
	public int replyDelete(Board board) {
		int result = 0;
		System.out.println("BoardServiceImpl delete Start...");
		result = bdao.replyDelete(board);
		return result;
	}
	
	
	
	//coinBd,detail
	@Override
	public int viewCnt(int ref) { //controller 에서 return한 값 여기로.
		System.out.println("BoardServiceImpl viewCnt start...");
		int result = bdao.viewCnt(ref);
		return result;
	}

	
	
	
	//댓글정보 저장
	@Override
	public int instResult(Board board) {
		System.out.println("BoardServiceImpl instResult start...");
		int result = bdao.instResult(board);
		
		return result;
	}
	//댓글 출력
	@Override
	public List<Board>levone(Board board) {
		System.out.println("BoardServiceImpl levone start...");
		List<Board> levone = bdao.levone(board);
		
		return levone;
	}





	@Override
	public Board scdetailBd(Board bd) {
		System.out.println("BoardServiceImpl scdetailBd ...");
		Board board = null;
		
		board = bdao.scdetailBd(bd); //dao에서 리턴받은값 여기로
		
		return board;//리턴 컨트롤러로
	}





	

	




	




	

	
	
	

	
	
	
	
	
}
