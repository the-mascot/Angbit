package com.oracle.Angbit.service.invest;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChkTradeQuartzService implements Job {
	
	@Autowired
	private InvestService ivs;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		System.out.println("QuartzService execute start...");
		ivs.checkBuyLimits();
	}
}
