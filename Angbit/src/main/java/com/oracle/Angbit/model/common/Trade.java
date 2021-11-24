package com.oracle.Angbit.model.common;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Trade {

	private int trd_num;
	private String id;
	private String coincode;
	private Date trd_date;
	private int trd_amt;
	private int trd_unit_price;
	private int trd_price;
	private int trd_div;
	private int trd_stu;
	
}
