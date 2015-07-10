package com.armon.test.quartz;

import java.util.List;

import org.quartz.JobKey;

public class MyJobDescriptor {
	private String jobid;
	private String groupid;
	private List<JobKey> dependencies;
	private String cronException;
	
	MyJobDescriptor(String jobid, String groupid, String cronException) {
		this.jobid = jobid;
		this.groupid = groupid;
		this.cronException = cronException;
	}
	
	public String getJobid() {
		return jobid;
	}
	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public List<JobKey> getDependencies() {
		return dependencies;
	}
	public void setDependencies(List<JobKey> dependencies) {
		this.dependencies = dependencies;
	}
	public String getCronException() {
		return cronException;
	}
	public void setCronException(String cronException) {
		this.cronException = cronException;
	}
	
}
