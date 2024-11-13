package lastsecond.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "hotel_bookings")
@Getter
@Setter
@ToString
public class HotelBooking implements Serializable {
    @Id
    private int id;

    private String firstName;
    private String lastName;
    private int idNumber;
    private String hotelName;
    private String hotelAddress;
    private Date arrivalDate;
    private Date departureDate;
    private float price;
}
