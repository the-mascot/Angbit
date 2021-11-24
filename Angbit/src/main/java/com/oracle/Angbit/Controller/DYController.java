package com.oracle.Angbit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.oracle.Angbit.model.common.Board;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.service.board.BoardService;

public class DYController {

	@Autowired
	private BoardService bs;
	
	@GetMapping("list")
	public String getList(Board board,MemberInfo memberInfo,Model model) {
		System.out.println("AngController getList Start...");
		List<Board>boardList = bs.BoardList(); 
		List<MemberInfo>memberList = bs.MemberList();
				
		model.addAttribute("bdList",boardList);
		model.addAttribute("mbList",memberList);
		return "/board/board";
	}
}
