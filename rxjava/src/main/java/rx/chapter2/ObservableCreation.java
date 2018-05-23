package rx.chapter2;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;

import java.util.Arrays;

@Slf4j
class ObservableCreation {

    void just() {
        log.info("[Observable.just()]");
        Observable.just("Banana", "Kiwi", "Apple")
                  .subscribe(log::info);
    }

    void from() {
        log.info("[Observable.from()]");
        Observable.from(Arrays.asList("Banana", "Kiwi", "Apple"))
                  .subscribe(log::info);
    }

    void range() {
        log.info("[Observable.range()]");
        Observable.range(3, 3)
                  .subscribe(number -> log.info("number: {}", number));
    }

    void empty() {
        log.info("[Observable.empty()]");
        Observable.empty()
                  .subscribe(
                          o -> {}, // Never called
                          e -> {}, // Never called
                          () -> log.info("Called only onCompleted()")
                  );
    }

    void never() {
        log.info("[Observable.never()]");
        Observable.never()
                  .subscribe(
                          o -> {}, // Never called
                          e -> {}, // Never called
                          () -> {} // Never called
                  );
        log.info("Nothing is subscribed");
    }

    void error() {
        log.info("[Observable.error()]");
        Observable.error(new Exception("Observable.never() throws Exception"))
                  .subscribe(
                          o -> {}, // Never called
                          e -> log.warn("Observable.never() notifies error", e),
                          () -> {} // Never called
                  );
    }
}
