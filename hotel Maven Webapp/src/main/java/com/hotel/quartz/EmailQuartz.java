package com.hotel.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.ContextLoader;

import com.hotel.service.QuartzService;

public class EmailQuartz implements Job {

	private QuartzService quartzService;
	{
		BeanFactory beanFactory=ContextLoader.getCurrentWebApplicationContext();
	quartzService=beanFactory.getBean(QuartzService.class);
	}
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
	quartzService.autoSendLogEmail();
	}

}
