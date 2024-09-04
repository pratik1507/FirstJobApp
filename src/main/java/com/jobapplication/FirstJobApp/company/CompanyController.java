package com.jobapplication.FirstJobApp.company;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	
	private CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}
	
	@GetMapping
	public ResponseEntity<List<Company>> getAllCompanies(){
		return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company company) {
		companyService.updateCompany(id, company);
		return new ResponseEntity<String>("Company updated successfully",HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> addCompany(@RequestBody Company company){
		companyService.createCompany(company);
		return new ResponseEntity<String>("Company added Successfully",HttpStatus.CREATED );
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable Long id){
		boolean isDeleted = companyService.deleteCompanyById(id);
		if(isDeleted) return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
		return new ResponseEntity<String>("Not found",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompany(@PathVariable Long id){
		Company company = companyService.getCompanyById(id);
		if(company!=null) {
			return new ResponseEntity<Company>(company,HttpStatus.OK);
		}
		return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
	}
}
