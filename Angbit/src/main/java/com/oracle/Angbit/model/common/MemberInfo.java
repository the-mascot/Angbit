package com.oracle.Angbit.model.common;

import lombok.Data;
import java.util.Date;


@Data
public class MemberInfo {
	
	
	private String id;
	
	private String password;
	private String nickname;
	private int krw;
	private Date joindate;
	private Date finaldate;
	private int member_stu;
	private int asset;
	
	//랭킹 페이지
	private int rn;
	private String coincode;
	private Float coin_amt;
	private String yield; // 수익률
}
