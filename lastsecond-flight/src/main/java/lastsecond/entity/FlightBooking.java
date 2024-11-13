package lastsecond.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Entity
@Getter
@Setter
@Table(name = "flight_booking")
public class FlightBooking {
    @Id
    private int id;

    private String firstName;
    private String lastName;
    private int idNumber;
    private String departureAirport;
    private Date departureTime;
    private String arrivalAirport;
    private Date arrivalTime;
    private float price;

}
