package lastsecond.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TripBookingDTO {
    private FlightBookingDTO arrivalFlightBooking;
    private FlightBookingDTO departureFlightBooking;
    private HotelBookingDTO hotelBooking;
}
