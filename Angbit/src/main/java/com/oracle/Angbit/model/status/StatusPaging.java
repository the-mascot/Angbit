package com.oracle.Angbit.model.status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusPaging {
	private int currentPage = 1;
	private int rowPage = 10;
	private int pageBlock = 10;
	private int start;
	private int end;
	private int startPage;
	private int endPage;
	private int total;
	private int totalPage;
	
	public StatusPaging(int total, String currentPage1) {
		this.total = total;
		if (currentPage1 == null) {
			this.currentPage = 1;
		} else {
			this.currentPage = Integer.parseInt(currentPage1);
		}
		start = (currentPage - 1) * rowPage +1;	// 시작 시 1 / 2페이지 시작글번호 11
		end = start + rowPage - 1;				// 시작 시 10 / 2페이지 끝글번호 20
		totalPage = (int) Math.ceil((double)total / rowPage); // 시작 시 2(total - 15/10의 올림)
		startPage = currentPage - (currentPage - 1) % pageBlock; // 시작 시 1
		endPage = startPage + pageBlock - 1;
		if(endPage > totalPage) {
			endPage = totalPage; // 끝페이지가 총페이지수를 넘지 못하게 함
		}
	}
}
