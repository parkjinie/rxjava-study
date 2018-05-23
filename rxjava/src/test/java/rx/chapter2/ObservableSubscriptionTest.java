package rx.chapter2;

import org.junit.Before;
import org.junit.Test;

public class ObservableSubscriptionTest {

    private ObservableSubscription observableSubscription;

    @Before
    public void setUp() {
        observableSubscription = new ObservableSubscription();
    }

    @Test
    public void subscribe() {
        observableSubscription.subscribe();
    }
}