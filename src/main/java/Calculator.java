import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Calculator {

    public static void main(String[] args) throws Exception {

        Observable<String> observeur = Observable.create((Observable.OnSubscribe<String>) (subscriber) -> {

            Scanner scanner = new Scanner(System.in);

            new Thread(() -> {
                while (!subscriber.isUnsubscribed()) {
                    String ligne = scanner.nextLine();
                    subscriber.onNext(ligne);
                }

                if (subscriber.isUnsubscribed()) {
                    scanner.close();
                }

                subscriber.onCompleted();
            }).start();

        });


        observeur.
                throttleWithTimeout(800, TimeUnit.MILLISECONDS).
                subscribe(ligne -> System.out.println("Lu : " + ligne));

        Thread.sleep(60000);

    }


//
//        System.out.println(scanner.nextLine());
//        System.out.println(scanner.nextLine());
}
