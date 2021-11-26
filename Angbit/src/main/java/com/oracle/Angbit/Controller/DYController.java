package com.oracle.Angbit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.oracle.Angbit.model.common.Board;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.service.board.BoardService;
import com.oracle.Angbit.service.board.Paging;

@Controller
public class DYController {

	@Autowired
	private BoardService bs;

	//보드값가져오기
	@GetMapping("board_list")
	public String getList(Board board, MemberInfo memberInfo, Model model) {
		System.out.println("DYController getList Start...");
		List<Board> boardList = bs.BoardList();
		List<MemberInfo> memberList = bs.MemberList();
		System.out.println("memberList.get(0).getId() ->"+memberList.get(0).getId());
		model.addAttribute("boardList", boardList);
		model.addAttribute("memberList", memberList);
		return "board/coinBoard";
	}
	
	//삭제
	@GetMapping("board_delete")
	public String delete(int empno, Model model) {
		System.out.println("DYController Start delete...");
		int result = bs.Delete(empno);
		return "empList";
	}

	  //페이징
	  @GetMapping("board_memlist") 
	  public String MemList(MemberInfo memberInfo ,String currentPage, Model model) {
		  
	  System.out.println("DYController Start memlist..." );   int total = bs.total();
	  System.out.println("DYController total=>" + total);     Paging pg = new Paging(total, currentPage);
	
	  memberInfo.setStart(pg.getStart()); // 1 
	  memberInfo.setEnd(pg.getEnd()); //10 
	  System.out.println("DYController 	MemList start......"); 
	  List<MemberInfo> pgMemberList = bs.pgMemberList(memberInfo);
	  
	  model.addAttribute("total", total); 
	  model.addAttribute("pgMemberList", pgMemberList);
	  model.addAttribute("pg", pg);
	  
	  return "memlist"; 
	  }
	

}
