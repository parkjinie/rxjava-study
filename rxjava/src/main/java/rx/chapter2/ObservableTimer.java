package rx.chapter2;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * @author jinie@coupang.com
 * @since 2018. 5. 27.
 */
@Slf4j
class ObservableTimer {

    void timer(long delay, TimeUnit unit) {
        Observable.timer(delay, unit)
                  .subscribe(zero -> log.info("{}", zero));
    }

    void interval(long interval, TimeUnit unit) {
        Observable.interval(interval, unit)
                  .subscribe(count -> log.info("{}", count));
    }
}
