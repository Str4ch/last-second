package lastsecond.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class RetrofitConfiguration {
    private static String BASE_URL_HOTEL = "https://localhost:8081/";
    private static String BASE_URL_FLIGHT = "https://localhost:8080/";
    private static String BASE_URL_FLIGHT2 = "https://localhost:8082/";
    private static boolean order = false;


    public static Retrofit getClient(String client_type) throws KeyManagementException, NoSuchAlgorithmException {
        System.out.println(1);
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {}

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {}

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0]);
        builder.hostnameVerifier((hostname, session) -> true);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX") // Устанавливаем нужный формат
                .create();

        OkHttpClient okHttpClient = builder.build();
        if(client_type.equals("hotel")) {
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL_HOTEL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        else {
            order = !order;
            return new Retrofit.Builder()
                    .baseUrl(order?BASE_URL_FLIGHT:BASE_URL_FLIGHT2)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
    }

}
