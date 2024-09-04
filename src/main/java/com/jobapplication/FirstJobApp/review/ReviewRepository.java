package com.jobapplication.FirstJobApp.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findByCompanyId(Long companyId); // we no need to write the implementation of this method Jpa will breakdown the function name
	// and it will get to know what should be the functionality.
	// the functionality would be like this SELECT * FROM review WHERE company_id = ?;

}
