package limmen.business.representations.entity_representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.integration.entities.Employee;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class EmployeeRepresentation extends ResourceSupport {
    private final Employee employee;

    @JsonCreator
    public EmployeeRepresentation(@JsonProperty("employee") Employee employee) {
        this.employee = employee;
    }

    public Employee getemployee() {
        return employee;
    }
}
