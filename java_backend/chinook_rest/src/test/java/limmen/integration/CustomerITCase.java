package limmen.integration;

import limmen.ChinookRestApplication;
import limmen.business.representations.array_representations.CustomersArrayRepresentation;
import limmen.business.representations.entity_representation.CustomerRepresentation;
import limmen.integration.entities.Customer;
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
 * Integration test-suite for the customer REST-resource
 *
 * @author Kim Hammar on 2016-03-24.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChinookRestApplication.class)
@WebAppConfiguration
@IntegrationTest
public class CustomerITCase {
    final String BASE_URL = "http://localhost:7777/resources/customers";
    private JdbcTemplate jdbc;
    private RestTemplate rest;
    private List<Customer> customers;
    @Autowired
    DataSource dataSource;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setup() {
        rest = new RestTemplate();
        jdbc = new JdbcTemplate(dataSource);
        customers = jdbc.query("SELECT * FROM \"Customer\";", customerMapper);
    }

    /**
     * test of a singular customer resource
     */
    @Test
    public void getCustomerTest() {
        if (customers.size() > 0) {
            CustomerRepresentation expectedCustomerRepresenation = new CustomerRepresentation(customers.get(0));
            ResponseEntity<CustomerRepresentation> responseEntity = rest.getForEntity(BASE_URL + "/" +
                    expectedCustomerRepresenation.getCustomer().getCustomerId(), CustomerRepresentation.class, Collections.EMPTY_MAP);
            assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals("Asserting entity", expectedCustomerRepresenation, responseEntity.getBody());
        }
    }

    /**
     * test of customers resource
     */
    @Test
    public void getCustomers() {
        ResponseEntity<CustomersArrayRepresentation> responseEntity = rest.getForEntity(BASE_URL, CustomersArrayRepresentation.class, Collections.EMPTY_MAP);
        assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Asserting array size", customers.size(), responseEntity.getBody().getCustomers().size());
    }

    private static final RowMapper<Customer> customerMapper = new RowMapper<Customer>() {
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer(rs.getInt("CustomerId"), rs.getString("FirstName"), rs.getString("LastName"),
                    rs.getString("Company"), rs.getString("Address"), rs.getString("City"), rs.getString("State"),
                    rs.getString("Country"), rs.getString("PostalCode"), rs.getString("Phone"), rs.getString("Fax"),
                    rs.getString("Email"), rs.getInt("SupportRepId"));
            return customer;
        }
    };
}
