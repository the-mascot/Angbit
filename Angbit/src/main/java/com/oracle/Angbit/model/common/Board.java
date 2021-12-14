package com.oracle.Angbit.model.common;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class Board {
	
	private int rn;
	private int b_num;
	private String id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date uploaddate;
	
	private String title;
	private String content;
	private String imagepath;
	private int views;
	private int ref;
	private int re_level;
	private int re_step;
	
	private String nickname;
	
	// 조회용
	private String search;   private String keyword;
	private String pageNum;  
	private int start; 		 private int end;
}
