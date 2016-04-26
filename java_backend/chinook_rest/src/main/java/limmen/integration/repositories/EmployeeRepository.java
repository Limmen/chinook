package limmen.integration.repositories;

import limmen.integration.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * CRUD-repository for the "Employee" table in the chinook database.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Repository
public class EmployeeRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbc;

    /**
     * Method to query the database for a employee with a certain id.
     *
     * @param employeeId id of the employee.
     * @return Employee with the id.
     */
    public Employee getEmployee(int employeeId) {
        log.debug("getEmployee {} from Database", employeeId);
        return jdbc.queryForObject("SELECT * FROM \"Employee\" WHERE \"EmployeeId\"=?", employeeMapper, employeeId);
    }

    /**
     * Method to query the database for a list of all employees.
     *
     * @return list of employees.
     */
    public List<Employee> getAllEmployees(){
        log.debug("getAllEmployees from Database");
        return jdbc.query("SELECT * FROM \"Employee\";", employeeMapper);
    }

    /**
     * Method to update the database with a new employee.
     *
     * @param employee employee to insert
     * @return the inserted employee
     */
    public Employee createNewEmployee(Employee employee) {
        log.info("Update Database with new Employee. employeeId: {}, firstName: {}, lastName: {}, title: {}, " +
                "reportsTo: {}, birthDate: {}, hireDate: {}, address: {}, city: {}, state: {}, country: {}, postalCode: {}, phone: {}," +
                "fax: {}, email: {}", employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(),
                employee.getTitle(), employee.getReportsTo(), employee.getBirthDate(), employee.getHireDate(),
                employee.getAddress(), employee.getCity(), employee.getState(), employee.getCountry(), employee.getPostalCode(),
                employee.getPhone(), employee.getFax(), employee.getEmail());
        jdbc.update("INSERT INTO \"Employee\" (\"EmployeeId\", \"FirstName\", \"LastName\", \"Title\", \"ReportsTo\"," +
                "\"BirthDate\", \"HireDate\", \"Address\", \"City\", \"State\", \"Country\", \"PostalCode\"," +
                "\"Phone\", \"Fax\", \"Email\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
                employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(),
                employee.getTitle(), employee.getReportsTo(), employee.getBirthDate(), employee.getHireDate(),
                employee.getAddress(), employee.getCity(), employee.getState(), employee.getCountry(), employee.getPostalCode(),
                employee.getPhone(), employee.getFax(), employee.getEmail());
        return employee;
    }

    /**
     * Method to query the database for the maximum id of all employees
     *
     * @return maxmum id
     */
    public int getMaxId() {
        log.debug("get max Id of employees");
        return jdbc.queryForObject("SELECT COALESCE(MAX(\"EmployeeId\"),0) FROM \"Employee\";", maxIdMapper);
    }

    /**
     * Method to update the database with new data for a certain employee.
     *
     * @param employee data to update
     * @return updated employee
     */
    public Employee updateEmployee(Employee employee) {
        log.debug("update employee {}", employee.getEmployeeId());
        jdbc.update("UPDATE \"Employee\" SET \"FirstName\" = ?, \"LastName\" = ?, \"Title\" = ?, \"ReportsTo\" = ?," +
                "\"BirthDate\" = ?, \"HireDate\" = ?, \"Address\" = ?, \"City\" = ?, \"State\" = ?, \"Country\" = ?," +
                "\"PostalCode\" = ?, \"Phone\" = ?, \"Fax\" = ?, \"Email\" = ? WHERE \"EmployeeId\" = ?;",
                employee.getFirstName(), employee.getLastName(),
                employee.getTitle(), employee.getReportsTo(), employee.getBirthDate(), employee.getHireDate(),
                employee.getAddress(), employee.getCity(), employee.getState(), employee.getCountry(),
                employee.getPostalCode(),
                employee.getPhone(), employee.getFax(), employee.getEmail(), employee.getEmployeeId());
        return employee;
    }

    /**
     * Method to delete employee from the database.
     *
     * @param employeeId id of the employee to delete
     */
    public void deleteEmployee(int employeeId) {
        log.debug("delete employee {}", employeeId);
        jdbc.update("DELETE FROM \"Employee\" WHERE \"EmployeeId\" = ?;", employeeId);
    }

    /**
     * Method to delete all employees from the database.
     */
    public void deleteEmployees() {
        log.debug("delete all employees");
        jdbc.update("DELETE  * FROM \"Employee\";");
    }
    
    private static final RowMapper<Employee> employeeMapper = new RowMapper<Employee>() {
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee(rs.getInt("EmployeeId"), rs.getString("LastName"), rs.getString("FirstName"),
                    rs.getString("Title"), rs.getInt("ReportsTo"), rs.getTimestamp("BirthDate"),
                    rs.getTimestamp("HireDate"), rs.getString("Address"), rs.getString("City"), rs.getString("State"),
                    rs.getString("Country"), rs.getString("PostalCode"), rs.getString("Phone"), rs.getString("Fax"),
                    rs.getString("Email"));
            return employee;
        }
    };

    private static final RowMapper<Integer> maxIdMapper = new RowMapper<Integer>() {
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt(1);
            return id;
        }
    };
}
