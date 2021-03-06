package rx.chapter2;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

import static java.lang.Thread.sleep;

@Slf4j
class ObservableSubscription {

    void subscribeByObserver() {
        Observable<String> strings = Observable.create(subscriber -> subscriber.onNext("Observable created"));

        // 1. Subscribe by Observer
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
    }

    void subscribeByParameters() {
        Observable<String> strings = Observable.create(subscriber -> subscriber.onNext("Observable created"));

        // 2. Subscribe by parameters using lambda, method reference
        strings.subscribe(
                log::info,
                e -> log.warn("Failed to subscribe", e),
                this::noMore
        );
    }

    private void noMore() {
        log.info("Stopped subscription");
    }

    /**
     * Unsubscribe is used to prevent memory leak
     */
    void unsubscribe() throws InterruptedException {
        Observable<String> observable = Observable.create(subscriber -> {
            // Just example, using thread in create is Not recommended
            Runnable runnable = () -> subscriber.onNext("Subscribing");
            Thread thread = new Thread(runnable);
            thread.start();

            // Add callback to run when unsubscribed
            subscriber.add(Subscriptions.create(() -> {
                thread.interrupt();
                subscriber.onNext("Unsubscribed");
            }));
        });

        // Unsubscribe subscription
        Subscription subscription = observable.subscribe(log::info);
        sleep(100L);
        subscription.unsubscribe();
    }
}
