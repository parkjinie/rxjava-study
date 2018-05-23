package rx.chapter3;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jinie@coupang.com
 * @since 2018. 5. 15.
 */
public class ObservableFilterOperationTest {

    private ObservableFilterOperation observableFilterOperation;

    @Before
    public void setUp() {
        observableFilterOperation = new ObservableFilterOperation();
    }

    @Test
    public void filter() {
        observableFilterOperation.filter();
    }
}