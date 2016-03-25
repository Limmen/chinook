package limmen.business.services.implementations;

import limmen.business.services.CustomerService;
import limmen.integration.entities.Customer;
import limmen.integration.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the CustomerService interface, uses a repository for database interaction.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CustomerRepository customerRepository;

    @Inject
    public CustomerServiceImpl(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public Customer getCustomer(int customerId) {
        return customerRepository.getCustomer(customerId);
    }
}
