package com.oracle.Angbit.model.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MemberInfo {

	private String id;
	private String password;
	private String nickname;
	private int krw;
	private Date joindate;
	private Date finaldate;
	private int member_stu;
	
	// 조회용
		private String search;   private String keyword;
		private String pageNum;  
		private int start; 		 private int end;
}
