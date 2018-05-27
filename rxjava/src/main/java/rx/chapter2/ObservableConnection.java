package rx.chapter2;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Subscription;
import rx.observables.ConnectableObservable;
import rx.subscriptions.Subscriptions;

/**
 * @author jinie@coupang.com
 * @since 2018. 5. 27.
 */
@Slf4j
class ObservableConnection {

    private Observable<String> observable = Observable.create(subscriber -> {
        log.info("Establishing connection");
        subscriber.add(Subscriptions.create(() -> log.info("Disconnecting")));
    });

    void connectOneByOne() {
        Subscription subscription1 = observable.subscribe();
        log.info("Subscribed 1");
        Subscription subscription2 = observable.subscribe();
        log.info("Subscribed 2");

        subscription1.unsubscribe();
        log.info("Unsubscribed 1");
        subscription2.unsubscribe();
        log.info("Unsubscribed 2");
    }

    void connectByRefCount() {
        Observable<String> lazy = observable.publish().refCount();
        Subscription subscription1 = lazy.subscribe();
        log.info("Subscribed 1");
        Subscription subscription2 = lazy.subscribe();
        log.info("Subscribed 2");

        subscription1.unsubscribe();
        log.info("Unsubscribed 1");
        subscription2.unsubscribe();
        log.info("Unsubscribed 2");
    }

    void connectByConnect() {
        ConnectableObservable<String> published = observable.publish();
        published.connect();
    }
}
