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
 * Unit test-suite for the customer service implementation
 *
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

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        customerService = new CustomerServiceImpl((customerRepository));
    }

    /**
     * Test for the getAllCustomers method
     */
    @Test
    public void testGetAllCustomers() {
        final List<Customer> databaseList = new ArrayList();
        when(customerRepository.getAllCustomers()).thenReturn(databaseList);
        List<Customer> returnedList = customerService.getAllCustomers();
        assertEquals("Asserting getAllCustomers", databaseList, returnedList);
    }

    /**
     * Test of the getCustomer method
     */
    @Test
    public void testGetCustomer() {
        when(customerRepository.getCustomer(1)).thenReturn(customerOne);
        when(customerRepository.getCustomer(2)).thenReturn(customerTwo);
        assertEquals("Asserting getCustomer", customerOne, customerService.getCustomer(1));
        assertEquals("Asserting getCustomer", customerTwo, customerService.getCustomer(2));
        assertNotEquals("Asserting getCustomer", customerOne, customerService.getCustomer(2));
        assertNotEquals("Asserting getCustomer", customerTwo, customerService.getCustomer(1));
    }
}