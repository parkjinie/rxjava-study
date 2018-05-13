package chapter3;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ObservableMergeOperation {

    public void merge() {
        Observable<String> animals = Observable.just("Rabbit", "Tiger", "Panda");
        Observable<String> fruits = Observable.just("Mango", "Apple", "Orange");
        Observable<String> countries = Observable.just("Korea", "Japan", "China");

        Observable<String> merged = Observable.merge(animals, fruits, countries);
        merged.subscribe(log::info); // Rabbit, Tiger, Panda, Mango, Apple, Orange, Korea, Japan, China
    }

    public void zip() {
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

    public void zipWith() {
        Observable<String> countries = Observable.just("Korea", "Japan", "China");
        Observable<String> capitals = Observable.just("Seoul", "Tokyo", "Beijing");

        Observable<String> zipped = countries.zipWith(
                capitals,
                (country, capital) -> country + "-" + capital
        );
        zipped.subscribe(log::info); // Korea-Seoul, Japan-Tokyo, China-Beijing
    }

    /**
     * logged when slowInterval or fastInterval notified
     * @throws InterruptedException
     */
    public void combineLatest() throws InterruptedException {
        Observable<String> slowInterval = Observable.interval(17, TimeUnit.MILLISECONDS).map(s -> "S" + s);
        Observable<String> fastInterval = Observable.interval(10, TimeUnit.MILLISECONDS).map(f -> "F" + f);

        Observable<String> combined = Observable.combineLatest(
                slowInterval,
                fastInterval,
                (s, f) -> f + ":" + s
        );
        combined.forEach(log::info); // F0:S0, F1:S0, F2:S0, F2:S1, F3:S1, ...

        Thread.sleep(1000); // added because of running 'combined' during that time.
    }

    /**
     * logged only when slowInterval notified
     * @throws InterruptedException
     */
    public void withLatestFrom() throws InterruptedException {
        Observable<String> slowInterval = Observable.interval(17, TimeUnit.MILLISECONDS).map(s -> "S" + s);
        Observable<String> fastInterval = Observable.interval(10, TimeUnit.MILLISECONDS).map(f -> "F" + f);

        Observable<String> fromSlowInterval = slowInterval.withLatestFrom(
                fastInterval,
                (s, f) -> s + ":" + f
        );
        fromSlowInterval.forEach(log::info); // S0:F1, S1:F2, S2:F4, S3:F5, S4:F7, ...

        Thread.sleep(1000); // added because of running 'fromSlowInterval' during that time.
    }

    // TODO
    public void amb() {

    }

    // TODO
    public void ambWith() {

    }
}
