package com.armon.test.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		String jobname = (String)context.getJobDetail().getJobDataMap().get("jobname");
		System.out.println("hello " + jobname);
	}
}
