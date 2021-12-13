package com.oracle.Angbit.configuration;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.Angbit.service.invest.InvestService;
import com.oracle.Angbit.service.rank.RankUpdateQuartzService;
import com.oracle.Angbit.service.invest.ChkTradeQuartzService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class QuartzConfig {
	
	@Autowired
	private Scheduler scheduler;
	@Autowired
	private InvestService ivs;
	
	@PostConstruct
	public void start() {
		log.info("JobController start invoked");
		try {
			scheduler.scheduleJob(chkTradeJobDetail(), buildCronJobtrigger("0 0/1 * * * ?"));
			scheduler.scheduleJob(rankUpdateJobDetail(), buildCronJobtrigger("0 0 0 * * ?"));
		} catch (SchedulerException e) {
			System.out.println("QuartzConfig start() Exception->"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public Trigger buildCronJobtrigger(String scheduleExp) {
		return TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
	}
	
	public JobDetail chkTradeJobDetail() {
		
		return JobBuilder.newJob().ofType(ChkTradeQuartzService.class).storeDurably().withIdentity("ChkTradeJob").withDescription("Invoke ChkTrade Job service...").build();
	}
	
	public JobDetail rankUpdateJobDetail() {
		
		return JobBuilder.newJob().ofType(RankUpdateQuartzService.class).storeDurably().withIdentity("RankJob").withDescription("Invoke RankUpdate Job service...").build();
	}
}
