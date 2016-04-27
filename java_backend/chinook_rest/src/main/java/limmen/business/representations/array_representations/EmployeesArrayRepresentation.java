package limmen.business.representations.array_representations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.business.representations.entity_representation.EmployeeRepresentation;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * JSON-Representation of a Array of employees.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class EmployeesArrayRepresentation extends ResourceSupport {
    private final List<EmployeeRepresentation> employees;

    /**
     * Class constructor. Initializes the employees property of the JSON representation.
     *
     * @param employees value of the employees property.
     */
    @JsonCreator
    public EmployeesArrayRepresentation(@JsonProperty("employees") List<EmployeeRepresentation> employees) {
        this.employees = employees;
    }

    public List<EmployeeRepresentation> getEmployees() {
        return employees;
    }
}
