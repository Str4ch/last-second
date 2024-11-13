package lastsecond.services;

import com.bagdouri.lastsecond.configuration.FlightBookingNotFoundException;
import lastsecond.dto.FlightBookingDTO;
import lastsecond.entity.FlightBooking;
import lastsecond.repository.FlightBookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class BookingService {
    private FlightBookingRepository flightBookingRepository;

    public FlightBookingDTO createBooking(FlightBookingDTO flight) {
        FlightBooking flightBooking = new FlightBooking();

        flightBooking.setPrice(flight.getPrice());
        flightBooking.setDepartureAirport(flight.getDepartureAirport());
        flightBooking.setDepartureTime(flight.getDepartureTime());
        flightBooking.setArrivalAirport(flight.getArrivalAirport());
        flightBooking.setArrivalTime(flight.getArrivalTime());
        flightBooking.setFirstName(flight.getFirstName());
        flightBooking.setLastName(flight.getLastName());

        int new_flight_id = (int) flightBookingRepository.count() + 1;
        flightBooking.setId(new_flight_id);

        flightBooking = flightBookingRepository.save(flightBooking);
        flight.setId(flightBooking.getId());
        return flight;
    }

    public List<FlightBookingDTO> getAllFlights(){
        List<FlightBooking> flights = flightBookingRepository.findAll();
        List<FlightBookingDTO> flightsDTO = new ArrayList<>();
        for (FlightBooking flightBooking : flights) {
            FlightBookingDTO flightBookingDTO = new FlightBookingDTO(flightBooking.getId(), flightBooking.getFirstName(),flightBooking.getLastName(), flightBooking.getIdNumber(), flightBooking.getDepartureAirport(), flightBooking.getDepartureTime(), flightBooking.getArrivalAirport(), flightBooking.getArrivalTime(), flightBooking.getPrice());
            flightsDTO.add(flightBookingDTO);
        }
        return flightsDTO;
    }
    public FlightBookingDTO updateBooking(FlightBookingDTO booking){
        if(flightBookingRepository.findById(booking.getId()).isPresent()){
            flightBookingRepository.updateById(booking.getId(), booking.getFirstName(), booking.getLastName(), booking.getIdNumber(), booking.getDepartureAirport(), booking.getDepartureTime(), booking.getArrivalAirport(), booking.getArrivalTime(), booking.getPrice());
            return booking;
        }
        else{
            throw new FlightBookingNotFoundException();
        }
    }
    public void deleteBooking(int id){
        flightBookingRepository.deleteByIdCustom(id);
    }

    public List<FlightBookingDTO> getAllBookingsbyidnum(int id) {
        return flightBookingRepository.getAllBookingsbyidnum(id);
    }
}
