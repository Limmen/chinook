package limmen.controllers;

import limmen.business.representations.array_representations.CustomersArrayRepresentation;
import limmen.business.representations.entity_representation.CustomerRepresentation;
import limmen.business.services.CustomerService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.CustomerFilter;
import limmen.integration.entities.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * RestController for the resource pointed by the url: /resources/customers*
 *
 * @author Kim Hammar on 2016-03-22.
 */
@RestController
@CrossOrigin
@RequestMapping("/resources/customers")
public class CustomerController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CustomerService customerService;

    /**
     * Method to be injected by the Spring container. Initializes the service.
     *
     * @param customerService service to handle customer-data.
     */
    @Inject
    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Method to handle HTTP GET-requests for /resources/customers
     *
     * @return HTTP-response, JSON array of customers
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<CustomersArrayRepresentation> getAllCustomers(@RequestParam(name = "customerId", required = false) String customerId,
                                                                    @RequestParam(name = "firstName", required = false) String firstName,
                                                                    @RequestParam(name = "lastName", required = false) String lastName,
                                                                    @RequestParam(name = "company", required = false) String company,
                                                                    @RequestParam(name = "address", required = false) String address,
                                                                    @RequestParam(name = "city", required = false) String city,
                                                                    @RequestParam(name = "state", required = false) String state,
                                                                    @RequestParam(name = "country", required = false) String country,
                                                                    @RequestParam(name = "postalCode", required = false) String postalCode,
                                                                    @RequestParam(name = "phone", required = false) String phone,
                                                                    @RequestParam(name = "fax", required = false) String fax,
                                                                    @RequestParam(name = "email", required = false) String email,
                                                                    @RequestParam(name = "supportRepId", required = false) String supportRepId,
                                                                    @RequestParam(name = "sort", required = false) String sort)
            throws SortException {
        log.debug("HTTP GET-request /resources/customers");
        CustomerFilter customerFilter = new CustomerFilter();
        customerFilter.setCustomerId(customerId);
        customerFilter.setFirstName(firstName);
        customerFilter.setLastName(lastName);
        customerFilter.setCompany(company);
        customerFilter.setAddress(address);
        customerFilter.setCity(city);
        customerFilter.setState(state);
        customerFilter.setCountry(country);
        customerFilter.setPostalCode(postalCode);
        customerFilter.setPhone(phone);
        customerFilter.setFax(fax);
        customerFilter.setEmail(email);
        customerFilter.setSupportRepId(supportRepId);
        customerFilter.setSort(sort);
        List<CustomerRepresentation> customerRepresentations = new ArrayList();
        List<Customer> customers = customerService.getAllCustomers(customerFilter);
        customers.forEach((customer) -> {
            CustomerRepresentation customerRepresentation = new CustomerRepresentation(customer);
            customerRepresentation.add(linkTo(methodOn(CustomerController.class).getCustomer(customer.getCustomerId()))
                    .withSelfRel());
            customerRepresentation.add(linkTo(methodOn(EmployeeController.class)
                    .getEmployee(customerRepresentation.getCustomer().getSupportRepId())).withRel("employee"));
            customerRepresentations.add(customerRepresentation);
        });
        CustomersArrayRepresentation arrayRepresentation = new CustomersArrayRepresentation(customerRepresentations);
        return new ResponseEntity<CustomersArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP GET-requests for /resources/customers/{customerId}
     *
     * @param customerId id of the customer.
     * @return HTTP-response, JSON-representation of the customer.
     */
    @CrossOrigin
    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<CustomerRepresentation> getCustomer(@PathVariable int customerId) {
        log.debug("HTTP GET-request /resources/customers/{}", customerId);
        CustomerRepresentation customerRepresentation = new CustomerRepresentation(customerService.getCustomer(customerId));
        customerRepresentation.add(linkTo(methodOn(CustomerController.class).getCustomer(customerId)).withSelfRel());
        customerRepresentation.add(linkTo(methodOn(EmployeeController.class)
                .getEmployee(customerRepresentation.getCustomer().getSupportRepId())).withRel("employee"));
        return new ResponseEntity<CustomerRepresentation>(customerRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP POST-requests for /resources/customers
     *
     * @param customer posted customer data
     * @return HTTP-response, JSON representation of the created customer
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<CustomerRepresentation> createNewCustomer(@RequestBody Customer customer) {
        log.debug("HTTP POST-request /resources/customers");
        CustomerRepresentation customerRepresentation = new CustomerRepresentation(customerService.createNewCustomer(customer));
        customerRepresentation.add(linkTo(methodOn(CustomerController.class).getCustomer(customer.getCustomerId()))
                .withSelfRel());
        customerRepresentation.add(linkTo(methodOn(EmployeeController.class)
                .getEmployee(customerRepresentation.getCustomer().getSupportRepId())).withRel("employee"));
        return new ResponseEntity<CustomerRepresentation>(customerRepresentation, HttpStatus.CREATED);
    }

    /**
     * Method to handle HTTP PUT-requests for /resources/customers/{customerId}
     *
     * @param customerId id of the customer
     * @param customer   put customer data
     * @return HTTP-Response, JSON representation of the updated customer representation
     */
    @CrossOrigin
    @RequestMapping(value = "/{customerId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<CustomerRepresentation> updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) {
        log.debug("HTTP PUT-request /resources/customers/{}", customerId);
        customer.setCustomerId(customerId);
        CustomerRepresentation customerRepresentation = new CustomerRepresentation(customerService.updateCustomer(customer));
        customerRepresentation.add(linkTo(methodOn(CustomerController.class).getCustomer(customer.getCustomerId()))
                .withSelfRel());
        customerRepresentation.add(linkTo(methodOn(EmployeeController.class)
                .getEmployee(customerRepresentation.getCustomer().getSupportRepId())).withRel("employee"));
        return new ResponseEntity<CustomerRepresentation>(customerRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle PUT-requests for /resources/customers
     *
     * @param putCustomers
     * @return HTTP-Response, JSON representation of the updated customers resource
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<CustomersArrayRepresentation> updateCustomers(@RequestBody List<Customer> putCustomers) {
        log.debug("HTTP PUT-request /resources/customers");
        List<CustomerRepresentation> customerRepresentations = new ArrayList();
        List<Customer> customers = customerService.updateCustomers(putCustomers);
        customers.forEach((customer) -> {
            CustomerRepresentation customerRepresentation = new CustomerRepresentation(customer);
            customerRepresentation.add(linkTo(methodOn(CustomerController.class).getCustomer(customer.getCustomerId()))
                    .withSelfRel());
            customerRepresentation.add(linkTo(methodOn(EmployeeController.class)
                    .getEmployee(customerRepresentation.getCustomer().getSupportRepId())).withRel("employee"));
            customerRepresentations.add(customerRepresentation);
        });
        CustomersArrayRepresentation arrayRepresentation = new CustomersArrayRepresentation(customerRepresentations);
        return new ResponseEntity<CustomersArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP DELETE-requests for /resources/customers/{customerId}
     *
     * @param customerId id of the customer
     * @return HTTP-Response, JSON representation of the deleted customer.
     */
    @CrossOrigin
    @RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<CustomerRepresentation> deleteCustomer(@PathVariable int customerId) {
        log.debug("HTTP DELETE-request /resources/customers/{}", customerId);
        CustomerRepresentation customerRepresentation = new CustomerRepresentation(customerService.deleteCustomer(customerId));
        customerRepresentation.add(linkTo(methodOn(CustomerController.class).getCustomer(customerId))
                .withSelfRel());
        customerRepresentation.add(linkTo(methodOn(EmployeeController.class)
                .getEmployee(customerRepresentation.getCustomer().getSupportRepId())).withRel("employee"));
        return new ResponseEntity<CustomerRepresentation>(customerRepresentation, HttpStatus.OK);
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
