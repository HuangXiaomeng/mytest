package com.armon.test.quartz;

import java.util.HashSet;
import java.util.Set;

import org.quartz.JobKey;

public class MyJobStatus {
	private String jobid;
	private Status status;
	private Set<JobKey> readyDependencies = new HashSet<JobKey>();
	
	/**
	 * WAIT: Job没有开始，或者Job依赖的任务没有全部完成
	 * RUNNING: Job正在运行中
	 * SUCCESS: Job运行成功(瞬间状态)
	 * FAILED: Job运行失败(瞬间状态)
	 *
	 */
	public enum Status{
		WAIT("wait"),RUNNING("running"),SUCCESS("success"),FAILED("failed");
		
		private final String id;
		private Status(String id){
			this.id=id;
		}
		@Override
		public String toString() {
			return id;
		}
		public static Status parser(String v){
			for(Status s:Status.values()){
				if(s.id.equalsIgnoreCase(v)){
					return s;
				}
			}
			return null;
		}
		public String getId() {
			return id;
		}
	}
	
	MyJobStatus(String jobid) {
		this(jobid, Status.WAIT);
	}
	
	MyJobStatus(String jobid, Status status) {
		this.jobid = jobid;
		this.status =  status;
	}
	
	public String getJobid() {
		return jobid;
	}
	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Set<JobKey> getReadyDependencies() {
		return readyDependencies;
	}
	public void setReadyDependencies(Set<JobKey> readyDependencies) {
		this.readyDependencies = readyDependencies;
	}
	public void addDependency(JobKey dependency) {
		readyDependencies.add(dependency);
	}
	public void removeDependency(JobKey dependency) {
		readyDependencies.remove(dependency);
	}
	public void clearDependencies() {
		readyDependencies.clear();
	}
	
}
