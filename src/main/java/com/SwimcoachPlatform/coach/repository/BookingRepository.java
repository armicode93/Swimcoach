package com.SwimcoachPlatform.coach.repository;

import com.SwimcoachPlatform.coach.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import com.SwimcoachPlatform.coach.entity.BookingStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);



        // Verifica se esiste una prenotazione sovrapposta

    @Query("""
    SELECT COUNT(b) > 0
    FROM Booking b
    WHERE b.coach.id = :coachId
      AND b.bookingDate = :bookingDate
      AND b.status IN :statuses
      AND b.startTime < :endTime
      AND b.endTime > :startTime
      AND (:excludedBookingId IS NULL OR b.id <> :excludedBookingId)
""")
    boolean existsOverlappingBooking(
            @Param("coachId") Long coachId,
            @Param("bookingDate") java.time.LocalDate bookingDate,
            @Param("startTime") java.time.LocalTime startTime,
            @Param("endTime") java.time.LocalTime endTime,
            @Param("statuses") List<BookingStatus> statuses,
            @Param("excludedBookingId") Long excludedBookingId
    );

}