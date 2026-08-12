package com.SwimcoachPlatform.coach.repository;

import com.SwimcoachPlatform.coach.entity.Pool;
import com.SwimcoachPlatform.coach.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
