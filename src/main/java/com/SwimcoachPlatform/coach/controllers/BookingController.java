package com.SwimcoachPlatform.coach.controllers;

import com.SwimcoachPlatform.coach.dto.BookingDTO;
import com.SwimcoachPlatform.coach.entity.Booking;
import com.SwimcoachPlatform.coach.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // GET: recupera tutte le prenotazioni
    @GetMapping
    public List<Booking> findAllBooking() {
        return bookingService.findAllBooking();
    }

    // GET: recupera una prenotazione tramite ID
    @GetMapping("/{id}")
    public Booking findBookingById(@PathVariable Long id) {
        return bookingService.findBookingById(id);
    }

    // POST: crea una nuova prenotazione
    @PostMapping
    public Booking addBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.addBooking(bookingDTO);
    }


    @PutMapping("/{id}")
    public Booking updateBooking(
            @PathVariable Long id,
            @RequestBody BookingDTO dto) {

        return bookingService.updateBooking(id, dto);
    }

    // DELETE: elimina una prenotazione
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}