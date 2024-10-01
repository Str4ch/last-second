package com.bagdouri.lastsecond.controller;

import com.bagdouri.lastsecond.dto.FlightBookingDTO;
import com.bagdouri.lastsecond.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public FlightBookingDTO createBooking(@RequestBody FlightBookingDTO bookingDTO) {
        return bookingService.createBooking(bookingDTO);
    }
    @GetMapping
    public List<FlightBookingDTO> getAllBookings() {
        return bookingService.getAllFlights();
    }
    @PutMapping
    public FlightBookingDTO updateBooking(@RequestBody FlightBookingDTO bookingDTO) {

        return bookingService.updateBooking(bookingDTO);
    }
    @DeleteMapping
    public void deleteBooking(@RequestBody Integer id) {
        bookingService.deleteBooking(id);
    }
}
