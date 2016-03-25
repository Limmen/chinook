package limmen.integration.repositories;

import limmen.integration.entities.Invoice;
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
 * CRUD-repository for the "Invoice" table in the chinook database.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Repository
public class InvoiceRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbc;

    /**
     * Method to query the database for a certain invoice.
     *
     * @param invoiceId id of the invoice.
     * @return Invoice with the specified id.
     */
    public Invoice getInvoice(int invoiceId) {
        return jdbc.queryForObject("SELECT * FROM \"Invoice\" WHERE \"InvoiceId\"=?", invoiceMapper, invoiceId);
    }

    /**
     * Method to query the database for a list of all invoices.
     *
     * @return list of invoices.
     */
    public List<Invoice> getAllInvoices(){
        log.info("getAllInvoices from Database");
        return jdbc.query("SELECT * FROM \"Invoice\";", invoiceMapper);
    }

    private static final RowMapper<Invoice> invoiceMapper = new RowMapper<Invoice>() {
        public Invoice mapRow(ResultSet rs, int rowNum) throws SQLException {
            Invoice invoice = new Invoice(rs.getInt("InvoiceId"),rs.getInt("CustomerId"), rs.getTimestamp("InvoiceDate"),
                    rs.getString("BillingAddress"), rs.getString("BillingCity"), rs.getString("BillingState"),
                    rs.getString("BillingCountry"), rs.getString("BillingPostalCode"), rs.getFloat("Total"));
            return invoice;
        }
    };
}
