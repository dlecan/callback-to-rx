package callbacks;

import com.google.gson.JsonElement;
import rest.OpenWeatherMapService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.List;
import java.util.Scanner;

import static rest.OpenWeatherMapService.Factory.getInstance;
import static rest.OpenWeatherMapService.Helpers.asyncToCity;

public class WeatherSearch {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String ligne = scanner.nextLine();

            getInstance().rawASyncSearchWeatherForCity(ligne, new Callback<JsonElement>() {
                @Override
                public void success(JsonElement jsonElement, Response response) {
                    List<String> cityList = asyncToCity(jsonElement);


                    int i = 0;
                    for (String city : cityList) {

                        getInstance().getASyncWeatherForCity(city, new Callback<OpenWeatherMapService.City>() {
                            @Override
                            public void success(OpenWeatherMapService.City city, Response response) {
                                System.out.println(city);
                            }

                            @Override
                            public void failure(RetrofitError retrofitError) {
                                retrofitError.printStackTrace();
                            }
                        });

                        i++;
                        if (i == 2) {
                            break;
                        }
                    }
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    retrofitError.printStackTrace();
                }
            });
        }
    }
}
