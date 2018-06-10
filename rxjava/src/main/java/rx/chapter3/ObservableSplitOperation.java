package rx.chapter3;

import dto.Employee;
import lombok.extern.slf4j.Slf4j;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

import static dto.EmployeeType.*;

@Slf4j
class ObservableSplitOperation {

    void groupBy() {
        Observable<Employee> employees = Observable.just(
                new Employee(1L, "Jane", FixedTerm),
                new Employee(2L, "John", Other),
                new Employee(3L, "Alice", FullTime),
                new Employee(4L, "Heather", FixedTerm),
                new Employee(5L, "Dean", FullTime),
                new Employee(6L, "Robert", PartTime),
                new Employee(7L, "Robin", FixedTerm),
                new Employee(8L, "Lia", FullTime),
                new Employee(9L, "Sandra", PartTime)
        );

        Observable<List<Employee>> grouped = employees.groupBy(Employee::getType)
                                                      .flatMap(byType -> byType.collect(ArrayList::new, List::add));

        grouped.subscribe(group -> log.info("Group: {}", group));
    }
}
