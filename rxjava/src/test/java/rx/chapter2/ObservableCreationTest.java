package rx.chapter2;

import org.junit.Before;
import org.junit.Test;
import rx.Observable;

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
    }

    @Test
    public void error() {
        observableCreation.error();
    }

    @Test
    public void defer() {
        observableCreation.defer(Observable.range(1, 3));
    }
}