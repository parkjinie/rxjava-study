package rx.chapter3;

import org.junit.Before;
import org.junit.Test;

public class ObservableScanOperationTest {

    private ObservableScanOperation observableScanOperation;

    @Before
    public void setUp() {
        observableScanOperation = new ObservableScanOperation();
    }

    @Test
    public void scan() {
        observableScanOperation.scan();
    }

    @Test
    public void scanWithInitialValue() {
        int to = 10;
        observableScanOperation.scanWithInitialValue(to);
        observableScanOperation.scanWithInitialValueAndLambda(to);
    }

    @Test
    public void reduce() {
        observableScanOperation.reduce();
    }

    @Test
    public void collectList() {
        observableScanOperation.collectListByReduce();
        observableScanOperation.collectToList();
    }

    @Test
    public void collectString() {
        observableScanOperation.collectToString();
    }

    @Test
    public void distinct() {
        observableScanOperation.distinct();
    }

    @Test
    public void distinctUntilChanged() {
        observableScanOperation.distinctUntilChanged();
    }

    @Test
    public void take() {
        observableScanOperation.take();
    }
}