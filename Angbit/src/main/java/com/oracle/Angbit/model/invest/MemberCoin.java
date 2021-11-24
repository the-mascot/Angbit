package com.oracle.Angbit.model.invest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCoin {
	
	private String coincode;
	private String id;
	private Float coin_amt;
	private int avg_price;
	private int tot_price;
	private String password;
	private String nickname;
	private int krw;
	
}
