package lastsecond.repository;

import jakarta.transaction.Transactional;
import lastsecond.dto.HotelBookingDTO;
import lastsecond.entity.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface HotelBookingRepository extends JpaRepository<HotelBooking, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE HotelBooking f SET f.firstName = ?2, f.lastName = ?3, f.idNumber = ?4, f.hotelName = ?5, f.hotelAddress = ?6, f.arrivalDate = ?7, f.departureDate = ?8, f.price = ?9 WHERE f.id = ?1")
    void updateById(int id, String firstName, String lastName, int idNumber, String hotelName, String hotelAddress, Date arrivalDate, Date departureDate, float price);

    @Modifying
    @Transactional
    @Query("DELETE FROM HotelBooking f WHERE f.id = :id")
    void deleteByIdCustom(@Param("id") int id);

    @Query("SELECT h from HotelBooking h where h.idNumber=?1")
    List<HotelBookingDTO> getHotelBookingByIdNumber(int idNumber);
}
