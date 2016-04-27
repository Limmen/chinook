package limmen.business.representations.entity_representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.integration.entities.Employee;
import org.springframework.hateoas.ResourceSupport;

/**
 * JSON-Representation of a Employee entity.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class EmployeeRepresentation extends ResourceSupport {
    private final Employee employee;

    /**
     * Class constructor. Initializes the "employee" property of the JSON representation.
     *
     * @param employee value of the employee property.
     */
    @JsonCreator
    public EmployeeRepresentation(@JsonProperty("employee") Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
