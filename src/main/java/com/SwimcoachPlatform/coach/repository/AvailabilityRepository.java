package com.SwimcoachPlatform.coach.repository;

import com.SwimcoachPlatform.coach.entity.Availability;
import com.SwimcoachPlatform.coach.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
}
