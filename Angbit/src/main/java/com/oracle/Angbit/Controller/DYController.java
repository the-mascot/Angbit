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
		 int total = bs.total(); 											    				//total service
		 System.out.println("DYController total=>" + total);									//select count(*) FROM board ,  테이블 전체 행  40	
		
		  Paging pg = new Paging(total, currentPage);											//paging(40,1)
		  
		  board.setStart(pg.getStart()); 														// 1, board에  start 필드값 생김
		  board.setEnd(pg.getEnd()); 															//10, board에  end 필드값 생김
		  
		  System.out.println("DYController getList start......");  
		  List<Board> boardList = bs.pagingBd(board);											//paging service, board를 파라미터로 받음 start,end값 있음
		  
		  			   model.addAttribute("total", total); 										//total==40
					   model.addAttribute("boardList", boardList);								//pagingBd==1~10까지의 행수로 보드데이터를 보내줌
					   model.addAttribute("pg", pg);											//pg==	paging 객체로 DB 40개와 현재1페이지라는 정보 있음
					   
					   //String ssid = (String)session.getAttribute("sessionID");					//swcontroller loginprocess에서 가져옴 email			   
					   //if(ssid!=null) model.addAttribute("ssid", ssid);
					
					  
					   
		return "board/coinBoard";
	}
	
	
	  //게시판 상세보기
	  @GetMapping(value="board_detailBoard")
		public String detailBoard(Board levone, Model model, HttpServletRequest request) {   //html에서 넘겨받을 변수의 타입을 적는건가?
		  
			System.out.println("DYController Start detail..." );
		    
			int b_num=(Integer)session.getAttribute("sessionBnum");			   // coinboard로부터 b_num을  넘겨받아  viewCnt에서 세션화시킴, session은 그대로니 댓글로직에서 넘어와도 괜찮다.
			System.out.println("DYController detail session b_num->"+b_num);
			
			Board board = bs.detailBoard(b_num);
			
			model.addAttribute("board",board);
			
			if(b_num>0) 							 //세션변수값 지워 중복 방지, 세션은 유지
			   b_num=0;
			//session.removeAttribute("sessionID");  
			
			//댓글테이블 따로 쓰나 ?
			model.addAttribute("levone",levone);
			
			return "board/detailBoard";
		}
	  
//	  viewCnt->detailboard
	  @RequestMapping(value="board_viewCnt")
	  public String viewCnt(int b_num ,  Model model, HttpServletRequest request) {
		  System.out.println("DYController Start viewCnt..." );
		  int viewcnt = bs.viewCnt(b_num);								//게시글 번호 가지고 조회수 증가시키기
		  System.out.println("DYController viewCnt result->"+viewcnt);  //성공시 1
		  
		  session.setAttribute("sessionBnum", b_num);                   //넘겨받은 게시글번호 보관 			
		  
		  return "redirect:board_detailBoard";
	  }
	  
	  
	  	//수정 폼에 자료를 가져와 띄우는거
		@GetMapping(value="board_updateForm")
		public String updateForm(int b_num, Model model) {
			System.out.println("DYController Start updateForm..." );
			Board board = bs.detailBoard(b_num); 
			model.addAttribute("board",board);
			
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
				
		// 삭제
		@GetMapping(value = "delete")
		public String delete(int b_num, Model model) {
			System.out.println("DYController Start delete...");
			b_num=(Integer)session.getAttribute("sessionBnum");
			
			int result = bs.Delete(b_num);
			System.out.println("delete성공여부->"+result);
			//model.addAttribute("result", result);
			
			return "redirect:board_list";
		}
		
		
		
		  //detail 댓글로직
		  @GetMapping(value = "detail_reply")
		  public String reply(Board board, Model model) {
			  	System.out.println("DYController Start reply..." );
			  	int result = bs.instResult(board);
			    System.out.println("DYController reply result->"+result); 
			  	model.addAttribute("ref", board.getRef());
			    
			return "redirect:detail_levOne";
		  }
		  // re_level=1 인 댓글들을 가져올 메소드	
		  @GetMapping(value = "detail_levOne")
		  public String levOne(int ref, Model model) {
			  	System.out.println("DYController Start levOne..." );
			  	Board levone = bs.levone(ref);
			    System.out.println("DYController levOne levone->"+levone); 
			    
			    model.addAttribute("levone",levone);
			    
			return "redirect:board_detailBoard";
		  }
				
				
				
				
}
