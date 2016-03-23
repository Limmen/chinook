package limmen.business.services.implementations;

import limmen.business.services.EmployeeService;
import limmen.integration.entities.Employee;
import limmen.integration.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
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
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public Employee getEmployee(int employeeId) {
        return employeeRepository.getEmployee(employeeId);
    }
}
