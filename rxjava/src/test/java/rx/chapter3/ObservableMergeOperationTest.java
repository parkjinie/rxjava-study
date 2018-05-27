package rx.chapter3;

import org.junit.Before;
import org.junit.Test;

import static java.lang.Thread.sleep;

public class ObservableMergeOperationTest {

    private ObservableMergeOperation observableMergeOperation;

    @Before
    public void setUp() {
        observableMergeOperation = new ObservableMergeOperation();
    }

    @Test
    public void merge() throws InterruptedException {
        observableMergeOperation.merge();
        sleep(1000L);
    }

    @Test
    public void concat() {
        observableMergeOperation.concat();
    }

    @Test
    public void zip() {
        observableMergeOperation.zip();
    }

    @Test
    public void zipWith() {
        observableMergeOperation.zipWith();
    }

    @Test
    public void combineLatest() throws InterruptedException {
        observableMergeOperation.combineLatest();
        sleep(1000L);
    }

    @Test
    public void withLatestFrom() throws InterruptedException {
        observableMergeOperation.withLatestFrom();
        sleep(1000L);
    }

    @Test
    public void amb() throws InterruptedException {
        observableMergeOperation.amb();
        sleep(1000L);
    }

    @Test
    public void ambWith() throws InterruptedException {
        observableMergeOperation.ambWith();
        sleep(1000L);
    }
}