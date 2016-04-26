package limmen.business.services;

import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.EmployeeFilter;
import limmen.integration.entities.Employee;
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
     * Method to get all employees (filtered).
     *
     * @param employeeFilter properties to filter the list of employees on
     * @return list of filtered employees
     */
    public List<Employee> getAllEmployees(EmployeeFilter employeeFilter) throws SortException;
    
    /**
     * Method to get a employee with a specified id.
     *
     * @param employeeId id of the employee.
     * @return Employee with the specified id.
     */
    public Employee getEmployee(int employeeId);

    /**
     * Method to create a new employee.
     *
     * @param employee data of the employee to create
     * @return the created  employee
     */
    public Employee createNewEmployee(Employee employee);

    /**
     * Method to update a employee.
     *
     * @param employee employee to update
     * @return updated employee
     */
    public Employee updateEmployee(Employee employee);

    /**
     * Method to update the list of employees
     *
     * @param employees data to update employees list with
     * @return new list of employees
     */
    public List<Employee> updateEmployees(List<Employee> employees);

    /**
     * Method to delete a employee.
     *
     * @param employeeId id of the employee to delete
     * @return the deleted employee.
     */
    public Employee deleteEmployee(int employeeId);
}
