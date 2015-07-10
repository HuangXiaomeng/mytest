package com.armon.test.quartz;

import java.util.HashMap;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.listeners.JobListenerSupport;

public class MyJobChainingJobListener extends JobListenerSupport {
	   private String name;
	    private Map<JobKey, JobKey> chainLinks;

	    /**
	     * Construct an instance with the given name.
	     *
	     * @param name the name of this instance
	     */
	    public MyJobChainingJobListener(String name) {
	        if(name == null) {
	            throw new IllegalArgumentException("Listener name cannot be null!");
	        }
	        this.name = name;
	        chainLinks = new HashMap<JobKey, JobKey>();
	    }

	    public String getName() {
	        return name;
	    }

	    /**
	     * Add a chain mapping - when the Job identified by the first key completes
	     * the job identified by the second key will be triggered.
	     *
	     * @param firstJob a JobKey with the name and group of the first job
	     * @param secondJob a JobKey with the name and group of the follow-up job
	     */
	    public void addJobChainLink(JobKey firstJob, JobKey secondJob) {

	        if(firstJob == null || secondJob == null) {
	            throw new IllegalArgumentException("Key cannot be null!");
	        }

	        if(firstJob.getName() == null || secondJob.getName() == null) {
	            throw new IllegalArgumentException("Key cannot have a null name!");
	        }

	        chainLinks.put(firstJob, secondJob);
	    }

	    @Override
	    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {

	        JobKey sj = chainLinks.get(context.getJobDetail().getKey());

	        if(sj == null) {
	            return;
	        }

	        getLog().info("Job '" + context.getJobDetail().getKey() + "' will now chain to Job '" + sj + "'");

	        try {
	             context.getScheduler().triggerJob(sj);
	        } catch(SchedulerException se) {
	            getLog().error("Error encountered during chaining to Job '" + sj + "'", se);
	        }
	        
	        MyJobStatus jobStatus = TestQuartz.jobStatusMap.get(sj);
	        jobStatus.addDependency(context.getJobDetail().getKey());
	    }
}
