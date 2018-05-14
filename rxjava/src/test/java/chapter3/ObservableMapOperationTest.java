package chapter3;

import org.junit.Before;
import org.junit.Test;

public class ObservableMapOperationTest {

    private ObservableMapOperation observableMapOperation;

    @Before
    public void setUp() {
        observableMapOperation = new ObservableMapOperation();
    }

    @Test
    public void map() {
        observableMapOperation.map();
    }

    @Test
    public void flatMap() {
        observableMapOperation.flatMap();
    }
}