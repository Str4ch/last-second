package lastsecond.controller;

import lastsecond.dto.FlightBookingDTO;
import lastsecond.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/createbooking")
    public FlightBookingDTO createBooking(@RequestBody FlightBookingDTO bookingDTO) {
        return bookingService.createBooking(bookingDTO);
    }
    @GetMapping("/getallbookings")
    public List<FlightBookingDTO> getAllBookings() {
        return bookingService.getAllFlights();
    }
    @GetMapping("/heartbeat")
    public String heartbeat() {
        return "heartbeat";
    }

    @GetMapping("/getallbookingsbyidnum/{id}")
    public List<FlightBookingDTO> getAllBookingsbyidnum(@PathVariable int id) {
        return bookingService.getAllBookingsbyidnum(id);
    }
    @PutMapping("/updatebooking")
    public FlightBookingDTO updateBooking(@RequestBody FlightBookingDTO bookingDTO) {

        return bookingService.updateBooking(bookingDTO);
    }
    @DeleteMapping("/deletebooking")
    public void deleteBooking(@RequestBody Integer id) {
        bookingService.deleteBooking(id);
    }
}
