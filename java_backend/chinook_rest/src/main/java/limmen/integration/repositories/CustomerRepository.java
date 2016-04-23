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
