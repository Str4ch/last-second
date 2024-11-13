package lastsecond.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import lastsecond.client.FlightBookingClient;
import lastsecond.client.HotelBookingClient;
import lastsecond.configuration.RetrofitConfiguration;
import lastsecond.dto.FlightBookingDTO;
import lastsecond.dto.HotelBookingDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Service
@Component
public class TripBookingService {
    private static Cache<Integer, FlightBookingDTO> cache = CacheBuilder.newBuilder().initialCapacity(32).maximumSize(10).expireAfterWrite(5, TimeUnit.MINUTES).build();
    private final RestTemplate restTemplate = new RestTemplate();


    private int create_flight(FlightBookingClient client, FlightBookingDTO dto) throws IOException {
        Call<FlightBookingDTO> callSync = client.createBooking(dto);
        Response<FlightBookingDTO> response = callSync.execute();
        cache.put(dto.getId(), dto);
        return response.body().getId();
    }
    private int create_hotel(HotelBookingClient client, HotelBookingDTO dto) throws IOException {
        Call<HotelBookingDTO> callSync = client.createBooking(dto);
        Response<HotelBookingDTO> response = callSync.execute();
        return response.body().getId();
    }

    public String createTrip(FlightBookingDTO arrivalFlightBookingDTO, FlightBookingDTO departureFlightBookingDTO, HotelBookingDTO hotelBookingDTO) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        Retrofit retrofit = RetrofitConfiguration.getClient("flight");
        FlightBookingClient flight_client = retrofit.create(FlightBookingClient.class);
        retrofit = RetrofitConfiguration.getClient("hotel");
        HotelBookingClient hotel_client = retrofit.create(HotelBookingClient.class);
        int arrivalFlightId = -1, departureFlightId = -1, hotelId = -1;
        try {

            arrivalFlightId = create_flight(flight_client, arrivalFlightBookingDTO);

            hotelId = create_hotel(hotel_client, hotelBookingDTO);

            departureFlightId = create_flight(flight_client, departureFlightBookingDTO);
            return "Success, your destination flight id is " + arrivalFlightId + " and your return flight id is " + departureFlightId + ". Your hotel booking id is " + hotelId + ".";
        }
        catch (Exception e) {
            if(arrivalFlightId != -1){
                flight_client.deleteBooking(arrivalFlightId);
            }
            if(departureFlightId != -1){
                flight_client.deleteBooking(departureFlightId);
            }
            if(hotelId != -1){
                hotel_client.deleteBooking(hotelId);
            }
            return "Error creating trip booking.";
        }
    }

    public List<FlightBookingDTO> getAllFlights(int user_id) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        List<FlightBookingDTO> flights = new ArrayList<>();
        if(cache.size() < 1) {
            Retrofit retrofit = RetrofitConfiguration.getClient("flight");
            FlightBookingClient flight_client = retrofit.create(FlightBookingClient.class);
            Call<List<FlightBookingDTO>> callSync = flight_client.GetAllUserFlights(user_id);
            flights = callSync.execute().body();
            for (FlightBookingDTO flight : flights) {
                cache.put(flight.getId(), flight);
            }
        }
        else{
            Map<Integer, FlightBookingDTO> temps = cache.asMap();
            for (Map.Entry<Integer, FlightBookingDTO> entry : temps.entrySet()) {
                flights.add(entry.getValue());
            }
        }
        return flights;
    }
    public List<HotelBookingDTO> getAllHotelBookings(int user_id) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        Retrofit retrofit = RetrofitConfiguration.getClient("hotel");
        HotelBookingClient hotel_client = retrofit.create(HotelBookingClient.class);
        Call<List<HotelBookingDTO>> callSync = hotel_client.GetAllUserHotelBookings(user_id);
        return callSync.execute().body();
    }

    public FlightBookingDTO updateFlight( FlightBookingDTO dto) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        Retrofit retrofit = RetrofitConfiguration.getClient("flight");
        FlightBookingClient flight_client = retrofit.create(FlightBookingClient.class);
        Call<FlightBookingDTO> callSync = flight_client.updateBooking(dto);
        cache.put(dto.getId(), dto);
        return callSync.execute().body();
    }

    public HotelBookingDTO updateHotelBooking( HotelBookingDTO dto) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        Retrofit retrofit = RetrofitConfiguration.getClient("hotel");
        HotelBookingClient hotel_client = retrofit.create(HotelBookingClient.class);
        Call<HotelBookingDTO> callSync = hotel_client.updateBooking(dto);
        return callSync.execute().body();
    }
    public boolean deleteBooking(int flight_id1, int flight_id2, int hotel_id) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        Retrofit retrofit = RetrofitConfiguration.getClient("hotel");
        HotelBookingClient hotel_client = retrofit.create(HotelBookingClient.class);
        Call<Void>callSync = hotel_client.deleteBooking(hotel_id);
        Response<Void> response = callSync.execute();

        retrofit = RetrofitConfiguration.getClient("flight");
        FlightBookingClient flight_client = retrofit.create(FlightBookingClient.class);
        Call<Void> callsync2 = flight_client.deleteBooking(flight_id2);
        Response<Void> response2 = callsync2.execute();
        callsync2  = flight_client.deleteBooking(flight_id1);
        Response<Void> response3 = callsync2.execute();
        cache.invalidate(flight_id1);
        cache.invalidate(flight_id2);
        return response.code() == 200 && response2.code() == 200 && response3.code() == 200;
    }

    @Scheduled(fixedRate = 5000)  // Every 5 seconds
    public void sendHeartbeat() {
        try {
            String response1 = restTemplate.getForObject("https://localhost:8080/heartbeat", String.class);
            String response2 = restTemplate.getForObject("https://localhost:8081/heartbeat", String.class);
            String response3 = restTemplate.getForObject("https://localhost:8082/heartbeat", String.class);
            if (Objects.equals(response1, "heartbeat") && Objects.equals(response2, "heartbeat") && Objects.equals(response3, "heartbeat")) {
                System.out.println("all services sre alive");
            }
        } catch (Exception e) {
            System.err.println("Service is down: " + e.getMessage());
        }
    }
}
