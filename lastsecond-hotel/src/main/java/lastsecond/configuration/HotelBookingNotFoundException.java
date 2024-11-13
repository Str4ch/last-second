package lastsecond.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="No such flight")
public class HotelBookingNotFoundException extends RuntimeException {
    public HotelBookingNotFoundException() {};
}
