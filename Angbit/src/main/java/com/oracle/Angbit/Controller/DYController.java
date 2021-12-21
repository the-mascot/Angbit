package com.oracle.Angbit.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oracle.Angbit.model.common.Board;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.service.board.BoardJpaService;
import com.oracle.Angbit.service.board.BoardService;
import com.oracle.Angbit.service.board.Paging;

@Controller
public class DYController {

	@Autowired
	private BoardService bs;
	@Autowired
	HttpSession session;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	// 검색어
	// 자료가져오기-------------------------------------------------------------------------
	private final BoardJpaService boardJpaService; // di 로 활용하기때문에 서비스 이름을 변수로 선언해두고

	// 인젝션
	@Autowired
	public DYController(BoardJpaService boardJpaService) {
		this.boardJpaService = boardJpaService;
	}

	@GetMapping(value = "jpa_list")
	public String listBoard(@RequestParam String schword, HttpServletRequest request) {
		System.out.println("BoardJpaController listBoard start.. ");
		List<Board> jpaBoardList = bs.schBoard(schword);
//		System.out.println(
//				"BoardJpaController listBoard jpaBoardList.get(0).getTitle())->");

		System.out.println("DYController schlist Start...");
		/* int totCnt = bs.schTotal(); */

		int totCnt = jpaBoardList.size();
		System.out.println("멤버 총 합" + totCnt);

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum.equals(""))
			pageNum = "1";

		String pageSize = request.getParameter("pageSize"); // 10개씩 보기 받아오기
		if (pageSize == null || pageSize.equals(""))
			pageSize = "10";

		int currentPage = Integer.parseInt(pageNum);
		int blockSize = 10;
		int startRow = (currentPage - 1) * Integer.parseInt(pageSize) + 1;
		int endRow = startRow + Integer.parseInt(pageSize) - 1;
		int startNum = totCnt - startRow + 1;
		Board bd = new Board();
		bd.setStart(startRow);
		bd.setEnd(endRow);

		int pageCnt = (int) Math.ceil((double) totCnt / Integer.parseInt(pageSize)); // 반올림
		int StartPage = (int) (currentPage - 1) / blockSize * blockSize + 1;
		int endPage = StartPage + blockSize - 1;

		if (endPage > pageCnt)
			endPage = pageCnt;

		System.out.println("startRow" + startRow);
		System.out.println("endRow" + endRow);

		request.setAttribute("totCnt", totCnt);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startNum", startNum);
		request.setAttribute("boardList", jpaBoardList);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("startPage", StartPage);
		request.setAttribute("endPage", endPage);
		

