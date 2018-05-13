package chapter2;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jinie@coupang.com
 * @since 2018. 5. 13.
 */
public class ObservableCreationTest {

    private ObservableCreation observableCreation;

    @Before
    public void setUp() {
        observableCreation = new ObservableCreation();
    }

    @Test
    public void create() {
        observableCreation.just();
        observableCreation.from();
        observableCreation.range();
        observableCreation.empty();
        observableCreation.never();
        observableCreation.error();
    }
}