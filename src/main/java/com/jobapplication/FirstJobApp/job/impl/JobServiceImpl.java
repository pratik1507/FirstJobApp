package com.jobapplication.FirstJobApp.job.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jobapplication.FirstJobApp.job.Job;
import com.jobapplication.FirstJobApp.job.JobRepository;
import com.jobapplication.FirstJobApp.job.JobService;

@Service
public class JobServiceImpl implements JobService{

	//private List<Job> jobs=new ArrayList<>();
	JobRepository jobRepository;
	
	
	public JobServiceImpl(JobRepository jobRepository) {
		super();
		this.jobRepository = jobRepository;
	}

	//private Long nextId=1L;
	
	@Override
	public List<Job> finAll() {
		return jobRepository.findAll();
	}

	@Override
	public void createJob(Job job) {
		//job.setId(nextId++);
		jobRepository.save(job);
	}

	@Override
	public Job getJobById(Long id) {
		/*
		 * for(Job job:jobs) { if(job.getId().equals(id)) { return job; } } return null;
		 */
		
		return jobRepository.findById(id).orElse(null);
	}

	@Override
	public boolean deleteJobById(Long id) {
		/*
		 * Iterator<Job> iterator = jobs.iterator(); while(iterator.hasNext()) { Job job
		 * = iterator.next(); if(job.getId().equals(id)) { iterator.remove(); return
		 * true; } } return false;
		 */
		
		try {
			jobRepository.deleteById(id);
			return true;
			}catch (Exception e) {
				// TODO: handle exception
				return false;
			}
	}

	@Override
	public boolean updateJob(Long id, Job updatedJob) {
		// TODO Auto-generated method stub
		/*
		 * Iterator<Job> iterator = jobs.iterator(); while(iterator.hasNext()) { Job
		 * job=iterator.next(); if(job.getId().equals(id)) {
		 * job.setTitle(updatedJob.getTitle());
		 * job.setDescription(updatedJob.getDescription());
		 * job.setMinSalary(updatedJob.getMinSalary());
		 * job.setMaxSalary(updatedJob.getMaxSalary());
		 * job.setLocation(updatedJob.getLocation()); return true; } } return false;
		 */
		
		Optional<Job> jobOptional = jobRepository.findById(id);
		if(jobOptional.isPresent()) {
			Job job=jobOptional.get();
			job.setTitle(updatedJob.getTitle());
			job.setDescription(updatedJob.getDescription());
			job.setMinSalary(updatedJob.getMinSalary());
			job.setMaxSalary(updatedJob.getMaxSalary());
			job.setLocation(updatedJob.getLocation());
			jobRepository.save(job);
			return true;
		}
		return false;
	}

}
