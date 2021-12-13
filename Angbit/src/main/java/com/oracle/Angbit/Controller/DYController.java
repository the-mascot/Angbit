package com.oracle.Angbit.Controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.Angbit.model.common.Board;

import com.oracle.Angbit.service.board.BoardService;
import com.oracle.Angbit.service.board.Paging;


@Controller
public class DYController {

	@Autowired
	private BoardService bs;
	@Autowired
	HttpSession session;
	

	
	//보드값가져오기
	@GetMapping("board_list")
	public String getList(Board board, String currentPage, Model model, HttpServletRequest request) {
		System.out.println("DYController getList Start...");
		
		//순번 rownum과 정렬
		int result = bs.arrange();
		
		
		 int total = bs.total(); 											    				//total service
		 System.out.println("DYController total=>" + total);									//select count(*) FROM board ,  테이블 전체 행  40	
		 
		  Paging pg = new Paging(total, currentPage);											//paging(40,1)
		  
		  int[] oriNo = new int[total];							//게시물번호
		  for(int i=1; i<total+1; i++) {
			  oriNo[i-1]=total-i+1;
		  }
		  
		  board.setStart(pg.getStart()); 														// 1, board에  start 필드값 생김
		  board.setEnd(pg.getEnd()); 															//10, board에  end 필드값 생김
		  
		  System.out.println("DYController getList start......");  
		  List<Board> boardList = bs.pagingBd(board);											//paging service, board를 파라미터로 받음 start,end값 있음
		  
		  			   model.addAttribute("total", total); 										//total==40
					   model.addAttribute("boardList", boardList);								//pagingBd==1~10까지의 행수로 보드데이터를 보내줌
					   model.addAttribute("pg", pg);											//pg==	paging 객체로 DB 40개와 현재1페이지라는 정보 있음
					   model.addAttribute("oriNo", oriNo);//수정중 순번부분...
					   
					   
			System.out.println("rownum->"+board.getRownum());		  
			
			
			
					  
		return "board/coinBoard";
	}
	
	  //상세페이지	
	  @GetMapping(value="board_detailBoard")
	  public String detailBoard(Board board, Model model, HttpServletRequest request) {   
			System.out.println("DYController Start board_detailBoard..." );
			
			
			//조회수 조정 +1
			if(board.getRe_level()==0) {
				System.out.println("DYController Start viewCnt..." );
				int viewcnt = bs.viewCnt(board.getRef());							
				System.out.println("DYController viewCnt result->"+viewcnt);
			}
			
			//(조회수 증가한) 보드 정보 출력용
			Board bd = bs.detailBoard(board.getRef());      					
			
			//댓글db추가
			if(board.getRe_level()==1 && board.getContent()!=null) {
				System.out.println("DYController Start reply..." );	
			  	int result = bs.instResult(board);
			  	System.out.println("DYController reply result->"+result);
			}
		    	
			// re_level=1 인 댓글들	가져오기
		    System.out.println("DYController Start levone..." );	
			Board levone = bs.levone(board.getRef());
		    System.out.println("DYController levone->"+levone); 
			
		    //전송
			model.addAttribute("board",bd);										//상세게시글
			model.addAttribute("levone",levone);								//댓글
			  
			return "board/detailBoard";
		}
	  
		/*
		 * // viewCnt->detailboard
		 * 
		 * @RequestMapping(value="board_viewCnt") public String viewCnt(Board board ,
		 * Model model, HttpServletRequest request) {
		 * System.out.println("DYController Start viewCnt..." );
		 * 
		 * int viewcnt = bs.viewCnt(board.getRef()); //게시글 번호 가지고 조회수 증가시키기
		 * System.out.println("DYController viewCnt result->"+viewcnt); //성공시 1
		 * 
		 * session.setAttribute("sessionRef", board.getRef()); //넘겨받은 게시글 고유번호 보관
		 * 
		 * return "redirect:board_detailBoard"; }
		 */
	  
