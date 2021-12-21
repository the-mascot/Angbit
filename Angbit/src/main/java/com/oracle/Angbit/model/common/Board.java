package com.oracle.Angbit.model.common;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
@Entity
public class Board {

	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
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
	@Transient
	private int rn;
	// 조회용
	@Transient
	private String search;   
	@Transient
	private String keyword;
	@Transient
	private String pageNum;
	@Transient
	private int start; 		 
	@Transient
	private int end;
}
