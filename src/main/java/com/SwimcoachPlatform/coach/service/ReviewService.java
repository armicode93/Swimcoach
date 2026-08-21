package com.SwimcoachPlatform.coach.service;


import com.SwimcoachPlatform.coach.entity.Review;
import com.SwimcoachPlatform.coach.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private  final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }
    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }
    public Review findReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }
    public Review updateReview(Long id,Review review) {
        return reviewRepository.save(review);
    }
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
