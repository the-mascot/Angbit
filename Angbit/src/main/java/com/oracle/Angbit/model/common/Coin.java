package com.oracle.Angbit.model.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coin {
	
	private String coincode;
	private String id;
	private int coin_amt;
	private int avg_price;
	private int tot_price;
	
}
