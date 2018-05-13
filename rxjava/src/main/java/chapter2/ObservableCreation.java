package chapter2;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Observer;

import java.util.Arrays;

@Slf4j
public class ObservableCreation {

    public void just() {
        log.info("[Observable.just()]");
        Observable.just("Banana", "Kiwi", "Apple")
                  .subscribe(log::info);
    }

    public void from() {
        log.info("[Observable.from()]");
        Observable.from(Arrays.asList("Banana", "Kiwi", "Apple"))
                  .subscribe(log::info);
    }

    public void range() {
        log.info("[Observable.range()]");
        Observable.range(3, 3)
                  .subscribe(number -> log.info("number: {}", number));
    }

    public void empty() {
        log.info("[Observable.empty()]");
        Observable.empty()
                  .subscribe(
                          o -> {},
                          e -> log.warn("Observable.empty() never call onError()", e),
                          () -> log.info("Called only onCompleted()")
                  );
    }

    public void never() {
        log.info("[Observable.never()]");
        Observable.never()
                  .subscribe(o -> {});
        log.info("Nothing is subscribed");
    }

    public void error() {
        log.info("[Observable.error()]");
        Observable.error(new Exception("Observable.never() throws Exception"))
                  .subscribe(
                          o -> {},
                          e -> log.warn("Observable.never() notifies error", e),
                          () -> log.info("Observable.never() never call onCompleted()")
                  );
    }
}
