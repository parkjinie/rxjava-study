package rx.chapter2;

import org.junit.Before;
import org.junit.Test;

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ObservableTimerTest {

    private ObservableTimer observableTimer;

    @Before
    public void setUp() {
        observableTimer = new ObservableTimer();
    }

    @Test
    public void timer() throws InterruptedException {
        long delay = 1L;
        observableTimer.timer(delay, SECONDS);
        sleep(2000L);
    }

    @Test
    public void interval() throws InterruptedException {
        long interval = 1_000_000L / 60L; // 60Hz
        observableTimer.interval(interval, MICROSECONDS);
        sleep(2000L);
    }
}