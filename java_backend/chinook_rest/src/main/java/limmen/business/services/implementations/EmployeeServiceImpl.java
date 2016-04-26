package limmen.business.services.implementations;

import limmen.business.services.EmployeeService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.EmployeeFilter;
import limmen.integration.entities.Employee;
import limmen.integration.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the EmployeeService interface, uses a repository for database interaction.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final EmployeeRepository employeeRepository;

    @Inject
    public EmployeeServiceImpl(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    @Override
    public List<Employee> getAllEmployees(EmployeeFilter employeeFilter) throws SortException {
        List<Employee> employees = getAllEmployees();
        employees = employeeFilter.filter(employees);
        try {
            return employeeFilter.sort(employees);
        } catch (Exception e) {
            throw new SortException("Invalid query string for sorting: " + employeeFilter.getSort());
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public Employee getEmployee(int employeeId) {
        return employeeRepository.getEmployee(employeeId);
    }

    @Override
    public Employee createNewEmployee(Employee employee) {
        employee.setEmployeeId(employeeRepository.getMaxId() + 1);
        return employeeRepository.createNewEmployee(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.updateEmployee(employee);
    }

    @Override
    public List<Employee> updateEmployees(List<Employee> employees) {
        employeeRepository.deleteEmployees();
        employees.forEach((employee) -> {
            createNewEmployee(employee);
        });
        return getAllEmployees();
    }

    @Override
    public Employee deleteEmployee(int employeeId) {
        Employee employee = getEmployee(employeeId);
        employeeRepository.deleteEmployee(employeeId);
        return employee;
    }

}
