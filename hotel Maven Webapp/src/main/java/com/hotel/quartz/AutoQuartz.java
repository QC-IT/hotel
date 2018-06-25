package com.hotel.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.ContextLoader;
import com.hotel.service.QuartzService;

public class AutoQuartz implements Job {
	private QuartzService quartzService;
	{
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		quartzService = wac.getBean(QuartzService.class);
	}

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		quartzService.autoComment();
	}

}
