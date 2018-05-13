package chapter2;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Observer;

/**
 * @author jinie@coupang.com
 * @since 2018. 5. 13.
 */
@Slf4j
public class ObservableSubscription {

    public void subscribe() {
        Observable<String> strings = Observable.create(subscriber -> {
            subscriber.onNext("Observable created");
        });

        // 1. Subscribe with Observer
        strings.subscribe(new Observer<String>() {
            @Override
            public void onNext(String s) {
                log.info(s);
            }

            @Override
            public void onError(Throwable e) {
                log.warn("Failed to subscribe", e);
            }

            @Override
            public void onCompleted() {
                noMore();
            }
        });

        // 2. Subscribe with lambda, method reference
        strings.subscribe(
                log::info,
                e -> log.warn("Failed to subscribe", e),
                this::noMore
        );
    }

    private void noMore() {
        log.info("Stopped subscription");
    }
}
