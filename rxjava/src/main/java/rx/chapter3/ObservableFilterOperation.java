package rx.chapter3;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;

@Slf4j
class ObservableFilterOperation {

    void filter() {
        Observable<String> strings = Observable.just("#a", "a", "b", "#b", "c");
        log.info("Before filtered:");
        strings.subscribe(log::info);

        Observable<String> filtered = strings.filter(s -> s.startsWith("#"));
        log.info("After filtered:");
        filtered.subscribe(log::info); // #a, #b
    }
}
