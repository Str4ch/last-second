package lastsecond.services;

import lastsecond.configuration.HotelBookingNotFoundException;
import lastsecond.dto.HotelBookingDTO;
import lastsecond.entity.HotelBooking;
import lastsecond.repository.HotelBookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class BookingHotelService {
    HotelBookingRepository hotelBookingRepository;


    public HotelBookingDTO createBooking(HotelBookingDTO hotelBooking) {

        HotelBooking booking = new HotelBooking();

        booking.setPrice(hotelBooking.getPrice());
        booking.setDepartureDate(hotelBooking.getDepartureDate());
        booking.setArrivalDate(hotelBooking.getArrivalDate());
        booking.setFirstName(hotelBooking.getFirstName());
        booking.setLastName(hotelBooking.getLastName());
        booking.setIdNumber(hotelBooking.getIdNumber());
        booking.setHotelName(hotelBooking.getHotelName());
        booking.setHotelAddress(hotelBooking.getHotelAddress());

        int new_hotel_book_id = (int) hotelBookingRepository.count() + 1;
        booking.setId(new_hotel_book_id);

        booking = hotelBookingRepository.save(booking);
        hotelBooking.setId(booking.getId());

        return hotelBooking;
    }

    public List<HotelBookingDTO> getAllHotelBookings(){
        List<HotelBooking> bookings = hotelBookingRepository.findAll();
        List<HotelBookingDTO> bookingsDTO = new ArrayList<>();
        for (HotelBooking booking : bookings) {
            HotelBookingDTO hotelBookingDTO = new HotelBookingDTO(booking.getId(), booking.getFirstName(), booking.getLastName(), booking.getIdNumber(), booking.getHotelName(), booking.getHotelAddress(), booking.getArrivalDate(), booking.getDepartureDate(), booking.getPrice());
            bookingsDTO.add(hotelBookingDTO);
        }
        return bookingsDTO;
    }

    public HotelBookingDTO getHotelBookingById(int id){
        HotelBooking booking = hotelBookingRepository.getById(id);
        HotelBookingDTO hotelBookingDTO = new HotelBookingDTO(booking.getId(), booking.getFirstName(), booking.getLastName(), booking.getIdNumber(), booking.getHotelName(), booking.getHotelAddress(), booking.getArrivalDate(), booking.getDepartureDate(), booking.getPrice());
        return hotelBookingDTO;
    }

    public HotelBookingDTO updateBooking(HotelBookingDTO hotelBooking){
        if(hotelBookingRepository.findById(hotelBooking.getId()).isPresent()){
            HotelBooking booking = hotelBookingRepository.findById(hotelBooking.getId()).get();
            booking.setPrice(hotelBooking.getPrice());
            booking.setDepartureDate(hotelBooking.getDepartureDate());
            booking.setArrivalDate(hotelBooking.getArrivalDate());
            booking.setFirstName(hotelBooking.getFirstName());
            booking.setLastName(hotelBooking.getLastName());
            booking.setIdNumber(hotelBooking.getIdNumber());

            hotelBookingRepository.updateById(hotelBooking.getId(), hotelBooking.getFirstName(), hotelBooking.getLastName(), hotelBooking.getIdNumber(), hotelBooking.getHotelName(), hotelBooking.getHotelAddress(), booking.getArrivalDate(), booking.getDepartureDate(), hotelBooking.getPrice());
            return hotelBooking;
        }
        else {
            throw new HotelBookingNotFoundException();
        }
    }
    public void deleteBooking(int id){
       hotelBookingRepository.deleteByIdCustom(id);
    }

    public List<HotelBookingDTO> getAllHotelBookingsByIdNumber(int idnum) {
        return  hotelBookingRepository.getHotelBookingByIdNumber(idnum);
    }
}
