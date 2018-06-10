package rx.chapter4;

import rx.Observable;

import java.util.List;

/**
 * first: returns first item, discarding the rest.
 * last: returns last item, blocking until Observable completes.
 * single: returns only one item, making sure there is no remaining items after Observable completes.
 *         if not, throws IllegalArgumentException.
 */
class BlockingObservable {
    @SafeVarargs
    final <T> T first(T... ts) {
        return Observable.from(ts)
                         .toBlocking()
                         .first();  // if single() and ts has more than 1, can throw IllegalArgumentException
    }

    @SafeVarargs
    final <T> T last(T... ts) {
        return Observable.from(ts)
                         .toBlocking()
                         .last();
    }

    <T> T single(T t) {
        return Observable.just(t)
                         .toBlocking()
                         .single();
    }

    @SafeVarargs
    final <T> List<T> toList(T... ts) {
        return Observable.from(ts)
                         .toList()
                         .toBlocking()
                         .single();
    }
}
