package limmen.business.services;

import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.CustomerFilter;
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
     * Method to get all customers (filtered).
     *
     * @param customerFilter properties to filter the list of customers on
     * @return list of filtered customers
     */
    public List<Customer> getAllCustomers(CustomerFilter customerFilter) throws SortException;
    
    /**
     * Method to get a customer with a specified id.
     *
     * @param customerId id of the customer.
     * @return Customer with the specified id.
     */
    public Customer getCustomer(int customerId);

    /**
     * Method to create a new customer.
     *
     * @param customer data of the customer to create
     * @return the created  customer
     */
    public Customer createNewCustomer(Customer customer);

    /**
     * Method to update a customer.
     *
     * @param customer customer to update
     * @return updated customer
     */
    public Customer updateCustomer(Customer customer);

    /**
     * Method to update the list of customers
     *
     * @param customers data to update customers list with
     * @return new list of customers
     */
    public List<Customer> updateCustomers(List<Customer> customers);

    /**
     * Method to delete a customer.
     *
     * @param customerId id of the customer to delete
     * @return the deleted customer.
     */
    public Customer deleteCustomer(int customerId);
}
