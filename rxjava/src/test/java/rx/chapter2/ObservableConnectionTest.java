package rx.chapter2;

import org.junit.Before;
import org.junit.Test;

public class ObservableConnectionTest {

    private ObservableConnection observableConnection;

    @Before
    public void setUp() {
        observableConnection = new ObservableConnection();
    }

    @Test
    public void connectOneByOne() {
        observableConnection.connectOneByOne();
    }

    @Test
    public void connectByRefCount() {
        observableConnection.connectByRefCount();
    }

    @Test
    public void connectByConnect() {
        observableConnection.connectByConnect();
    }
}