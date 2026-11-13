package com.SwimcoachPlatform.coach.controllers;

import com.SwimcoachPlatform.coach.dto.BookingDTO;
import com.SwimcoachPlatform.coach.entity.Booking;
import com.SwimcoachPlatform.coach.entity.User;
import com.SwimcoachPlatform.coach.repository.UserRepository;
import com.SwimcoachPlatform.coach.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final UserRepository userRepository;

    @Autowired
    public BookingController(
            BookingService bookingService,
            UserRepository userRepository) {
        this.bookingService = bookingService;
        this.userRepository = userRepository;
    }

    // GET: recupera tutte le prenotazioni
   /* @GetMapping
    public List<Booking> findAllBooking() {
        return bookingService.findAllBooking();
    }

    */

    // GET: recupera una prenotazione tramite ID
    @GetMapping("/{id}")
    public Booking findBookingById(
            @PathVariable Long id,
            Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found."
                ));

        return bookingService.findBookingById(id, user.getId());
    }

    // POST: crea una nuova prenotazione
    @PostMapping
    public Booking addBooking(
            @RequestBody BookingDTO bookingDTO,
            Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found."
                ));

        bookingDTO.setUserId(user.getId());

        return bookingService.addBooking(bookingDTO);
    }

    @PutMapping("/{id}")
    public Booking updateBooking(
            @PathVariable Long id,
            @RequestBody BookingDTO dto,
            Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found."
                ));

        return bookingService.updateBooking(id, dto, user.getId());
    }

    // DELETE: elimina una prenotazione
    @DeleteMapping("/{id}")
    public void deleteBooking(
            @PathVariable Long id,
            Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found."
                ));

        bookingService.deleteBooking(id, user.getId());
    }

    @GetMapping("/my-bookings")
    public List<Booking> findMyBookings(Authentication authentication) {

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication.getPrincipal().equals("anonymousUser")) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Please log in to view your bookings."
            );
        }

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found."
                ));

        return bookingService.findBookingsByUserId(user.getId());
    }
}