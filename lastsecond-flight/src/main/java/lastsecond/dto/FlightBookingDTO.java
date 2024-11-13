package lastsecond.dto;

import java.util.Date;
import java.util.Optional;

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

    public FlightBookingDTO(Integer id, String firstName, String lastName, int idNumber, String departureAirport, Date departureTime, String arrivalAirport, Date arrivalTime, float price) {
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

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public float getPrice() {
        return price;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }
}
