package com.hotel.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hotel.service.QuartzService;
import com.hotel.service.impl.QuartzServiceImpl;

public class EmailQuartz implements Job {

	private QuartzService quartzService=new QuartzServiceImpl();
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
	quartzService.autoSendLogEmail();
	}

}
