package limmen.business.services.filters;

import limmen.integration.entities.Customer;
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
 * @author Kim Hammar on 2016-04-27.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerFilterTest {

    private CustomerFilter customerFilter;
    private List<Customer> customers;

    @Mock
    Customer customerOne;
    @Mock
    Customer customerTwo;
    @Mock
    Customer customerThree;
    @Mock
    Customer customerFour;
    @Mock
    Customer customerFive;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        customerFilter = new CustomerFilter();
        customers = new ArrayList<>();
        customers.add(customerOne);
        customers.add(customerTwo);
        customers.add(customerThree);
        customers.add(customerFour);
        customers.add(customerFive);
        when(customerOne.getCustomerId()).thenReturn(1);
        when(customerTwo.getCustomerId()).thenReturn(2);
        when(customerThree.getCustomerId()).thenReturn(3);
        when(customerFour.getCustomerId()).thenReturn(4);
        when(customerFive.getCustomerId()).thenReturn(5);
    }

    /**
     * Test of the filter method
     */
    @Test
    public void testFilter() {
        customerFilter.setCustomerId("1");
        assertEquals("Asserting filter customers", customerOne, customerFilter.filter(customers).get(0));
        customerFilter.setCustomerId("2");
        assertNotEquals("Asserting filter customers", customerOne, customerFilter.filter(customers).get(0));
        assertEquals("Asserting filter customers", customerTwo, customerFilter.filter(customers).get(0));
    }

    /**
     * Test of the sort method
     */
    @Test
    public void testSort() {
        customerFilter.setSort("+customerId");
        assertEquals("Asserting filter customers", customerOne, customerFilter.sort(customers).get(0));
        assertEquals("Asserting filter customers", customerFive, customerFilter.sort(customers).get(4));
        customerFilter.setSort("-customerId");
        assertEquals("Asserting filter customers", customerFive, customerFilter.sort(customers).get(0));
        assertEquals("Asserting filter customers", customerFour, customerFilter.sort(customers).get(1));
    }
}