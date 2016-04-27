package limmen.business.services.implementations;

import limmen.business.services.EmployeeService;
import limmen.integration.entities.Employee;
import limmen.integration.repositories.EmployeeRepository;
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
 * Unit test-suite for the employee service implementation
 *
 * @author Kim Hammar on 2016-03-24.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;
    @Mock
    private Employee employeeOne;
    @Mock
    private Employee employeeTwo;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        employeeService = new EmployeeServiceImpl((employeeRepository));
    }

    /**
     * Test for the getAllEmployees method
     */
    @Test
    public void testGetAllEmployees() {
        final List<Employee> databaseList = new ArrayList();
        when(employeeRepository.getAllEmployees()).thenReturn(databaseList);
        List<Employee> returnedList = employeeService.getAllEmployees();
        assertEquals("Asserting getAllEmployees", databaseList, returnedList);
    }

    /**
     * Test of the getEmployee method
     */
    @Test
    public void testGetEmployee() {
        when(employeeRepository.getEmployee(1)).thenReturn(employeeOne);
        when(employeeRepository.getEmployee(2)).thenReturn(employeeTwo);
        assertEquals("Asserting getEmployee", employeeOne, employeeService.getEmployee(1));
        assertEquals("Asserting getEmployee", employeeTwo, employeeService.getEmployee(2));
        assertNotEquals("Asserting getEmployee", employeeOne, employeeService.getEmployee(2));
        assertNotEquals("Asserting getEmployee", employeeTwo, employeeService.getEmployee(1));
    }

    /**
     * Test of the createNewEmployee method
     */
    @Test
    public void testCreateNewEmployee() {
        when(employeeRepository.createNewEmployee(employeeOne)).thenReturn(employeeOne);
        when(employeeRepository.createNewEmployee(employeeTwo)).thenReturn(employeeTwo);
        assertEquals("Asserting getEmployee", employeeOne, employeeService.createNewEmployee(employeeOne));
        assertEquals("Asserting getEmployee", employeeTwo, employeeService.createNewEmployee(employeeTwo));
        assertNotEquals("Asserting getEmployee", employeeOne, employeeService.createNewEmployee(employeeTwo));
        assertNotEquals("Asserting getEmployee", employeeTwo, employeeService.createNewEmployee(employeeOne));
    }

    /**
     * Test of the updateEmployee method
     */
    @Test
    public void testUpdateEmployee() {
        when(employeeRepository.updateEmployee(employeeOne)).thenReturn(employeeOne);
        when(employeeRepository.updateEmployee(employeeTwo)).thenReturn(employeeTwo);
        assertEquals("Asserting getEmployee", employeeOne, employeeService.updateEmployee(employeeOne));
        assertEquals("Asserting getEmployee", employeeTwo, employeeService.updateEmployee(employeeTwo));
        assertNotEquals("Asserting getEmployee", employeeOne, employeeService.updateEmployee(employeeTwo));
        assertNotEquals("Asserting getEmployee", employeeTwo, employeeService.updateEmployee(employeeOne));
    }
}