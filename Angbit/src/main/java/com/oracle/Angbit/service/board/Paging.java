package com.oracle.Angbit.service.board;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class Paging {
	private int currentPage = 1;	private int rowPage   = 10;//한페이지 게시글수
	private int pageBlock = 10;	//밑에 나오는 블록갯수인듯	
	private int start;				private int end;           //게시글   
	private int startPage;			private int endPage;
	private int total;				private int totalPage;

	public boolean backChk;		//startPage  > pageBlock
	public boolean frontChk;		//pg.endPage < pg.totalPage
	private int blockChk;		//pg.startPage ~ pg.endPage
	
	public Paging(int total, String currentPage1) {
		this.total = total;
		if (currentPage1 == null) {
			this.currentPage = 1;
		} else {
			this.currentPage = Integer.parseInt(currentPage1);			
		}
		//밑에 페이지 목록
		start = (currentPage - 1) * rowPage + 1;  				 // 페이지블록 첫번째 게시글번호 
		end   = start + rowPage - 1;              				 // 페이지블록 마지막 게시글번호 
		totalPage = (int) Math.ceil((double)total / rowPage);    // 시작시 2, 소수점 이하 반올림,  글기반 블록수 - 게시글40이면  블록4개 생성
		startPage = currentPage - (currentPage - 1) % pageBlock; // 시작시 1 그다음 11, 1~10블록까지는 1~10블록가지만 노출, 11번째 블록가면 11~20블록이 보이겠지?
		endPage = startPage + pageBlock - 1;
		if (endPage > totalPage) {
			endPage = totalPage;
		}
	
		
		this.blockChk = (endPage-startPage)+1;
		
		if(startPage-pageBlock>0) {
			backChk=true;
		}else {
			backChk=false;
		}
		
		if(totalPage-endPage>0) {
			frontChk=true;
		}else {
			frontChk=false;
		}

		
	}
	
	
	/*
	 * public boolean backChk() { boolean returnChk;
	 * 
	 * if(backChk>0) { returnChk=true; }else { returnChk=false; }
	 * 
	 * return returnChk; } public boolean frontChk() { boolean returnChk;
	 * 
	 * if(frontChk>0) { returnChk=true; }else { returnChk=false; }
	 * 
	 * return returnChk; }
	 */
		
	
	
}
