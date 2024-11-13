package lastsecond.controller;

import lastsecond.dto.FlightBookingDTO;
import lastsecond.dto.HotelBookingDTO;
import lastsecond.dto.TripBookingDTO;
import lastsecond.service.TripBookingService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController

public class TripController {
    TripBookingService tripBookingService;
    public TripController(TripBookingService tripBookingService) {
        this.tripBookingService = tripBookingService;
    }
    @PostMapping("/createbooking")
    public String createBooking(@RequestBody TripBookingDTO tripBookingDTO) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        FlightBookingDTO arrivalFlightBookingDTO = tripBookingDTO.getArrivalFlightBooking();
        FlightBookingDTO departureFlightBookingDTO = tripBookingDTO.getDepartureFlightBooking();
        HotelBookingDTO hotelBookingDTO = tripBookingDTO.getHotelBooking();
        return tripBookingService.createTrip(arrivalFlightBookingDTO, departureFlightBookingDTO, hotelBookingDTO);
    }
    @GetMapping("/getAllFlightsByIdNum/{id}")
    public List<FlightBookingDTO> getAllFlightsByIdNum(@PathVariable int id) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        return tripBookingService.getAllFlights(id);
    }
    @GetMapping("/getAllHotelsByIdNum/{id}")
    public List<HotelBookingDTO> getAllHotelsByIdNum(@PathVariable int id) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        return tripBookingService.getAllHotelBookings(id);
    }
    @PutMapping("/updateHotelBooking")
    public HotelBookingDTO updateHotelBooking(@RequestBody HotelBookingDTO hotelBookingDTO) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        return tripBookingService.updateHotelBooking(hotelBookingDTO);
    }
    @PutMapping("/updateFlight")
    public FlightBookingDTO updateFlight(@RequestBody FlightBookingDTO flightBookingDTO) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        return tripBookingService.updateFlight(flightBookingDTO);
    }
    @DeleteMapping("/deleteBooling/{flight_id1}/{flight_id2}/{hotel_id}")
    public boolean deleteBooking(@PathVariable int flight_id1, @PathVariable int flight_id2, @PathVariable int hotel_id) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        return tripBookingService.deleteBooking(flight_id1, flight_id2, hotel_id);
    }
}
