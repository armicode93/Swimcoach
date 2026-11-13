package com.SwimcoachPlatform.coach.service;


import com.SwimcoachPlatform.coach.dto.BookingDTO;
import com.SwimcoachPlatform.coach.entity.*;
import com.SwimcoachPlatform.coach.repository.BookingRepository;
import com.SwimcoachPlatform.coach.repository.CourseRepository;
import com.SwimcoachPlatform.coach.repository.UserRepository;
import com.SwimcoachPlatform.coach.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final CoachRepository coachRepository;
    private final CourseRepository courseRepository;
    private final AvailabilityService availabilityService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, CoachRepository coachRepository, CourseRepository courseRepository, AvailabilityService availabilityService) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.coachRepository = coachRepository;
        this.courseRepository = courseRepository;
        this.availabilityService = availabilityService;

    }

    // POST
    public Booking addBooking(BookingDTO bookingDTO) {

        // 1. Validate date and time
        if (bookingDTO.getBookingDate() == null ||
                bookingDTO.getStartTime() == null ||
                bookingDTO.getEndTime() == null ||
                !bookingDTO.getEndTime().isAfter(bookingDTO.getStartTime())) {

            throw new IllegalArgumentException("Invalid booking date or time.");
        }

        // 2. Retrieve user, coach and course
        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Coach coach = coachRepository.findById(bookingDTO.getCoachId())
                .orElseThrow(() -> new RuntimeException("Coach not found"));

        Course course = courseRepository.findById(bookingDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));


        // 3. Check coach availability
        validateCoachAvailability(coach, course, bookingDTO);

// 4. Check overlapping bookings
        validateNoOverlappingBooking(coach.getId(), bookingDTO, null);

        // 5. Create booking
        Booking booking = new Booking();

        booking.setUser(user);
        booking.setCoach(coach);
        booking.setCourse(course);

        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setStartTime(bookingDTO.getStartTime());
        booking.setEndTime(bookingDTO.getEndTime());
        booking.setComment(bookingDTO.getComment());
        booking.setStatus(BookingStatus.PENDING);

        booking.setCreatedAt(LocalDateTime.now());

        return bookingRepository.save(booking);
    }
   /* public List<Booking> findAllBooking() {
        return bookingRepository.findAll();
    }

    */
    public Booking findBookingById(Long id, Long userId) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getUser().getId().equals(userId)) {
            throw new RuntimeException(
                    "You are not allowed to view this booking."
            );
        }

        return booking;
    }
    public void deleteBooking(Long id, Long userId) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getUser().getId().equals(userId)) {
            throw new RuntimeException(
                    "You are not allowed to delete this booking."
            );
        }

        bookingRepository.delete(booking);
    }
    public Booking updateBooking(Long id, BookingDTO bookingDTO, Long userId) {

        validateBookingDateAndTime(bookingDTO);

        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!existingBooking.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to modify this booking.");
        }

        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Coach coach = coachRepository.findById(bookingDTO.getCoachId())
                .orElseThrow(() -> new RuntimeException("Coach not found"));

        Course course = courseRepository.findById(bookingDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        validateCoachAvailability(coach, course, bookingDTO);

        validateNoOverlappingBooking(
                coach.getId(),
                bookingDTO,
                existingBooking.getId()
        );

        existingBooking.setUser(user);
        existingBooking.setCoach(coach);
        existingBooking.setCourse(course);
        existingBooking.setBookingDate(bookingDTO.getBookingDate());
        existingBooking.setStartTime(bookingDTO.getStartTime());
        existingBooking.setEndTime(bookingDTO.getEndTime());
        existingBooking.setComment(bookingDTO.getComment());

        return bookingRepository.save(existingBooking);
    }
    private void validateBookingDateAndTime(BookingDTO bookingDTO) {

        if (bookingDTO.getBookingDate() == null ||
                bookingDTO.getStartTime() == null ||
                bookingDTO.getEndTime() == null ||
                !bookingDTO.getEndTime().isAfter(bookingDTO.getStartTime())) {

            throw new IllegalArgumentException("Invalid booking date or time.");
        }
    }

    private void validateCoachAvailability(
            Coach coach, Course course, BookingDTO bookingDTO) {

        List<Availability> availabilities =
                availabilityService.getAvailabilityByCoachAndCourse(
                        coach.getId(), course.getId()
                );

        DayOfWeek bookingDay = DayOfWeek.valueOf(
                bookingDTO.getBookingDate().getDayOfWeek().name()
        );

        boolean available = availabilities.stream().anyMatch(a ->
                a.getDayOfWeek() == bookingDay &&
                        !bookingDTO.getStartTime().isBefore(a.getStartTime()) &&
                        !bookingDTO.getEndTime().isAfter(a.getEndTime())
        );

        if (!available) {
            throw new IllegalArgumentException(
                    "The coach is not available at the selected time."
            );
        }
    }

    private void validateNoOverlappingBooking(
            Long coachId, BookingDTO bookingDTO, Long excludedBookingId) {

        boolean hasConflict = bookingRepository.existsOverlappingBooking(
                coachId,
                bookingDTO.getBookingDate(),
                bookingDTO.getStartTime(),
                bookingDTO.getEndTime(),
                Arrays.asList(BookingStatus.PENDING, BookingStatus.CONFIRMED),
                excludedBookingId
        );

        if (hasConflict) {
            throw new RuntimeException(
                    "This time slot is already booked. Please choose another time."
            );
        }
    }
    public List<Booking> findBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

}
