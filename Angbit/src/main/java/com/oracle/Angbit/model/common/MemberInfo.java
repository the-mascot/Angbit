package com.oracle.Angbit.model.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfo {

	private String id;
	private String password;
	private String nickname;
	private int krw;
	
	
	// 조회용
		private String search;   private String keyword;
		private String pageNum;  
		private int start; 		 private int end;
}
