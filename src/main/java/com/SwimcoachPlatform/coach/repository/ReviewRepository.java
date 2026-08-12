package com.SwimcoachPlatform.coach.repository;

import com.SwimcoachPlatform.coach.entity.Pool;
import com.SwimcoachPlatform.coach.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository  extends JpaRepository<Review, Long>  {
}
