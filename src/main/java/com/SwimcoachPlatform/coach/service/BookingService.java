package com.SwimcoachPlatform.coach.service;


import com.SwimcoachPlatform.coach.dto.BookingDTO;
import com.SwimcoachPlatform.coach.entity.Booking;
import com.SwimcoachPlatform.coach.entity.Coach;
import com.SwimcoachPlatform.coach.entity.Course;
import com.SwimcoachPlatform.coach.entity.User;
import com.SwimcoachPlatform.coach.repository.BookingRepository;
import com.SwimcoachPlatform.coach.repository.CourseRepository;
import com.SwimcoachPlatform.coach.repository.UserRepository;
import com.SwimcoachPlatform.coach.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final CoachRepository coachRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, CoachRepository coachRepository, CourseRepository courseRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.coachRepository = coachRepository;
        this.courseRepository = courseRepository;
    }

    // POST
    public Booking addBooking(BookingDTO bookingDTO) {

        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Coach coach = coachRepository.findById(bookingDTO.getCoachId())
                .orElseThrow(() -> new RuntimeException("Coach not found"));

        Course course = courseRepository.findById(bookingDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Booking booking = new Booking();

        booking.setUser(user);
        booking.setCoach(coach);
        booking.setCourse(course);

        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setStartTime(bookingDTO.getStartTime());
        booking.setEndTime(bookingDTO.getEndTime());
        booking.setComment(bookingDTO.getComment());
        booking.setStatus(bookingDTO.getStatus());

        booking.setCreatedAt(LocalDateTime.now());

        return bookingRepository.save(booking);
    }
    public List<Booking> findAllBooking() {
        return bookingRepository.findAll();
    }
    public Booking findBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
    public Booking updateBooking(Long id, BookingDTO bookingDTO) {

        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Coach coach = coachRepository.findById(bookingDTO.getCoachId())
                .orElseThrow(() -> new RuntimeException("Coach not found"));

        Course course = courseRepository.findById(bookingDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        existingBooking.setUser(user);
        existingBooking.setCoach(coach);
        existingBooking.setCourse(course);

        existingBooking.setBookingDate(bookingDTO.getBookingDate());
        existingBooking.setStartTime(bookingDTO.getStartTime());
        existingBooking.setEndTime(bookingDTO.getEndTime());
        existingBooking.setComment(bookingDTO.getComment());
        existingBooking.setStatus(bookingDTO.getStatus());

        return bookingRepository.save(existingBooking);
    }

}
