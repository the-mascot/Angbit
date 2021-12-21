package com.oracle.Angbit.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;


@Data


public class Board {
	
	// private int rn;
	
	// @Column(name="b_num")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
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
	
	@Transient
	private String nickname;
	

	
	// 조회용
//	private String search;   private String keyword;
//	private String pageNum;  
//	private int start; 		 private int end;
}

