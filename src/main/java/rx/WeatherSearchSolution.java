package rx;

import static rest.OpenWeatherMapService.Factory.getInstance;
import static rest.OpenWeatherMapService.Helpers.toCity;

public class WeatherSearchSolution {

    public static void main(String[] args) throws Exception {

        ObservableScanner
                .create(System.in)
                .flatMap(pattern -> toCity(getInstance().rawSearchWeatherForCity(pattern)))
                .distinct()
                .take(2)
                .flatMap(city -> getInstance().getWeatherForCity(city))
                .subscribe(elt -> System.out.println(elt), t -> {
                    System.err.println(t);
                    t.printStackTrace();
                });

    }

}
