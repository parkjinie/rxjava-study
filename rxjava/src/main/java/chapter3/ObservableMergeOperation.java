package chapter3;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Slf4j
class ObservableMergeOperation {

    /**
     * put multiple streams in one stream
     */
    void merge() {
        Observable<String> animals = Observable.just("Rabbit", "Tiger", "Panda");
        Observable<String> fruits = Observable.just("Mango", "Apple", "Orange");
        Observable<String> countries = Observable.just("Korea", "Japan", "China");

        Observable<String> merged = Observable.merge(animals, fruits, countries);
        merged.subscribe(log::info); // Rabbit, Tiger, Panda, Mango, Apple, Orange, Korea, Japan, China
    }

    /**
     * tied synchronously
     */
    void zip() {
        Observable<String> continents = Observable.just("Asia", "Asia", "Asia");
        Observable<String> countries = Observable.just("Korea", "Japan", "China");
        Observable<String> capitals = Observable.just("Seoul", "Tokyo", "Beijing");

        Observable<String> zipped = Observable.zip(
                continents,
                countries,
                capitals,
                (continent, country, capital) -> continent + "-" + country + "-" + capital
        );
        zipped.subscribe(log::info); //Asia-Korea-Seoul, Asia-Japan-Tokyo, Asia-China-Beijing
    }

    void zipWith() {
        Observable<String> countries = Observable.just("Korea", "Japan", "China");
        Observable<String> capitals = Observable.just("Seoul", "Tokyo", "Beijing");

        Observable<String> zipped = countries.zipWith(
                capitals,
                (country, capital) -> country + "-" + capital
        );
        zipped.subscribe(log::info); // Korea-Seoul, Japan-Tokyo, China-Beijing
    }

    /**
     * tied when whether 'slowInterval' or 'fastInterval' notified
     */
    void combineLatest() {
        Observable<String> slowInterval = Observable.interval(17, MILLISECONDS).map(s -> "S" + s);
        Observable<String> fastInterval = Observable.interval(10, MILLISECONDS).map(f -> "F" + f);

        Observable<String> combined = Observable.combineLatest(
                slowInterval,
                fastInterval,
                (s, f) -> f + ":" + s
        );
        combined.forEach(log::info); // F0:S0, F1:S0, F2:S0, F2:S1, F3:S1, ...

        executeIntervalStreamWithSleep(1000);
    }

    /**
     * tied only when 'slowInterval' notified
     */
    void withLatestFrom() {
        Observable<String> slowInterval = Observable.interval(17, MILLISECONDS).map(s -> "S" + s);
        Observable<String> fastInterval = Observable.interval(10, MILLISECONDS).map(f -> "F" + f);

        Observable<String> fromSlowInterval = slowInterval.withLatestFrom(
                fastInterval,
                (s, f) -> s + ":" + f
        );
        fromSlowInterval.forEach(log::info); // S0:F1, S1:F2, S2:F4, S3:F5, S4:F7, ...

        executeIntervalStreamWithSleep(1000);
    }

    /**
     * 'second' unsubscribe after 'first' notified
     */
    void amb() {
        Observable<String> first = intervalStream(100, 17, "F");
        Observable<String> second = intervalStream(200, 10, "S");

        Observable<String> amb = Observable.amb(first, second);
        amb.subscribe(log::info); // Subscribe to F, Subscribe to S, Unsubscribe from S, F0, F1, ...

        executeIntervalStreamWithSleep(1000);
    }

    void ambWith() {
        Observable<String> first = intervalStream(100, 17, "F");
        Observable<String> second = intervalStream(200, 10, "S");

        Observable<String> ambWith = first.ambWith(second);
        ambWith.subscribe(log::info); // Subscribe to F, Subscribe to S, Unsubscribe from S, F0, F1, ...

        executeIntervalStreamWithSleep(1000);
    }

    /**
     * run stream during millis.
     */
    private void executeIntervalStreamWithSleep(long millis) {
        try {
            sleep(millis);
        } catch (InterruptedException ignored) {

        }
    }

    private Observable<String> intervalStream(long initialDelay, long period, String streamName) {
        return Observable.interval(initialDelay, period, MILLISECONDS)
                         .map(number -> streamName + number)
                         .doOnSubscribe(() -> log.info("Subscribe to {}", streamName))
                         .doOnUnsubscribe(() -> log.info("Unsubscribe from {}", streamName));
    }
}
