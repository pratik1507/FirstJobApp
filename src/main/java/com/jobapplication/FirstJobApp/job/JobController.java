package com.jobapplication.FirstJobApp.job;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/jobs") // we can define the base url like this 
public class JobController {
	
	private JobService jobService;
	
	public JobController(JobService jobService) {
		this.jobService=jobService; 
	}
	
	@GetMapping("/jobs")
	public ResponseEntity<List<Job>> findAll(){
		return ResponseEntity.ok(jobService.finAll()); // you can write the response entity like this as well.
	}
	
	@GetMapping("/jobs/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long id) {
		Job job=jobService.getJobById(id);
		if(job!=null)
			return new ResponseEntity<>(job,HttpStatus.OK); // it is necessary to pass HttpStatus
		return new ResponseEntity<Job>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/jobs")
	public ResponseEntity<String> createJob(@RequestBody Job job) {
		jobService.createJob(job);
		return new ResponseEntity<>("Job added Successfully",HttpStatus.CREATED);
	}

	@DeleteMapping("/jobs/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id){
		boolean deleted=jobService.deleteJob(id);
		if(deleted) new ResponseEntity<>("job deleted successfully", HttpStatus.OK);
		return new ResponseEntity<String>("job not found",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/jobs/{id}")
	// @RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT) Instead of get,put,post,delete we can use this too.
	public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
		boolean updated = jobService.updateJob(id, updatedJob);
		if(updated) {
			return new ResponseEntity<String>("Job updated Successfully",HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
}
