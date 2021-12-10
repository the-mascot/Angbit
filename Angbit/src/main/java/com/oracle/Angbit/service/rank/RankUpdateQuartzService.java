package com.oracle.Angbit.service.rank;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oracle.Angbit.service.invest.InvestService;

@Component
public class RankUpdateQuartzService implements Job {
	
	@Autowired
	private RankService rs;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		System.out.println("QuartzService execute start...");
		rs.updateAsset();
	}
	
	
}
