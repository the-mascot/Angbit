package com.oracle.Angbit.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oracle.Angbit.domain.Board;
import com.oracle.Angbit.service.board.BoardJpaService;

@Controller
public class BoardJpaController {

	/*
	 * private final BoardJpaService boardJpaService; // di 로 활용하기때문에 서비스 이름을 변수로
	 * 선언해두고
	 */
	/*
	 * @Autowired //인젝션 public BoardJpaController(BoardJpaService boardJpaService) {
	 * this.boardJpaService = boardJpaService; }
	 * 
	 * @GetMapping(value = "jpa_list") public String listBoard(@RequestParam String
	 * schword,HttpServletRequest request) {
	 * System.out.println("BoardJpaController listBoard start.. "); List<Board>
	 * jpaBoardList = boardJpaService.getListAllBoard(schword); System.out.
	 * println("BoardJpaController listBoard jpaBoardList.get(0).getTitle())->"
	 * +jpaBoardList.get(0).getTitle());
	 * 
	 * model.addAttribute("boards", jpaBoardList); request.setAttribute("boards",
	 * jpaBoardList);
	 * 
	 * return "jpa_schlist"; }
	 */

}
