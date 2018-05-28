package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author jinie@coupang.com
 * @since 2018. 5. 29.
 */
@Getter
@ToString
@AllArgsConstructor
public class Employee {
    private final Long id;
    private final String name;
    private final EmployeeType type;
}