package rest;

import com.google.gson.JsonElement;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @GET("/data/2.5/weather?mode=json&lang=fr&units=metric")
    void getASyncWeatherForCity(@Query("q") String city, Callback<City> cb);

    @GET("/data/2.5/find?type=like&mode=json")
    void rawASyncSearchWeatherForCity(@Query("q") String pattern, Callback<JsonElement> cb);

    @GET("/data/2.5/weather?mode=json&lang=fr&units=metric")
    Observable<City> getWeatherForCity(@Query("q") String city);

    @GET("/data/2.5/find?type=like&mode=json")
    Observable<JsonElement> rawSearchWeatherForCity(@Query("q") String pattern);


    public static class Factory {
        public static OpenWeatherMapService getInstance() {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://api.openweathermap.org")
                    .setLogLevel(RestAdapter.LogLevel.BASIC)
                    .build();

            return restAdapter.create(OpenWeatherMapService.class);
        }
    }

    public static class Helpers {

        public static Observable<String> toCity(Observable<JsonElement> oje) {
            return oje
                    .map(je -> je.getAsJsonObject().get("list").getAsJsonArray())
                    // Observable<Iterable<<JsonElement>>
                    .flatMap(elt -> Observable.from(elt))
                    // Observable<JsonElement>
                    .map(elt -> elt.getAsJsonObject().get("name").getAsString());
        }

        public static List<String> asyncToCity(JsonElement oje) {
             return StreamSupport
                     .stream(oje.getAsJsonObject().get("list").getAsJsonArray().spliterator(), false)
                     .map(elt -> elt.getAsJsonObject().get("name").getAsString())
                     .collect(Collectors.toList());
        }
    }
}
