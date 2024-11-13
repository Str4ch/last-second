package lastsecond.dto;

import java.util.Date;

public class HotelBookingDTO {

    private int id;
    private String firstName;
    private String lastName;
    private int idNumber;
    private String hotelName;
    private String hotelAddress;
    private Date arrivalDate;
    private Date departureDate;
    private float price;

    public HotelBookingDTO(int id, String firstName, String lastName, int idNumber, String hotelName, String hotelAddress, Date arrivalDate, Date departureDate, float price) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.price = price;
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

    public String getHotelName() {return hotelName;}

    public String getHotelAddress() {return hotelAddress;}

    public Date getArrivalDate() {return arrivalDate;}

    public Date getDepartureDate() {return departureDate;}

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

    public void setPrice(float price) {
        this.price = price;
    }

    public void setHotelName(String hotelName) {this.hotelName = hotelName;}

    public void setHotelAddress(String hotelAddress) {this.hotelAddress = hotelAddress;}

    public void setArrivalDate(Date arrivalDate) {this.arrivalDate = arrivalDate;}

    public void setDepartureDate(Date departureDate) {this.departureDate = departureDate;}

    public void setId(int id) {
        this.id = id;
    }
}
