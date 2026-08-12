package com.SwimcoachPlatform.coach.repository;

import com.SwimcoachPlatform.coach.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Long> {
}
