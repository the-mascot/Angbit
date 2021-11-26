package com.oracle.Angbit.model.status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoinCoinInfo {
	
//	Coin table
	private String coincode;
	private String id;
	private Float coin_amt;
	private int avg_price;
	private int tot_price;
	
//	CoinInfo table
	private String coinname;
	
	
}
