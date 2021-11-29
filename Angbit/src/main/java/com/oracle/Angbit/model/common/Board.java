package com.oracle.Angbit.model.common;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
	
	private int b_num;
	private String id;
	private Date uploaddate;
	private String title;
	private String content;
	private String imagepath;
	private int views;
	private int ref;
	private int re_level;
	private int re_step;
	
	
	// 조회용
			private String search;   private String keyword;
			private String pageNum;  
			private int start; 		 private int end;
}
