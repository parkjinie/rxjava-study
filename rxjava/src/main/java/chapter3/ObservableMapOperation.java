package chapter3;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;

@Slf4j
class ObservableMapOperation {

    void map() {
        Observable<Integer> numbers = Observable.range(0, 5);
        log.info("Before mapped:");
        numbers.subscribe(number -> log.info("{}", number)); // 0, 1, 2, 3, 4

        Observable<String> mapped= numbers.map(number -> "#" + number);
        log.info("After mapped:");
        mapped.subscribe(log::info); // #0, #1, #2, #3, #4
    }

    void flatMap() {
        Observable<Observable<Integer>> observables = Observable.just(
                Observable.range(0, 2),
                Observable.range(2, 2),
                Observable.range(4, 2)
        );

        Observable<Integer> numbers = observables.flatMap(observable -> observable);
        log.info("After flatten:");
        numbers.subscribe(number -> log.info("{}", number)); // 0, 1, 2, 3, 4, 5
    }
}
