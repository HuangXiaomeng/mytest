package com.armon.test.quartz;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

public class TestQuartz {
	private static Scheduler scheduler;
	public static Map<JobKey, MyJobDescriptor> jobMap = 
			new HashMap<JobKey, MyJobDescriptor>();
	public static Map<JobKey, MyJobStatus> jobStatusMap = 
			new HashMap<JobKey, MyJobStatus>();
	
	public static void main(String[] args) throws SchedulerException, ParseException, InterruptedException {
		initScheduler();
		initJob();
		scheduleJob();
	}
	
	public static void initScheduler() {
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void initJob() {
		Configuration conf = QuartzConfiguration.create();
		String groupid = conf.get("quartz.defalut.groupname", "test");
		MyJobDescriptor job1 = new MyJobDescriptor("job1", groupid, "*/5 * * * * ?");
		MyJobDescriptor job2 = new MyJobDescriptor("job2", groupid, "*/10 * * * * ?");
		MyJobDescriptor job3 = new MyJobDescriptor("job3", groupid, null);
		JobKey key1 = new JobKey(job1.getJobid(), job1.getGroupid());
		JobKey key2 = new JobKey(job2.getJobid(), job2.getGroupid());
		JobKey key3 = new JobKey(job3.getJobid(), job3.getGroupid());
		job3.setDependencies(Arrays.asList(key1, key2));
		jobMap.put(key1, job1);
		jobMap.put(key2, job2);
		jobMap.put(key3, job3);
		
		MyJobStatus status1 = new MyJobStatus("job1");
		MyJobStatus status2 = new MyJobStatus("job2");
		MyJobStatus status3 = new MyJobStatus("job3");
		jobStatusMap.put(key1, status1);
		jobStatusMap.put(key2, status2);
		jobStatusMap.put(key3, status3);
	}
	
	public static void scheduleJob() throws ParseException, SchedulerException {
		MyJobChainingJobListener chainListener = new MyJobChainingJobListener("workflow1"); 
		
		for (Map.Entry<JobKey, MyJobDescriptor> entry : jobMap.entrySet()) {
			MyJobDescriptor jobDescriptor = entry.getValue();
			Trigger trigger = null;
			if (jobDescriptor.getCronException() != null) {
//				trigger = new CronTriggerImpl(jobDescriptor.getJobid(), 
//						jobDescriptor.getGroupid(), jobDescriptor.getCronException());
				trigger = TriggerBuilder.newTrigger()
						.withIdentity(jobDescriptor.getJobid(), jobDescriptor.getGroupid())
						.withSchedule(CronScheduleBuilder.cronSchedule(jobDescriptor.getCronException()))
						.startNow()
						.build();
			} else {
//				trigger = new SimpleTriggerImpl(jobDescriptor.getJobid(), 
//						jobDescriptor.getGroupid());
				trigger = TriggerBuilder.newTrigger()
						.withIdentity(jobDescriptor.getJobid(), jobDescriptor.getGroupid())
						.startNow()
						.build();
			}
//			JobDetail job = new JobDetailImpl(jobDescriptor.getJobid(), 
//					jobDescriptor.getGroupid(), MyJob.class);
			JobDetail job = JobBuilder.newJob(MyJob.class)
					.withIdentity(jobDescriptor.getJobid(), jobDescriptor.getGroupid())
					.build();
			job.getJobDataMap().put("jobname", jobDescriptor.getJobid());
			if (jobDescriptor.getDependencies() != null) {
				((JobDetailImpl) job).setDurability(true);
				scheduler.addJob(job, true);
				List<JobKey> dependencies = jobDescriptor.getDependencies();
				for (JobKey jobkey : dependencies) {
					chainListener.addJobChainLink(jobkey, job.getKey()); 
				}
				scheduler.getListenerManager().addJobListener(
						new MyJobListener(), KeyMatcher.keyEquals(job.getKey()));
			} else {
				((JobDetailImpl) job).setRequestsRecovery(true);
				scheduler.scheduleJob(job, trigger);
			}
		}
		scheduler.getListenerManager().addJobListener(chainListener);
	}
}
