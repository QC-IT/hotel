package com.hotel.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestQuartz implements Job{
static int i;
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(i++);
		
	}

}
