package rx.chapter3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jinie@coupang.com
 * @since 2018. 5. 29.
 */
public class ObservableSplitOperationTest {

    private ObservableSplitOperation observableSplitOperation;

    @Before
    public void setUp() {
        observableSplitOperation = new ObservableSplitOperation();
    }

    @Test
    public void groupBy() {
        observableSplitOperation.groupBy();
    }
}