package com.oracle.Angbit.service.board;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class Paging {
	//게시글
	private int currentPage = 1;	private int rowPage = 10;	//한페이지 게시글 갯수
	private int start;				private int end;           	//해당 페이지 게스글의 첫수 ,끝수   
	//블록
	private int pageBlock = 10;									//밑에 나오는 블록갯수한계	
	private int startPage;			private int endPage;		//1,10  11,20  ~
	private int total;				private int totalPage;      //count all , 글갯수기반 블록수

	
	
	public Paging(int total, String currentPage1) {
		this.total = total;
		if (currentPage1 == null) {
			this.currentPage = 1;
		} else {
			this.currentPage = Integer.parseInt(currentPage1);			
		}
		//밑에 페이지 목록
		start = (currentPage - 1) * rowPage + 1;  				 // 페이지블록 첫번째 게시글번호   1page 1번게시글
		end   = start + rowPage - 1;              				 // 페이지블록 마지막 게시글번호   1page 10번게시글
		totalPage = (int) Math.ceil((double)total / rowPage);    // 소수점 이하 반올림,  글기반 블록수 - 게시글40이면  블록4개 생성
		
		startPage = currentPage - (currentPage - 1) % pageBlock; // 시작시 1~10 그다음 11~20, 11번째 블록가면 11~20블록
		endPage = startPage + pageBlock - 1;
		
		
		if (endPage > totalPage) {
			endPage = totalPage;
		}
	
		
		

		
	}
	
	
		
	
	
}
