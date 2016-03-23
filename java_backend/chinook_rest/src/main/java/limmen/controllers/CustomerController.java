package limmen.controllers;

import limmen.business.representations.array_representations.CustomersArrayRepresentation;
import limmen.business.representations.entity_representation.CustomerRepresentation;
import limmen.business.services.CustomerService;
import limmen.integration.entities.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Kim Hammar on 2016-03-22.
 */
@RestController
@RequestMapping("/resources/customers")
public class CustomerController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CustomerService customerService;

    @Inject
    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<CustomersArrayRepresentation> getAllCustomers() {
        log.debug("HTTP GET-request /resources/customers");
        List<CustomerRepresentation> customerRepresentations = new ArrayList();
        List<Customer> customers = customerService.getAllCustomers();
        customers.forEach((customer) -> {
            CustomerRepresentation customerRepresentation = new CustomerRepresentation(customer);
            customerRepresentation.add(linkTo(methodOn(CustomerController.class).getCustomer(customer.getCustomerId()))
                    .withSelfRel());
            customerRepresentations.add(customerRepresentation);
            customerRepresentation.add(linkTo(methodOn(EmployeeController.class)
                    .getEmployee(customerRepresentation.getCustomer().getSupportRepId())).withRel("employee"));
        });
        CustomersArrayRepresentation arrayRepresentation = new CustomersArrayRepresentation(customerRepresentations);
        return new ResponseEntity<CustomersArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<CustomerRepresentation> getCustomer(@PathVariable int customerId) {
        log.debug("HTTP GET-request /resources/customers/{}", customerId);
        CustomerRepresentation customerRepresentation = new CustomerRepresentation(customerService.getCustomer(customerId));
        customerRepresentation.add(linkTo(methodOn(CustomerController.class).getCustomer(customerId)).withSelfRel());
        customerRepresentation.add(linkTo(methodOn(EmployeeController.class)
                .getEmployee(customerRepresentation.getCustomer().getSupportRepId())).withRel("employee"));
        return new ResponseEntity<CustomerRepresentation>(customerRepresentation, HttpStatus.OK);
    }
}
