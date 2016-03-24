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

    @Before
    public void setUp() throws Exception {
        employeeService = new EmployeeServiceImpl((employeeRepository));
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        final List<Employee> databaseList = new ArrayList();
        when(employeeRepository.getAllEmployees()).thenReturn(databaseList);
        List<Employee> returnedList = employeeService.getAllEmployees();
        assertEquals("Asserting getAllEmployees", databaseList, returnedList);
    }

    @Test
    public void testGetEmployee() throws Exception {
        when(employeeRepository.getEmployee(1)).thenReturn(employeeOne);
        when(employeeRepository.getEmployee(2)).thenReturn(employeeTwo);
        assertEquals("Asserting getEmployee", employeeOne, employeeService.getEmployee(1));
        assertEquals("Asserting getEmployee", employeeTwo, employeeService.getEmployee(2));
        assertNotEquals("Asserting getEmployee", employeeOne, employeeService.getEmployee(2));
        assertNotEquals("Asserting getEmployee", employeeTwo, employeeService.getEmployee(1));
    }
}