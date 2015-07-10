package com.armon.test.quartz;

import java.util.List;
import java.util.Set;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.JobListener;

public class MyJobListener implements JobListener {
	@Override
	public String getName() {
		return MyJobListener.class.getSimpleName();
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		// TODO Auto-generated method stub
		System.out.println("jobExecutionVetoed");
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		// TODO Auto-generated method stub
//		System.out.println("jobToBeExecuted");
		List<JobKey> needDependencies = TestQuartz.jobMap.get(context.
				getJobDetail().getKey()).getDependencies();
		Set<JobKey> readyDependencies = TestQuartz.jobStatusMap.get(context.
				getJobDetail().getKey()).getReadyDependencies();
		for (JobKey key : needDependencies) {
			if (!readyDependencies.contains(key)) {
//				System.out.println(key.toString() + " is not ready!");
				throw new RuntimeException(key.toString() + " is not ready!");
			}
		}
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException jobException) {
		// TODO Auto-generated method stub
//		System.out.println("jobWasExecuted");
		TestQuartz.jobStatusMap.get(context.
				getJobDetail().getKey()).clearDependencies();
	}
}
