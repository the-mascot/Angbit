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
import com.oracle.Angbit.service.invest.QuartzService;

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
			scheduler.scheduleJob(buildJobDetail(), buildCronJobtrigger("0 0/1 * * * ?"));
		} catch (SchedulerException e) {
			System.out.println("QuartzConfig start() Exception->"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public Trigger buildCronJobtrigger(String scheduleExp) {
		return TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
	}
	
	public JobDetail buildJobDetail() {
		
		return JobBuilder.newJob().ofType(QuartzService.class).storeDurably().withIdentity("QuartzJob").withDescription("Invoke QuartzService Job service...").build();
	}
	
}
