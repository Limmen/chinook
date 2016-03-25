package limmen.business.services;

import limmen.integration.entities.Customer;

import java.util.List;

/**
 * Interface for a service that provides CRUD services for customer-data.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public interface CustomerService {

    /**
     * Method to get all customers.
     *
     * @return list of all customers.
     */
    public List<Customer> getAllCustomers();

    /**
     * Method to get a customer with a specified id.
     *
     * @param customerId id of the customer.
     * @return Customer with the specified id.
     */
    public Customer getCustomer(int customerId);
}
