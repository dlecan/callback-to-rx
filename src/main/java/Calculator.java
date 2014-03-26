import rest.OpenWeatherMapService;
import rx.Observable;
import rx.ObservableScanner;

import static rest.OpenWeatherMapService.Factory.*;

public class Calculator {

    public static void main(String[] args) throws Exception {

//        Observable<String> observeur = ObservableScanner.create();
//
//        observeur
//                .flatMap(ligne -> getInstance().getWeatherForCity(ligne))
////                .throttleWithTimeout(800, TimeUnit.MILLISECONDS)
//                .subscribe(
//                        city -> System.out.println("City : " + city),
//                        throwable -> System.err.println(throwable.getMessage())
//                );


//        getInstance().getWeatherForCity("Paris", new Callback<OpenWeatherMapService.City>() {
//            @Override
//            public void success(OpenWeatherMapService.City city, Response response) {
//                System.out.println(city);
//            }
//
//            @Override
//            public void failure(RetrofitError retrofitError) {
//
//            }
//        });

        Thread.sleep(60000);

    }

}
