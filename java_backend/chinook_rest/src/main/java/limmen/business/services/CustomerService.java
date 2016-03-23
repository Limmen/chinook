package limmen.business.services;

import limmen.integration.entities.Customer;

import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public interface CustomerService {

    public List<Customer> getAllCustomers();
    public Customer getCustomer(int customerId);
}
