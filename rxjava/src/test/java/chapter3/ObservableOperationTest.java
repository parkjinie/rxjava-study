package chapter3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObservableOperationTest {

    private ObservableOperation observableOperation;

    @Before
    public void setUp() {
        observableOperation = new ObservableOperation();
    }

    @Test
    public void filter() {
        observableOperation.filter();
    }

    @Test
    public void map() {
        observableOperation.map();
    }

    @Test
    public void flatMap() {
        observableOperation.flatMap();
    }
}