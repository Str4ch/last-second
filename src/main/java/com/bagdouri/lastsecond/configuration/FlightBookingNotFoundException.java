package com.bagdouri.lastsecond.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="No such flight")
public class FlightBookingNotFoundException extends RuntimeException {
    public FlightBookingNotFoundException() {};
}
