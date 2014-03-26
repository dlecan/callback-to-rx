package rest;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

import java.util.List;

public interface OpenWeatherMapService {

    public static class City {

        public String name;

        public Main main;

        @Override
        public String toString() {
            return "Name: " + name + "\n" + "Main: " + main;
        }
    }

    public static class Main {
        public Double temp;
        public Double pressure;

        @Override
        public String toString() {
            return "Temperature: " + temp + " - Pressure: " + pressure;
        }

    }

//    @GET("/data/2.5/weather?mode=json&lang=fr&units=metric")
//    void getWeatherForCity(@Query("q") String city, Callback<City> cb);

    @GET("/data/2.5/weather?mode=json&lang=fr&units=metric")
    Observable<City> getWeatherForCity(@Query("q") String city);

    public static class Factory {
        public static OpenWeatherMapService getInstance() {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://api.openweathermap.org")
                    .setLogLevel(RestAdapter.LogLevel.BASIC)
                    .build();

            return restAdapter.create(OpenWeatherMapService.class);
        }
    }
}
