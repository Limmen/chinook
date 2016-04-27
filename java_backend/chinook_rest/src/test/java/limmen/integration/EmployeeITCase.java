package limmen.integration;

import limmen.ChinookRestApplication;
import limmen.business.representations.array_representations.EmployeesArrayRepresentation;
import limmen.business.representations.entity_representation.EmployeeRepresentation;
import limmen.integration.entities.Employee;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Integration test-suite for the employee REST-resource
 *
 * @author Kim Hammar on 2016-03-24.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChinookRestApplication.class)
@WebAppConfiguration
@IntegrationTest
public class EmployeeITCase {
    private final String BASE_URL = "http://localhost:7777/resources/employees";
    private JdbcTemplate jdbc;
    private RestTemplate rest;
    private List<Employee> employees;
    @Autowired
    DataSource dataSource;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setup() {
        rest = new RestTemplate();
        jdbc = new JdbcTemplate(dataSource);
        employees = jdbc.query("SELECT * FROM \"Employee\";", employeeMapper);
    }

    /**
     * test of a singular employee resource
     */
    @Test
    public void getEmployeeTest() {
        if (employees.size() > 0) {
            EmployeeRepresentation expectedEmployeeRepresenation =
                    new EmployeeRepresentation(employees.get(0));
            ResponseEntity<EmployeeRepresentation> responseEntity = rest.getForEntity(BASE_URL + "/" +
                    expectedEmployeeRepresenation.getEmployee().getEmployeeId(), EmployeeRepresentation.class, Collections.EMPTY_MAP);
            assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals("Asserting entity", expectedEmployeeRepresenation, responseEntity.getBody());
        }
    }

    /**
     * test of employees resource
     */
    @Test
    public void getEmployees() {
        ResponseEntity<EmployeesArrayRepresentation> responseEntity = rest.getForEntity(BASE_URL, EmployeesArrayRepresentation.class, Collections.EMPTY_MAP);
        assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Asserting array size", employees.size(), responseEntity.getBody().getEmployees().size());
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
