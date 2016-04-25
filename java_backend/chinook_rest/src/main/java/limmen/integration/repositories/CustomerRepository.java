package limmen.integration.repositories;

import limmen.integration.entities.Customer;
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
 * CRUD-repository for the "Customer" table in the chinook database.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Repository
public class CustomerRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbc;

    /**
     * Method to query the database for a certain customer.
     *
     * @param customerId id of the customer
     * @return Customer with the specifed id.
     */
    public Customer getCustomer(int customerId) {
        log.debug("getCustomer {} from Database", customerId);
        return jdbc.queryForObject("SELECT * FROM \"Customer\" WHERE \"CustomerId\"=?", customerMapper, customerId);
    }

    /**
     * Method to query the database for a list of all customers.
     *
     * @return list of customers
     */
    public List<Customer> getAllCustomers(){
        log.debug("getAllCustomers from Database");
        return jdbc.query("SELECT * FROM \"Customer\";", customerMapper);
    }

    /**
     * Method to update the database with a new customer.
     *
     * @param customer customer to insert
     * @return the inserted customer
     */
    public Customer createNewCustomer(Customer customer) {
        log.info("Update Database with new Customer. customerId: {}, firstName: {}, lastName: {}, company: {}, " +
                "address: {}, city: {}, state: {}, country: {}, postalCode: {}", customer.getFirstName(),
                customer.getLastName(), customer.getCompany(), customer.getAddress(), customer.getCity(),
                customer.getState(), customer.getCountry(), customer.getPostalCode());
        jdbc.update("INSERT INTO \"Customer\" (\"CustomerId\", \"FirstName\", \"LastName\", \"Company\", \"Address\", " +
                "\"City\", \"State\", \"Country\", \"PostalCode\", \"Phone\", \"Fax\", \"Email\", \"SupportRepId\") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", customer.getCustomerId(), customer.getFirstName(),
                customer.getLastName(), customer.getCompany(), customer.getAddress(), customer.getCity(),
                customer.getState(), customer.getCountry(), customer.getPostalCode(), customer.getPhone(), customer.getFax(),
                customer.getEmail(), customer.getSupportRepId());
        return customer;
    }

    /**
     * Method to query the database for the maximum id of all customers
     *
     * @return maxmum id
     */
    public int getMaxId() {
        log.debug("get max Id of customers");
        return jdbc.queryForObject("SELECT COALESCE(MAX(\"CustomerId\"),0) FROM \"Customer\";", maxIdMapper);
    }

    /**
     * Method to update the database with new data for a certain customer.
     *
     * @param customer data to update
     * @return updated customer
     */
    public Customer updateCustomer(Customer customer) {
        log.debug("update customer {}", customer.getCustomerId());
        jdbc.update("UPDATE \"Customer\" SET \"FirstName\" = ?, \"LastName\" = ?, \"Company\" = ?, \"Address\" = ?," +
                "\"City\" = ?, \"State\" = ?, \"Country\" = ?, \"PostalCode\" = ? WHERE \"CustomerId\" = ?;", customer.getFirstName(),
                customer.getLastName(), customer.getCompany(), customer.getAddress(), customer.getCity(),
                customer.getState(), customer.getCountry(), customer.getPostalCode(),customer.getPhone(), customer.getFax(),
                customer.getEmail(), customer.getSupportRepId(), customer.getCustomerId());
        return customer;
    }

    /**
     * Method to delete customer from the database.
     *
     * @param customerId id of the customer to delete
     */
    public void deleteCustomer(int customerId) {
        log.debug("delete customer {}", customerId);
        jdbc.update("DELETE FROM \"Customer\" WHERE \"CustomerId\" = ?;", customerId);
    }

    /**
     * Method to delete all customers from the database.
     */
    public void deleteCustomers() {
        log.debug("delete all customers");
        jdbc.update("DELETE  * FROM \"Customer\";");
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

    private static final RowMapper<Integer> maxIdMapper = new RowMapper<Integer>() {
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt(1);
            return id;
        }
    };
}
