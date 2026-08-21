package com.SwimcoachPlatform.coach.service;


import com.SwimcoachPlatform.coach.entity.Booking;
import com.SwimcoachPlatform.coach.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    public Booking addBooking(Booking booking) {
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
    public Booking updateBooking(Long id, Booking booking) {
        return bookingRepository.save(booking);
    }

}