		return "board/coinBoard";
	}
	// ----------------------------------------------------------------------------------------------

	/*
	 * //검색값 전개하기
	 * 
	 * @GetMapping("jpa_schlist") public String schlist(HttpServletRequest request )
	 * { System.out.println("DYController schlist Start..."); int totCnt =
	 * bs.schTotal();
	 * 
	 * int totCnt = bdsch.size(); System.out.println("멤버 총 합"+totCnt);
	 * 
	 * String pageNum = request.getParameter("pageNum");
	 * if(pageNum==null||pageNum.equals("")) pageNum="1";
	 * 
	 * String pageSize=request.getParameter("pageSize"); // 10개씩 보기 받아오기
	 * if(pageSize==null||pageSize.equals("")) pageSize="10";
	 * 
	 * int currentPage=Integer.parseInt(pageNum); int blockSize=10; int
	 * startRow=(currentPage-1)*Integer.parseInt(pageSize)+1; int
	 * endRow=startRow+Integer.parseInt(pageSize)-1; int startNum=totCnt-startRow+1;
	 * Board bd = new Board(); bd.setStart(startRow); bd.setEnd(endRow);
	 * 
	 * 
	 * 
	 * int pageCnt=(int) Math.ceil((double)totCnt/Integer.parseInt(pageSize)); //반올림
	 * int StartPage=(int)(currentPage-1)/blockSize*blockSize+1; int
	 * endPage=StartPage+blockSize-1;
	 * 
	 * if (endPage > pageCnt) endPage = pageCnt;
	 * 
	 * System.out.println("startRow"+startRow); System.out.println("endRow"+endRow);
	 * 
	 * request.setAttribute("totCnt", totCnt); request.setAttribute("pageNum",
	 * pageNum); request.setAttribute("pageSize", pageSize);
	 * request.setAttribute("currentPage", currentPage);
	 * request.setAttribute("startNum", startNum); request.setAttribute("boardList",
	 * bdsch); request.setAttribute("blockSize", blockSize);
	 * request.setAttribute("pageCnt", pageCnt); request.setAttribute("startPage",
	 * StartPage); request.setAttribute("endPage", endPage);
	 * 
	 * 
	 * return "board/coinBoard"; }
	 */

	// 보드값가져오기
	@GetMapping("board_list")
	public String getList(HttpServletRequest request) {
		System.out.println("DYController getList Start...");

		int totCnt = bs.total(); // total service

		System.out.println("멤버 총 합" + totCnt);
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		String pageSize = request.getParameter("pageSize"); // 10개씩 보기 받아오기
		if (pageSize == null || pageSize.equals(""))
			pageSize = "10";
		// 초기 totCnt 5, currentPage 1
		int currentPage = Integer.parseInt(pageNum);
		int blockSize = 10;
		int startRow = (currentPage - 1) * Integer.parseInt(pageSize) + 1;
		int endRow = startRow + Integer.parseInt(pageSize) - 1;
		int startNum = totCnt - startRow + 1;
		Board bd = new Board();
		bd.setStart(startRow);
		bd.setEnd(endRow);

		List<Board> boardList = bs.pagingBd(bd); // paging service, board를 파라미터로 받음 start,end값 있음

		int pageCnt = (int) Math.ceil((double) totCnt / Integer.parseInt(pageSize));
		int StartPage = (int) (currentPage - 1) / blockSize * blockSize + 1;
		int endPage = StartPage + blockSize - 1;

		if (endPage > pageCnt)
			endPage = pageCnt;

		System.out.println("startRow" + startRow);
		System.out.println("endRow" + endRow);
		System.out.println("list의 사이즈" + boardList.size());

		request.setAttribute("totCnt", totCnt);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startNum", startNum);
		request.setAttribute("boardList", boardList);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("startPage", StartPage);
		request.setAttribute("endPage", endPage);

		return "board/coinBoard";
	}

	// 상세페이지
	@GetMapping(value = "board_detailBoard")
	public String detailBoard(Board board, Model model, HttpServletRequest request) {
		System.out.println("DYController Start board_detailBoard...");
		System.out.println("컨트롤러  detailBoard re_level->" + board.getRe_level());

		// 조회수 조정 +1
		if (board.getRe_level() == 0) {
			System.out.println("DYController Start viewCnt...");
			int viewcnt = bs.viewCnt(board.getRef());
			System.out.println("DYController viewCnt result->" + viewcnt);
		}

		// ref를 통해 클릭한 게시글 정보 가져옴
		System.out.println("DYController Start detailBoard bd...");
		Board bd = bs.detailBoard(board);
		System.out.println("DYController bd->" + bd);

		// 댓글db추가
		if (board.getRe_level() == 1 && board.getContent() != null) {
			System.out.println("DYController Start reply...");
			System.out.println("reply board->" + board); // 댓글값확인
			int result = bs.instResult(board); // 댓글 삽입
			System.out.println("DYController reply result->" + result);
		}

		// re_level=1 인 댓글들 가져오기
		System.out.println("DYController Start levone...");
		System.out.println("board.getRef()->" + board.getRef());
		List<Board> levone = bs.levone(board);
		System.out.println("DYController levone->" + levone);
		String nickname = (String) session.getAttribute("sessionNickName");
		System.out.println("sessionnickname->" + nickname);

		// 전송
		model.addAttribute("board", bd); // 상세게시글
		model.addAttribute("levone", levone); // 댓글배열

		return "board/detailBoard";
	}

	// 수정 폼에 자료를 가져와 띄우는거
	@GetMapping(value = "board_updateForm")
	public String updateForm(Board board, Model model) {
		System.out.println("DYController Start updateForm...");
		Board bd = bs.detailBoard(board);
		model.addAttribute("board", bd);

		return "board/updateForm";
	}

	// 데이터베이스 수정 작업
	@PostMapping(value = "update")
	public String update(Board board, Model model) {
		System.out.println("board.getBnum->" + board.getB_num());
		int result = bs.update(board);
		System.out.println("DYController update result->" + result);
		model.addAttribute("result", result); // Test Controller간 Data 전달

		return "redirect:board_list"; // 갱신시에 계속 수정해줘야돼서 포워드로 반복
	}

	// 댓글수정폼
	@GetMapping(value = "reply_updateForm")
	public String replyUpdateForm(Board board, Model model) {
		System.out.println("DYController Start replyUpdateForm...");

		Board replybd = bs.detailReply(board); // 댓글 가져올 부분replybd
		System.out.println("reply updateForm bd ->" + replybd);

		Board detailbd = bs.scdetailBd(board); // 보드 가져올 부분

		HashMap<String, Board> map = new HashMap<String, Board>();
		map.put("replybd", replybd); // 댓글창
		map.put("detailbd", detailbd); // detail창

		model.addAttribute("map", map);

		return "board/replyUpdateForm";
	}

	// 댓글 db에 수정
	@GetMapping(value = "reply_update")
	public String replyUpdate(Board board, Model model, HttpServletRequest request) {
		System.out.println("DYController replyUpdate start...");
		int result = bs.replyUpdate(board);
		System.out.println("REF? ??? ? ? ? : " + board.getRef());
		System.out.println("DYController replyUpdate result->" + result);
		request.setAttribute("b_num", board.getRef());

		return "forward:board_detailBoard";
	}

	// 작성폼 - 로그인시에만 할수있어 세션으로 정보받을거 쿼리문 거칠 필요 x
	@GetMapping(value = "board_writeForm")
	public String writeForm(Model model) {
		System.out.println("DYController Start writeForm...");

		return "board/writeForm";
	}

	// 제목, 글내용 작성 후 글쓰기 버튼을 눌렀을 때, DB에 저장되는 컨트롤러
	@GetMapping(value = "board_writeProcess")
	public String writeProcess(Board board, Model model, HttpServletRequest request) {
		System.out.println("DYController Start writeProcess...");
		System.out.println("board.getTitle() -> " + board.getTitle());
		System.out.println("board.getContent() -> " + board.getContent());
		System.out.println("board.getId() -> " + board.getId()); // 로그인시

		String returnStr = null;
		int result = bs.insert(board);
		if (result > 0)
			returnStr = "redirect:board_list";
		else {
			model.addAttribute("msg", " 로그인을 해주세요."); // 지워도 될거같음
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
		System.out.println("delete성공여부->" + result);
		// model.addAttribute("result", result);

		return "redirect:board_list";
	}

	// 댓삭제
	@GetMapping(value = "reply_delete")
	public String replyDelete(Board board, Model model, HttpServletRequest request) {
		System.out.println("DYController Start delete...");

		int result = bs.replyDelete(board); // ref 와 re_step모두 넘기기 /어떤글의 몇번째 댓글인지 알고 삭제하기위해
		System.out.println("delete성공여부->" + result);

		request.setAttribute("b_num", board.getRef());

		return "forward:board_detailBoard";
	}

}
