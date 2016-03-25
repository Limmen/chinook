package limmen.business.services;

import limmen.integration.entities.Employee;

import java.util.List;

/**
 * Interface for a service that provides CRUD services for employee-data.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public interface EmployeeService {

    /**
     * Method to get all employees.
     *
     * @return list of employees.
     */
    public List<Employee> getAllEmployees();

    /**
     * Method to get a employee with a specified id.
     *
     * @param employeeId id of the employee.
     * @return Employee with the specified id.
     */
    public Employee getEmployee(int employeeId);
}
