package lastsecond.repository;

import jakarta.transaction.Transactional;
import lastsecond.dto.FlightBookingDTO;
import lastsecond.entity.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE FlightBooking f SET f.firstName = :firstName, f.lastName = :lastName, f.idNumber = :idNumber, "
            + "f.departureAirport = :departureAirport, f.departureTime = :departureTime, f.arrivalAirport = :arrivalAirport, "
            + "f.arrivalTime = :arrivalTime, f.price = :price WHERE f.id = :id")
    void updateById(@Param("id") int id,
                    @Param("firstName") String firstName,
                    @Param("lastName") String lastName,
                    @Param("idNumber") int idNumber,
                    @Param("departureAirport") String departureAirport,
                    @Param("departureTime") Date departureTime,
                    @Param("arrivalAirport") String arrivalAirport,
                    @Param("arrivalTime") Date arrivalTime,
                    @Param("price") float price);

    @Modifying
    @Transactional
    @Query("DELETE FROM FlightBooking f WHERE f.id = :id")
    void deleteByIdCustom(@Param("id") int id);

    @Query("Select f from FlightBooking f where f.idNumber = ?1")
    List<FlightBookingDTO> getAllBookingsbyidnum(int id);
}
