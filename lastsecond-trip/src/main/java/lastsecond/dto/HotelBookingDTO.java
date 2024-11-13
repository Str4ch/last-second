package lastsecond.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.sql.Timestamp;

@Getter
@Setter
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
}
