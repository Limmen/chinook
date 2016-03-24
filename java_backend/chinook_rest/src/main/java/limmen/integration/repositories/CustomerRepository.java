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
 * @author Kim Hammar on 2016-03-22.
 */
@Repository
public class CustomerRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbc;

    public Customer getCustomer(int customerId) {
        return jdbc.queryForObject("SELECT * FROM \"Customer\" WHERE \"CustomerId\"=?", customerMapper, customerId);
    }

    public List<Customer> getAllCustomers(){
        log.info("getAllCustomers from Database");
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