package com.jobapplication.FirstJobApp.review;

import java.util.List;

public interface ReviewService {
	
	List<Review> getAllReviews(Long companyId);
	boolean addReview(Long companyId,Review review);
	Review getReview(Long compnayId,Long reviewId);
	boolean updateReview(Long companyId, Long reviewId, Review review);
	boolean deleteReview(Long companyId, Long reviewId);

}
