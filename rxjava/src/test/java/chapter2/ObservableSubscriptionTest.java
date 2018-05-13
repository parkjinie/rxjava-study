package chapter2;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jinie@coupang.com
 * @since 2018. 5. 13.
 */
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