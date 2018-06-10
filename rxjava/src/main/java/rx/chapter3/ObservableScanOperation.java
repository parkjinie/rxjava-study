package rx.chapter3;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
class ObservableScanOperation {

    /**
     * useful when both intermediate progress and result values are desired.
     */
    void scan() {
        Observable<Long> progress = Observable.just(10L, 14L, 12L, 13L, 14L, 16L);
        Observable<Long> totalProgress = progress.scan((total, chunk) -> total + chunk); // 10L, 24L, 36L, 49L, 63L, 79L

        Observable<String> zipped = Observable.zip(
            progress,
            totalProgress,
            (chunk, total) -> chunk + " / " + total
        );

        zipped.subscribe(log::info);
    }

    void scanWithInitialValue(int to) {
        Observable<BigInteger> factorials = Observable.range(2, to - 1)
                                                      .scan(BigInteger.ONE, (prev, cur) -> prev.multiply(BigInteger.valueOf(cur)));

        Observable<String> zipped = Observable.zip(
            Observable.range(1, to),
            factorials,
            (cur, factorial) -> cur + "! = " + factorial
        );

        log.info("Factorial 1");
        zipped.subscribe(log::info);
    }


    void scanWithInitialValueAndLambda(int to) {
        Observable<BigInteger> factorials = Observable.range(2, to - 1)
                                                      .map(BigInteger::valueOf)
                                                      .scan(BigInteger.ONE, BigInteger::multiply);

        Observable<String> zipped = Observable.zip(
            Observable.range(1, to),
            factorials,
            (cur, factorial) -> cur + "! = " + factorial
        );

        log.info("Factorial 2");
        zipped.subscribe(log::info);
    }

    /**
     * useful when only the result value is desired.
     */
    void reduce() {
        Observable<BigInteger> total = Observable.just(1, 10, 5, 6, 3)
                                                 .map(BigInteger::valueOf)
                                                 .reduce(BigInteger::add);

        total.subscribe(val -> log.info("total: {}", val)); // 25
    }
    
    void collectListByReduce() {
        Observable<List<Object>> all = Observable.range(1, 10)
                                                 .reduce(new ArrayList<>(), (list, item) -> {
                                                     list.add(item);
                                                     return list;
                                                 });

        log.info("Collect list by reduce");
        all.subscribe(list -> log.info("list: {}", list)); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    }

    void collectToList() {
        Observable<List<Object>> all = Observable.range(1, 10)
                                                 .collect(ArrayList::new, List::add);

        log.info("Collect list");
        all.subscribe(list -> log.info("list: {}", list)); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    }

    void collectToString() {
        Observable<String> str = Observable.range(1, 10)
                                           .collect(StringBuilder::new, (sb, number) -> sb.append(number).append(", "))
                                           .map(StringBuilder::toString);

        str.subscribe(log::info); // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
    }

    void distinct() {
        List<Integer> numbers = Arrays.asList(1, 4, 3, 2, 6, 3, 1, 6, 7, 3, 5, 9, 8, 4, 8, 7, 6, 9, 3);
        Observable<Integer> distinctNumbers = Observable.from(numbers)
                                                        .distinct();

        distinctNumbers.subscribe(number -> log.info("number: {}", number)); // 1, 4, 3, 2, 6, 7, 5, 9, 8
    }

    void distinctUntilChanged() {
        List<Integer> temperaturesPerMinute = Arrays.asList(23, 23, 23, 23, 23, 24, 24, 24, 25, 25, 25, 27, 27, 25);
        Observable<Integer> changedTemperatures = Observable.from(temperaturesPerMinute)
                                                            .distinctUntilChanged();

        changedTemperatures.subscribe(number -> log.info("number: {}", number)); // 23, 24, 25, 27, 25
    }

    void take() {
        Observable<String> numbers = Observable.range(1, 5)
                                               .map(Object::toString);

        toList(numbers.take(3)).subscribe(list -> log.info("take 3: {}", list)); // [1, 2, 3]
        toList(numbers.skip(3)).subscribe(list -> log.info("skip 3: {}", list)); // [4, 5]
        toList(numbers.skip(5)).subscribe(list -> log.info("skip 5: {}", list)); // []
        toList(numbers.skip(1).take(3)).subscribe(list -> log.info("skip 1, take 3: {}", list)); // [2, 3, 4]
        toList(numbers.takeLast(2)).subscribe(list -> log.info("takeLast 2: {}", list)); // [4, 5]
        toList(numbers.skipLast(2)).subscribe(list -> log.info("skipLast 2: {}", list)); // [1, 2, 3]
        toList(numbers.first()).subscribe(list -> log.info("first: {}", list)); // [1]
        toList(numbers.last()).subscribe(list -> log.info("last: {}", list)); // [5]
    }

    private <T> Observable<List<T>> toList(Observable<T> observable) {
        return observable.collect(ArrayList::new, List::add);
    }
}
