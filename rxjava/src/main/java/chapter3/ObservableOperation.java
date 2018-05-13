package chapter3;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;

@Slf4j
public class ObservableOperation {

    public void filter() {
        Observable<String> strings = Observable.just("#a", "a", "b", "#b", "c");
        log.info("Before filtered:");
        strings.subscribe(log::info);

        Observable<String> filtered = strings.filter(s -> s.startsWith("#"));
        log.info("After filtered:");
        filtered.subscribe(log::info); // #a, #b
    }

    public void map() {
        Observable<Integer> numbers = Observable.range(0, 5);
        log.info("Before mapped:");
        numbers.subscribe(number -> log.info("{}", number)); // 0, 1, 2, 3, 4

        Observable<String> mapped= numbers.map(number -> "#" + number);
        log.info("After mapped:");
        mapped.subscribe(log::info); // #0, #1, #2, #3, #4
    }

    public void flatMap() {
        Observable<Observable<Integer>> observables = Observable.just(
                Observable.range(0, 2),
                Observable.range(2, 2),
                Observable.range(4, 2)
        );

        Observable<Integer> numbers = observables.flatMap(observable -> observable);
        numbers.subscribe(number -> log.info("{}", number)); // 0, 1, 2, 3, 4, 5
    }
}
