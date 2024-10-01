package com.bagdouri.lastsecond.services;

import com.bagdouri.lastsecond.configuration.FlightBookingNotFoundException;
import com.bagdouri.lastsecond.dto.FlightBookingDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class BookingService {
    private Map<Integer, FlightBookingDTO> flights;

    public BookingService() {
        flights = new HashMap<>();
    }
    public FlightBookingDTO createBooking(FlightBookingDTO flight) {
        flights.put(flight.getId(), flight);
        return flight;
    }

    public List<FlightBookingDTO> getAllFlights(){
        return new ArrayList<>(flights.values());
    }
    public FlightBookingDTO updateBooking(FlightBookingDTO booking){
        System.out.println(booking.getId());
        if(flights.containsKey(booking.getId())){
            flights.put(booking.getId(), booking);
            return booking;
        }
        else{
            throw new FlightBookingNotFoundException();
        }
    }
    public void deleteBooking(int id){
        flights.remove(id);
    }
}
