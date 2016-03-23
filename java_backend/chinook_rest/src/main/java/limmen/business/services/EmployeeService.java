package limmen.business.services;

import limmen.integration.entities.Employee;

import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public interface EmployeeService {

    public List<Employee> getAllEmployees();
    public Employee getEmployee(int employeeId);
}
