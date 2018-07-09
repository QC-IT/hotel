package com.hotel.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;

import com.hotel.service.QuartzService;

public class EmailQuartz implements Job {
	@Autowired
	private QuartzService quartzService;
	{
		BeanFactory beanFactory=ContextLoader.getCurrentWebApplicationContext();
		System.out.println(beanFactory);
	}
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
	quartzService.autoSendLogEmail();
	}

}
