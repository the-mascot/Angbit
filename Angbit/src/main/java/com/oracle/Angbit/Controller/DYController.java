package com.oracle.Angbit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.Angbit.model.common.Board;

import com.oracle.Angbit.service.board.BoardService;
import com.oracle.Angbit.service.board.Paging;


@Controller
public class DYController {

	@Autowired
	private BoardService bs;

	//보드값가져오기
	@GetMapping("board_list")
	public String getList(Board board, Model model) {
		System.out.println("DYController getList Start...");
		List<Board> boardList = bs.BoardList();
		
		model.addAttribute("boardList", boardList);
		
		return "board/coinBoard";
	}
	
	  //페이징
	  @GetMapping("board_bdPaging") 
	  public String bdPaging(Board board ,String currentPage, Model model) {
		  
	  System.out.println("DYController Start bdPaging..." );   
	  int total = bs.total(); 												//서비스로 넘겨서 작업
	  System.out.println("DYController total=>" + total);     
	  Paging pg = new Paging(total, currentPage);
	
	  board.setStart(pg.getStart()); // 1 
	  board.setEnd(pg.getEnd()); //10 
	  System.out.println("DYController bdPaging start......"); 
	  List<Board> pagingBd = bs.pagingBd(board);
	  
	  model.addAttribute("total", total); 
	  model.addAttribute("pagingBd", pagingBd);
	  model.addAttribute("pg", pg);
	  
	  return "board/coinBoard"; 
	  }
	
	  //게시판 상세보기
	  @GetMapping(value="board_detailBoard")
		public String detailBoard(int b_num, Model model) {//html에서 넘겨받을 변수의 타입을 적는건가?
		  
			System.out.println("DYController Start detail..." );
			Board board = bs.detailBoard(b_num); // service->dao->xml->dao->service->여기
			model.addAttribute("board",board);
			
			return "board/detailBoard";

		}
	  
	//수정 폼에 자료를 가져와 띄우는거
		@GetMapping(value="board_updateForm")
		public String updateForm(String nickname, int b_num, Model model) {
			System.out.println("DYController Start updateForm..." );
			Board board = bs.detailBoard(b_num); 
			
			
			model.addAttribute("board",board);
			
			
			return "board/updateForm";
		}
	//실질적인 데이터베이스 수정 작업	
		@PostMapping(value="update")
	    public String update(Board board, Model model) {
			int k = bs.update(board);
			System.out.println("bs.update(board) k-->"+k);
			model.addAttribute("kkk",k);               		// Test Controller간 Data 전달
			model.addAttribute("kk3","Message Test");   	// Test Controller간 Data 전달
			
			return "forward:getList";   
	    }
		
		
	//작성폼
		@GetMapping(value="board_writeForm")
		public String writeForm(Model model) {
			List<Board> list = bs.listManager();
			System.out.println("DYController writeForm list.size->"+list.size());
			model.addAttribute("bdMngList",list);   // board Manager List
			
			return "writeForm";
		}
//		@PostMapping(value="write")
//		public String write(Board board, Model model) {
//			System.out.println("DYController Start write..." );
//			//System.out.println("emp.getHiredate->"+emp.getHiredate());
//			// Service, Dao , Mapper명 까지 -> insert
//			String returnStr ="";
//			int result = bs.insert(board);
//			if (result > 0) returnStr = "redirect:empList";
//			else {
//				model.addAttribute("msg","입력 실패 확인해 보세요");
//				returnStr = "forward:writeForm";
//			}
//		    
//			return returnStr;
//		}	
		

				//삭제
				@PostMapping("delete")
				public String delete(int b_num, Model model) {
					System.out.println("DYController Start delete...");
					int result = bs.Delete(b_num);
					
					model.addAttribute("result", result);
					
					return "redirect:getList"; 
				}
}
