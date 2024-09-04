package com.jobapplication.FirstJobApp.review.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobapplication.FirstJobApp.company.Company;
import com.jobapplication.FirstJobApp.company.CompanyService;
import com.jobapplication.FirstJobApp.review.Review;
import com.jobapplication.FirstJobApp.review.ReviewRepository;
import com.jobapplication.FirstJobApp.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository reviewRepository;
	
	private CompanyService companyService;
	
	public ReviewServiceImpl(ReviewRepository reviewRepository,CompanyService companyService) {
		super();
		this.reviewRepository = reviewRepository;
		this.companyService = companyService;
	}



	@Override
	public List<Review> getAllReviews(Long companyId) {
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);
		return reviews;
	}



	@Override
	public boolean addReview(Long companyId, Review review) {
		Company company=companyService.getCompanyById(companyId);
		if(company!=null) {
			review.setCompany(company);
			reviewRepository.save(review);
			return true;
		}
		return false;
	}



	@Override
	public Review getReview(Long compnayId, Long reviewId) {
		List<Review> reviews = reviewRepository.findByCompanyId(compnayId);
		return reviews.stream().filter(review-> review.getId().equals(reviewId)).findFirst().orElse(null);
	}



	@Override
	public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
		if(companyService.getCompanyById(companyId)!=null) {
			updatedReview.setCompany(companyService.getCompanyById(companyId));
			updatedReview.setId(reviewId);
			reviewRepository.save(updatedReview);
			return true;
		}
		return false;
	}



	@Override
	public boolean deleteReview(Long companyId, Long reviewId) {
		if(companyService.getCompanyById(companyId)!=null && reviewRepository.existsById(reviewId)) {
			Review review=reviewRepository.findById(reviewId).orElse(null);
			Company company=review.getCompany();
			company.getReviews().remove(review); // since it is bi-directional mapping
			review.setCompany(null);
			companyService.updateCompany(companyId, company);
			reviewRepository.deleteById(reviewId);
			return true;
		}
		return false;
	}

}
