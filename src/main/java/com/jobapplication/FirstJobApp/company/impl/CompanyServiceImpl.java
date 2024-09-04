package com.jobapplication.FirstJobApp.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jobapplication.FirstJobApp.company.Company;
import com.jobapplication.FirstJobApp.company.CompanyRepository;
import com.jobapplication.FirstJobApp.company.CompanyService;
import com.jobapplication.FirstJobApp.job.Job;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	private CompanyRepository companyRepository;

	public CompanyServiceImpl(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}



	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}



	@Override
	public boolean updateCompany(Long id, Company company) {
		Optional<Company> companyOptional = companyRepository.findById(id);
		if(companyOptional.isPresent()) {
			Company newCompany=companyOptional.get();
			newCompany.setName(company.getName());
			newCompany.setDescription(company.getDescription());
			newCompany.setJobs(company.getJobs());
			companyRepository.save(newCompany);
			return true;
		}
		return false;
	}



	@Override
	public void createCompany(Company company) {
		companyRepository.save(company);
	}



	@Override
	public boolean deleteCompanyById(Long id) {
		if(companyRepository.existsById(id)) {
			companyRepository.deleteById(id);
			return true;
		}
		return false;
	}



	@Override
	public Company getCompanyById(Long id) {
		return companyRepository.findById(id).orElse(null);
	}




}
