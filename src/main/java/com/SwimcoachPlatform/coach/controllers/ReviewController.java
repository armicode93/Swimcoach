package com.SwimcoachPlatform.coach.controllers;

import com.SwimcoachPlatform.coach.dto.ReviewDTO;
import com.SwimcoachPlatform.coach.entity.Review;
import com.SwimcoachPlatform.coach.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // GET: recupera tutte le recensioni
    @GetMapping
    public List<Review> findAllReviews() {
        return reviewService.findAllReviews();
    }

    // GET: recupera una recensione tramite ID
    @GetMapping("/{id}")
    public Review findReviewById(@PathVariable Long id) {
        return reviewService.findReviewById(id);
    }

    // POST: crea una nuova recensione
    @PostMapping
    public Review addReview(@RequestBody ReviewDTO reviewDTO) {
        return reviewService.addReview(reviewDTO);
    }

    // PUT: modifica una recensione
    @PutMapping("/{id}")
    public Review updateReview(@PathVariable Long id,
                               @RequestBody Review review) {
        review.setId(id);
        return reviewService.updateReview(id, review);
    }

    // DELETE: elimina una recensione
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}