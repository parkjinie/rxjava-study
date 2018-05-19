package chapter3;

import org.junit.Before;
import org.junit.Test;

public class ObservableMergeOperationTest {

    private ObservableMergeOperation observableMergeOperation;

    @Before
    public void setUp() {
        observableMergeOperation = new ObservableMergeOperation();
    }

    @Test
    public void merge() {
        observableMergeOperation.merge();
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
    public void combineLatest() {
        observableMergeOperation.combineLatest();
    }

    @Test
    public void withLatestFrom() {
        observableMergeOperation.withLatestFrom();
    }

    @Test
    public void amb() {
        observableMergeOperation.amb();
    }

    @Test
    public void ambWith() {
        observableMergeOperation.ambWith();
    }
}