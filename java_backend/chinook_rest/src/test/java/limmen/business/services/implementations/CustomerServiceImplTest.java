package limmen.business.services.implementations;

import limmen.business.services.CustomerService;
import limmen.integration.entities.Customer;
import limmen.integration.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

/**
 * @author Kim Hammar on 2016-03-24.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;
    private CustomerService customerService;
    @Mock
    private Customer customerOne;
    @Mock
    private Customer customerTwo;

    @Before
    public void setUp() throws Exception {
        customerService = new CustomerServiceImpl((customerRepository));
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        final List<Customer> databaseList = new ArrayList();
        when(customerRepository.getAllCustomers()).thenReturn(databaseList);
        List<Customer> returnedList = customerService.getAllCustomers();
        assertEquals("Asserting getAllCustomers", databaseList, returnedList);
    }

    @Test
    public void testGetCustomer() throws Exception {
        when(customerRepository.getCustomer(1)).thenReturn(customerOne);
        when(customerRepository.getCustomer(2)).thenReturn(customerTwo);
        assertEquals("Asserting getCustomer", customerOne, customerService.getCustomer(1));
        assertEquals("Asserting getCustomer", customerTwo, customerService.getCustomer(2));
        assertNotEquals("Asserting getCustomer", customerOne, customerService.getCustomer(2));
        assertNotEquals("Asserting getCustomer", customerTwo, customerService.getCustomer(1));
    }
}