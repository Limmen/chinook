package limmen.controllers;

import limmen.business.representations.array_representations.EmployeesArrayRepresentation;
import limmen.business.representations.entity_representation.EmployeeRepresentation;
import limmen.business.services.EmployeeService;
import limmen.integration.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * RestController for the resource pointed by the url: /resources/employees*
 *
 * @author Kim Hammar on 2016-03-22.
 */
@RestController
@CrossOrigin
@RequestMapping("/resources/employees")
public class EmployeeController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final EmployeeService employeeService;

    /**
     * Method to be injected by the Spring container. Initializes the service.
     *
     * @param employeeService service that handles employee-data
     */
    @Inject
    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Method to handle HTTP-requests for /resources/employees
     *
     * @return HTTP-response, JSON array of employees
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EmployeesArrayRepresentation> getAllEmployees() {
        log.debug("HTTP GET-request /resources/employees");
        List<EmployeeRepresentation> employeeRepresentations = new ArrayList();
        List<Employee> employees = employeeService.getAllEmployees();
        employees.forEach((employee) -> {
            EmployeeRepresentation employeeRepresentation = new EmployeeRepresentation(employee);
            employeeRepresentation.add(linkTo(methodOn(EmployeeController.class).getEmployee(employee.getEmployeeId())).withSelfRel());
            employeeRepresentation.add(linkTo(methodOn(EmployeeController.class).
                    getEmployee(employeeRepresentation.getEmployee().getReportsTo())).withRel("reportsTo"));
            employeeRepresentations.add(employeeRepresentation);
        });
        EmployeesArrayRepresentation arrayRepresentation = new EmployeesArrayRepresentation(employeeRepresentations);
        return new ResponseEntity<EmployeesArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP-requests for /resources/employees/{employeeId}
     * @param employeeId id of the employee
     * @return HTTP-response, JSON representation of the employee
     */
    @CrossOrigin
    @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EmployeeRepresentation> getEmployee(@PathVariable int employeeId) {
        log.debug("HTTP GET-request /resources/employees/{}", employeeId);
        EmployeeRepresentation employeeRepresentation = new EmployeeRepresentation(employeeService.getEmployee(employeeId));
        employeeRepresentation.add(linkTo(methodOn(EmployeeController.class).getEmployee(employeeId)).withSelfRel());
        return new ResponseEntity<EmployeeRepresentation>(employeeRepresentation, HttpStatus.OK);
    }
}
