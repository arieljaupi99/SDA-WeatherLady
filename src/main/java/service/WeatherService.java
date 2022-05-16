package service;

import bean.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gr007.weatherapp.LocationEntity;
import com.gr007.weatherapp.WeatherParametersEntity;
import com.gr007.weatherapp.WindEntity;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class WeatherService {
    public static final String HTTPS_API_OPENWEATHERMAP_ORG_DATA_2_5_WEATHER = "https://api.openweathermap.org/data/2.5/weather";
    private static String FIXED_APPIKEY = "7edda0bea071419a1e1da9c91ac2d4c6";

    public WeatherResponse getLiveWeatherValues(String cityName) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        Request request = new Request.Builder()
                .url(HTTPS_API_OPENWEATHERMAP_ORG_DATA_2_5_WEATHER +"?q="+cityName+"&appid="+ FIXED_APPIKEY)
                .method("GET", null)
                .build();
        /*"?lat=" + lat + "&lon=" + lon + "&APPID=" "Sipas beqos"*/
        ResponseBody responseBody = client.newCall(request).execute().body();
        return objectMapper.readValue(responseBody.string(), WeatherResponse.class);
    }
    public void persistWeather(WeatherParametersEntity weatherParametersEntity, @NotNull SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(weatherParametersEntity);
        transaction.commit();
        session.close();
    }

}