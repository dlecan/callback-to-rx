package rx;

import java.util.Scanner;

public class ObservableScanner {

    public static Observable<String> create() {
        return Observable.create((Observable.OnSubscribe<String>) (subscriber) -> {

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
    }
}
