package lastsecond.client;

import lastsecond.dto.FlightBookingDTO;
import org.springframework.web.bind.annotation.RequestParam;
import retrofit2.*;
import retrofit2.http.*;

import java.util.List;

public interface FlightBookingClient {
    @GET("flights/getallbookings")
    Call<List<FlightBookingDTO>> GetAllFlights();

    @GET("flights/getallbookingsbyidnum/{id}")
    Call<List<FlightBookingDTO>> GetAllUserFlights(@RequestParam("id") int id);
    @POST("flights/createbooking")
    Call<FlightBookingDTO> createBooking(@Body FlightBookingDTO flight);

    @PUT("flights/updatebooking")
    Call<FlightBookingDTO> updateBooking(@Body FlightBookingDTO flight);

    @DELETE("/flights/deletebooking")
    Call<Void> deleteBooking(int id);

}
