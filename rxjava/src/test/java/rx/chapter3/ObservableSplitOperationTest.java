package rx.chapter3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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