	  /*
		 * //detail 댓글로직
		 * 
		 * @GetMapping(value = "detail_reply") public String reply(Board board, Model
		 * model) { System.out.println("DYController Start reply..." ); int result =
		 * bs.instResult(board);
		 * System.out.println("DYController reply result->"+result);
		 * model.addAttribute("ref", board.getRef());
		 * 
		 * // re_level=1 인 댓글들 int ref=board.getRef(); Board levone = bs.levone(ref);
		 * System.out.println("DYController levone->"+levone);
		 * 
		 * model.addAttribute("levone",levone);
		 * 
		 * return "redirect:board_detailBoard"; }
		 */
		  
		  //게시판 상세보기  ref값만 받아서 coinboard 에서 넘어온다
	  
	  
	  
	  
	  
	  	//수정 폼에 자료를 가져와 띄우는거
		@GetMapping(value="board_updateForm")
		public String updateForm(Board board, Model model) {
			System.out.println("DYController Start updateForm..." );
			Board bd = bs.detailBoard(board.getRef()); 
			model.addAttribute("board",bd);
			
			return "board/updateForm";
		}
		//데이터베이스 수정 작업	
		@PostMapping(value="update")
	    public String update(Board board, Model model) {
			System.out.println("board.getBnum->"+board.getB_num());
			int result = bs.update(board);
			System.out.println("DYController update result->"+result);
			model.addAttribute("result", result);               		// Test Controller간 Data 전달
			
			return "redirect:board_list";  						//갱신시에 계속 수정해줘야돼서 포워드로 반복 
	    }
		

		//		댓글수정자리ajax
		@GetMapping(value="reply_updateForm")
		public String replyUpdateForm(Board board, Model model) {
			System.out.println("DYController Start updateForm..." );
			Board bd = bs.detailReply(board.getRef()); 
			
			model.addAttribute("board",bd);
			
			return "board/replyUpdateForm";
		}
		//댓글 db에 보내기 만드는중 ....@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		@PostMapping(value="reply_update")
	    public String replyUpdate(Board board, Model model) {
			System.out.println("board.getBnum->"+board.getB_num());
			int result = bs.update(board);
			System.out.println("DYController update result->"+result);
			model.addAttribute("result", result);               		// Test Controller간 Data 전달
			
			return "redirect:board_list";  						//갱신시에 계속 수정해줘야돼서 포워드로 반복 
	    }
		
		
		//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
		
		
		
		//작성폼 -  로그인시에만 할수있어 세션으로 정보받을거 쿼리문 거칠 필요 x
		@GetMapping(value="board_writeForm")
		public String writeForm(Model model) {
			System.out.println("DYController Start writeForm..." );
			
			return "board/writeForm";
		}
		
		//제목, 글내용 작성 후 글쓰기 버튼을 눌렀을 때, DB에 저장되는 컨트롤러
		@PostMapping(value="writeProcess")
		public String writeProcess(Board board, Model model, HttpServletRequest request) {
			System.out.println("DYController Start writeProcess..." );
			System.out.println("board.getTitle() -> " + board.getTitle());
			System.out.println("board.getContent() -> " + board.getContent());
			System.out.println("board.getId() -> " + board.getId());			//로그인시 
			
			String returnStr = null;
			int result = bs.insert(board);
			if (result > 0) returnStr = "redirect:board_list";
			else {
				model.addAttribute("msg"," 로그인을 해주세요.");      //지워도 될거같음   
				returnStr = "forward:board_writeForm";
			}
		    
			return returnStr;
		}
				
		// 게시글삭제
		@GetMapping(value = "delete")
		public String delete(int ref, Model model) {
			System.out.println("DYController Start delete...");
			/* ref=(Integer)session.getAttribute("sessionRef"); */
			
			int result = bs.Delete(ref);
			System.out.println("delete성공여부->"+result);
			//model.addAttribute("result", result);
			
			return "redirect:board_list";
		}
		// 댓삭제
			@GetMapping(value = "reply_delete")
			public String replyDelete(int ref, Model model) {
				System.out.println("DYController Start delete...");
				
				int result = bs.replyDelete(ref);
				System.out.println("delete성공여부->"+result);
				//model.addAttribute("result", result);
				
				return "redirect:board_detailboard";
			}
		
}
