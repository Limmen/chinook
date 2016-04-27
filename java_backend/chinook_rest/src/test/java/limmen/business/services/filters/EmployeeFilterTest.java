package limmen.business.services.filters;

import limmen.integration.entities.Employee;
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
public class EmployeeFilterTest {

    private EmployeeFilter employeeFilter;
    private List<Employee> employees;

    @Mock
    Employee employeeOne;
    @Mock
    Employee employeeTwo;
    @Mock
    Employee employeeThree;
    @Mock
    Employee employeeFour;
    @Mock
    Employee employeeFive;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        employeeFilter = new EmployeeFilter();
        employees = new ArrayList<>();
        employees.add(employeeOne);
        employees.add(employeeTwo);
        employees.add(employeeThree);
        employees.add(employeeFour);
        employees.add(employeeFive);
        when(employeeOne.getEmployeeId()).thenReturn(1);
        when(employeeTwo.getEmployeeId()).thenReturn(2);
        when(employeeThree.getEmployeeId()).thenReturn(3);
        when(employeeFour.getEmployeeId()).thenReturn(4);
        when(employeeFive.getEmployeeId()).thenReturn(5);
    }

    /**
     * Test of the filter method
     */
    @Test
    public void testFilter() {
        employeeFilter.setEmployeeId("1");
        assertEquals("Asserting filter employees", employeeOne, employeeFilter.filter(employees).get(0));
        employeeFilter.setEmployeeId("2");
        assertNotEquals("Asserting filter employees", employeeOne, employeeFilter.filter(employees).get(0));
        assertEquals("Asserting filter employees", employeeTwo, employeeFilter.filter(employees).get(0));
    }

    /**
     * Test of the sort method
     */
    @Test
    public void testSort() {
        employeeFilter.setSort("+employeeId");
        assertEquals("Asserting filter employees", employeeOne, employeeFilter.sort(employees).get(0));
        assertEquals("Asserting filter employees", employeeFive, employeeFilter.sort(employees).get(4));
        employeeFilter.setSort("-employeeId");
        assertEquals("Asserting filter employees", employeeFive, employeeFilter.sort(employees).get(0));
        assertEquals("Asserting filter employees", employeeFour, employeeFilter.sort(employees).get(1));
    }
}