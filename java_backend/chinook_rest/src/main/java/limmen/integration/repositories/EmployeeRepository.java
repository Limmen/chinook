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
}
