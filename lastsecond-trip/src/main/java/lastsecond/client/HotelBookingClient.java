package lastsecond.client;

import jakarta.websocket.ClientEndpoint;
import lastsecond.dto.FlightBookingDTO;
import lastsecond.dto.HotelBookingDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

@ClientEndpoint
public interface HotelBookingClient {
    @GET("/hotels/GetAllHotelBookings")
    Call<List<HotelBookingDTO>> GetAllHotelBookings();

    @GET("hotels/getallhotelbookingsbyidnum/{id}")
    Call<List<HotelBookingDTO>> GetAllUserHotelBookings(@PathVariable("id") int id);

    @POST("/hotels/createHotelBookings")
    Call<HotelBookingDTO> createBooking(@Body HotelBookingDTO hotelBookingDTO);

    @PUT("/hotels/UpdateBooking")
    Call<HotelBookingDTO> updateBooking(@Body HotelBookingDTO hotelBookingDTO);

    @DELETE("/hotels/DeleteBooking")
    Call<Void> deleteBooking(int id);
}
