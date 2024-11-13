package lastsecond.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FlightBookingDTO {

    private int id;
    private String firstName;
    private String lastName;
    private int idNumber;
    private String departureAirport;
    private Date departureTime;
    private String arrivalAirport;
    private Date arrivalTime;
    private float price;

    public FlightBookingDTO(int id, String firstName, String lastName, int idNumber, String departureAirport, Date departureTime, String arrivalAirport, Date arrivalTime, float price) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.departureAirport = departureAirport;
        this.departureTime = departureTime;
        this.arrivalAirport = arrivalAirport;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public FlightBookingDTO(FlightBookingDTO flight) {
        this.id = flight.getId();
        this.firstName = flight.getFirstName();
        this.lastName = flight.getLastName();
        this.idNumber = flight.getIdNumber();
        this.departureAirport = flight.getDepartureAirport();
        this.departureTime = flight.getDepartureTime();
        this.arrivalAirport = flight.getArrivalAirport();
        this.arrivalTime = flight.getArrivalTime();
        this.price = flight.getPrice();
    }
}
