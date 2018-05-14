package chapter2;

import org.junit.Before;
import org.junit.Test;

public class ObservableCreationTest {

    private ObservableCreation observableCreation;

    @Before
    public void setUp() {
        observableCreation = new ObservableCreation();
    }

    @Test
    public void just() {
        observableCreation.just();
    }

    @Test
    public void from() {
        observableCreation.from();
    }

    @Test
    public void range() {
        observableCreation.range();
    }

    @Test
    public void empty() {
        observableCreation.empty();
    }

    @Test
    public void never() {
        observableCreation.never();
        observableCreation.error();
    }

    @Test
    public void error() {
        observableCreation.error();
    }
}