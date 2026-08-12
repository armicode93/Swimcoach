package com.SwimcoachPlatform.coach.repository;

import com.SwimcoachPlatform.coach.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
