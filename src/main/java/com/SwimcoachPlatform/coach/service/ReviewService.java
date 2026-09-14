package com.SwimcoachPlatform.coach.service;


import com.SwimcoachPlatform.coach.dto.ReviewDTO;
import com.SwimcoachPlatform.coach.entity.Booking;
import com.SwimcoachPlatform.coach.entity.Coach;
import com.SwimcoachPlatform.coach.entity.Review;
import com.SwimcoachPlatform.coach.entity.User;
import com.SwimcoachPlatform.coach.repository.BookingRepository;
import com.SwimcoachPlatform.coach.repository.CoachRepository;
import com.SwimcoachPlatform.coach.repository.ReviewRepository;
import com.SwimcoachPlatform.coach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    private  final ReviewRepository reviewRepository;
    private final CoachRepository coachRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, CoachRepository coachRepository, BookingRepository bookingRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;

        this.coachRepository = coachRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }
    public Review addReview(ReviewDTO reviewDTO) {

        User user = userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Coach coach = coachRepository.findById(reviewDTO.getCoachId())
                .orElseThrow(() -> new RuntimeException("Coach not found"));

        Booking booking = bookingRepository.findById(reviewDTO.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Review review = new Review();

        review.setUser(user);
        review.setCoach(coach);
        review.setBooking(booking);
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setCreatedAt(LocalDateTime.now());

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
