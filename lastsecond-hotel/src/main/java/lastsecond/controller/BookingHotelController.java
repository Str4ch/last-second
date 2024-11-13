package lastsecond.controller;


import lastsecond.dto.HotelBookingDTO;
import lastsecond.services.BookingHotelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@AllArgsConstructor
public class BookingHotelController {
    private final BookingHotelService bookingHotelService;

    @PostMapping("/createHotelBookings")
    public HotelBookingDTO createBooking(@RequestBody HotelBookingDTO bookingDTO) {
        return bookingHotelService.createBooking(bookingDTO);
    }
    @GetMapping("/GetAllHotelBookings")
    public List<HotelBookingDTO> getAllBookings() {
        return bookingHotelService.getAllHotelBookings();
    }

    @GetMapping("/getallhotelbookingsbyidnum/{id}")
    public List<HotelBookingDTO> getAllBookingsByIdnum(@PathVariable("id") int idnum) {
        return bookingHotelService.getAllHotelBookingsByIdNumber(idnum);
    }
    @GetMapping("heartbeat")
    public String heartbeat() {
        return "heartbeat";
    }
    @GetMapping("/GetHotelBooking/{id}")
    public HotelBookingDTO getBooking(@PathVariable("id") int id) {
        return bookingHotelService.getHotelBookingById(id);
    }
    @PutMapping("/UpdateBooking")
    public HotelBookingDTO updateBooking(@RequestBody HotelBookingDTO bookingDTO) {

        return bookingHotelService.updateBooking(bookingDTO);
    }
    @DeleteMapping("/DeleteBooking")
    public void deleteBooking(@RequestBody Integer id) {
        bookingHotelService.deleteBooking(id);
    }
}

