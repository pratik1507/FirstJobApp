package com.jobapplication.FirstJobApp.job.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jobapplication.FirstJobApp.job.Job;
import com.jobapplication.FirstJobApp.job.JobService;

@Service
public class JobServiceImpl implements JobService{

	private List<Job> jobs=new ArrayList<>();
	private Long nextId=1L;
	
	@Override
	public List<Job> finAll() {
		return jobs;
	}

	@Override
	public void createJob(Job job) {
		job.setId(nextId++);
		jobs.add(job);
	}

	@Override
	public Job getJobById(Long id) {
		for(Job job:jobs) {
			if(job.getId().equals(id)) {
				return job;
			}
		}
		return null;
	}

	@Override
	public boolean deleteJob(Long id) {
		Iterator<Job> iterator = jobs.iterator();
		while(iterator.hasNext()) {
			Job job = iterator.next();
			if(job.getId().equals(id)) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateJob(Long id, Job updatedJob) {
		// TODO Auto-generated method stub
		Iterator<Job> iterator = jobs.iterator();
		while(iterator.hasNext()) {
			Job job=iterator.next();
			if(job.getId().equals(id)) {
				job.setTitle(updatedJob.getTitle());
				job.setDescription(updatedJob.getDescription());
				job.setMinSalary(updatedJob.getMinSalary());
				job.setMaxSalary(updatedJob.getMaxSalary());
				job.setLocation(updatedJob.getLocation());
				return true;
			}
		}
		return false;
	}

}
