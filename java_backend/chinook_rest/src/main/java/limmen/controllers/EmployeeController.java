package limmen.controllers;

import limmen.business.representations.array_representations.EmployeesArrayRepresentation;
import limmen.business.representations.entity_representation.EmployeeRepresentation;
import limmen.business.services.EmployeeService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.EmployeeFilter;
import limmen.integration.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
     * Method to handle HTTP GET-requests for /resources/employees
     *
     * @return HTTP-response, JSON array of employees
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EmployeesArrayRepresentation> getAllEmployees(@RequestParam(name = "employeeId", required = false) String employeeId,
                                                                    @RequestParam(name = "firstName", required = false) String firstName,
                                                                    @RequestParam(name = "lastName", required = false) String lastName,
                                                                    @RequestParam(name = "title", required = false) String title,
                                                                    @RequestParam(name = "reportsTo", required = false) String reportsTo,
                                                                    @RequestParam(name = "birthDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date birthDate,
                                                                    @RequestParam(name = "hireDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date hireDate,
                                                                    @RequestParam(name = "address", required = false) String address,
                                                                    @RequestParam(name = "city", required = false) String city,
                                                                    @RequestParam(name = "state", required = false) String state,
                                                                    @RequestParam(name = "country", required = false) String country,
                                                                    @RequestParam(name = "postalCode", required = false) String postalCode,
                                                                    @RequestParam(name = "phone", required = false) String phone,
                                                                    @RequestParam(name = "fax", required = false) String fax,
                                                                    @RequestParam(name = "email", required = false) String email,
                                                                    @RequestParam(name = "sort", required = false) String sort)
            throws SortException {
        log.debug("HTTP GET-request /resources/employees");
        EmployeeFilter employeeFilter = new EmployeeFilter();
        employeeFilter.setEmployeeId(employeeId);
        employeeFilter.setFirstName(firstName);
        employeeFilter.setLastName(lastName);
        employeeFilter.setTitle(title);
        employeeFilter.setReportsTo(reportsTo);
        employeeFilter.setBirthDate(birthDate);
        employeeFilter.setHireDate(hireDate);
        employeeFilter.setAddress(address);
        employeeFilter.setCity(city);
        employeeFilter.setState(state);
        employeeFilter.setCountry(country);
        employeeFilter.setPostalCode(postalCode);
        employeeFilter.setPhone(phone);
        employeeFilter.setFax(fax);
        employeeFilter.setEmail(email);
        employeeFilter.setSort(sort);
        List<EmployeeRepresentation> employeeRepresentations = new ArrayList();
        List<Employee> employees = employeeService.getAllEmployees(employeeFilter);
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
     * Method to handle HTTP GET-requests for /resources/employees/{employeeId}
     *
     * @param employeeId id of the employee
     * @return HTTP-response, JSON representation of the employee
     */
    @CrossOrigin
    @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EmployeeRepresentation> getEmployee(@PathVariable int employeeId) {
        log.debug("HTTP GET-request /resources/employees/{}", employeeId);
        EmployeeRepresentation employeeRepresentation = new EmployeeRepresentation(employeeService.getEmployee(employeeId));
        employeeRepresentation.add(linkTo(methodOn(EmployeeController.class).getEmployee(employeeId)).withSelfRel());
        employeeRepresentation.add(linkTo(methodOn(EmployeeController.class).
                getEmployee(employeeRepresentation.getEmployee().getReportsTo())).withRel("reportsTo"));
        return new ResponseEntity<EmployeeRepresentation>(employeeRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP POST-requests for /resources/employees
     *
     * @param employee posted employee data
     * @return HTTP-response, JSON representation of the created employee
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EmployeeRepresentation> createNewEmployee(@RequestBody Employee employee) {
        log.debug("HTTP POST-request /resources/employees");
        EmployeeRepresentation employeeRepresentation = new EmployeeRepresentation(employeeService.createNewEmployee(employee));
        employeeRepresentation.add(linkTo(methodOn(EmployeeController.class).getEmployee(employee.getEmployeeId())).withSelfRel());
        employeeRepresentation.add(linkTo(methodOn(EmployeeController.class).
                getEmployee(employeeRepresentation.getEmployee().getReportsTo())).withRel("reportsTo"));
        return new ResponseEntity<EmployeeRepresentation>(employeeRepresentation, HttpStatus.CREATED);
    }

    /**
     * Method to handle HTTP PUT-requests for /resources/employees/{employeeId}
     *
     * @param employeeId id of the employee
     * @param employee   put employee data
     * @return HTTP-Response, JSON representation of the updated employee representation
     */
    @CrossOrigin
    @RequestMapping(value = "/{employeeId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EmployeeRepresentation> updateEmployee(@PathVariable int employeeId, @RequestBody Employee employee) {
        log.debug("HTTP PUT-request /resources/employees/{}", employeeId);
        employee.setEmployeeId(employeeId);
        EmployeeRepresentation employeeRepresentation = new EmployeeRepresentation(employeeService.updateEmployee(employee));
        employeeRepresentation.add(linkTo(methodOn(EmployeeController.class).getEmployee(employee.getEmployeeId())).withSelfRel());
        employeeRepresentation.add(linkTo(methodOn(EmployeeController.class).
                getEmployee(employeeRepresentation.getEmployee().getReportsTo())).withRel("reportsTo"));
        return new ResponseEntity<EmployeeRepresentation>(employeeRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle PUT-requests for /resources/employees
     *
     * @param putEmployees
     * @return HTTP-Response, JSON representation of the updated employees resource
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EmployeesArrayRepresentation> updateEmployees(@RequestBody List<Employee> putEmployees) {
        log.debug("HTTP PUT-request /resources/employees");
        List<EmployeeRepresentation> employeeRepresentations = new ArrayList();
        List<Employee> employees = employeeService.updateEmployees(putEmployees);
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
     * Method to handle HTTP DELETE-requests for /resources/employees/{employeeId}
     *
     * @param employeeId id of the employee
     * @return HTTP-Response, JSON representation of the deleted employee.
     */
    @CrossOrigin
    @RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EmployeeRepresentation> deleteEmployee(@PathVariable int employeeId) {
        log.debug("HTTP DELETE-request /resources/employees/{}", employeeId);
        EmployeeRepresentation employeeRepresentation = new EmployeeRepresentation(employeeService.deleteEmployee(employeeId));
        employeeRepresentation.add(linkTo(methodOn(EmployeeController.class).getEmployee(employeeId)).withSelfRel());
        employeeRepresentation.add(linkTo(methodOn(EmployeeController.class).
                getEmployee(employeeRepresentation.getEmployee().getReportsTo())).withRel("reportsTo"));
        return new ResponseEntity<EmployeeRepresentation>(employeeRepresentation, HttpStatus.OK);
    }

    /**
     * ExceptionHandler for when a requested resource was not found.
     *
     * @param response HTTP response to send back to client
     * @throws IOException
     */
    @ExceptionHandler(org.springframework.dao.EmptyResultDataAccessException.class)
    void notFound(HttpServletResponse response) throws IOException {
        log.debug("org.springframework.dao.EmptyResultDataAccessException.class exception caught");
        response.sendError(HttpStatus.NOT_FOUND.value(), "Resource not Found");
    }

    /**
     * ExceptionHandler for when query string was invalid.
     *
     * @param response HTTP response to send back to client
     * @throws IOException
     */
    @ExceptionHandler(SortException.class)
    void invalidQueryString(HttpServletResponse response) throws IOException {
        log.debug("invalid query string exception caught");
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid Query String");
    }
}
