package rx.chapter3;

import org.junit.Before;
import org.junit.Test;

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