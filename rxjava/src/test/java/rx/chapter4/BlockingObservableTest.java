package rx.chapter4;

import dto.Employee;
import org.junit.Before;
import org.junit.Test;

import static dto.EmployeeType.FixedTerm;
import static dto.EmployeeType.FullTime;
import static dto.EmployeeType.Other;
import static org.assertj.core.api.Assertions.assertThat;

public class BlockingObservableTest {

    private BlockingObservable blockingObservable;

    @Before
    public void setUp() {
        blockingObservable = new BlockingObservable();
    }

    @Test
    public void first() {
        Employee employee1 = new Employee(1L, "Jane", FixedTerm);
        Employee employee2 = new Employee(2L, "John", Other);
        Employee employee3 = new Employee(3L, "Alice", FullTime);

        assertThat(blockingObservable.first(employee1, employee2, employee3))
                .isEqualTo(employee1);
    }

    @Test
    public void last() {
        Employee employee1 = new Employee(1L, "Jane", FixedTerm);
        Employee employee2 = new Employee(2L, "John", Other);
        Employee employee3 = new Employee(3L, "Alice", FullTime);

        assertThat(blockingObservable.last(employee1, employee2, employee3))
                .isEqualTo(employee3);
    }

    @Test
    public void single() {
        Employee employee = new Employee(1L, "Jane", FixedTerm);

        assertThat(blockingObservable.single(employee))
                .isEqualTo(employee);
    }

    @Test
    public void toList() {
        Employee employee1 = new Employee(1L, "Jane", FixedTerm);
        Employee employee2 = new Employee(2L, "John", Other);
        Employee employee3 = new Employee(3L, "Alice", FullTime);

        assertThat(blockingObservable.toList(employee1, employee2, employee3))
                .containsExactly(employee1, employee2, employee3);
    }
}