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
        log.debug("getInvoice {} from Database", invoiceId);
        return jdbc.queryForObject("SELECT * FROM \"Invoice\" WHERE \"InvoiceId\"=?", invoiceMapper, invoiceId);
    }

    /**
     * Method to query the database for a list of all invoices.
     *
     * @return list of invoices.
     */
    public List<Invoice> getAllInvoices(){
        log.debug("getAllInvoices from Database");
        return jdbc.query("SELECT * FROM \"Invoice\";", invoiceMapper);
    }

    /**
     * Method to update the database with a new invoice.
     *
     * @param invoice invoice to insert
     * @return the inserted invoice
     */
    public Invoice createNewInvoice(Invoice invoice) {
        log.info("Update Database with new Invoice. invoiceId: {}, customerId: {}, invoiceDate: {}, billingAddress: {}, " +
                "billingCity: {}, billingState: {}, billingCountry: {}, billingPostalCode: {}, total: {}", invoice.getInvoiceId(), invoice.getCustomerId(),
                invoice.getInvoiceDate(), invoice.getBillingAddress(), invoice.getBillingCity(), invoice.getBillingState(),
                invoice.getBillingCountry(), invoice.getBillingPostalCode(), invoice.getTotal());
        jdbc.update("INSERT INTO \"Invoice\" (\"InvoiceId\", \"CustomerId\", \"InvoiceDate\", \"BillingAddress\"," +
                "\"BillingCity\", \"BillingState\", \"BillingCountry\", \"BillingPostalCode\", \"Total\") " +
                "VALUES (?, ?, ? ,? , ?, ?, ?, ?, ?);", invoice.getInvoiceId(), invoice.getCustomerId(),
                invoice.getInvoiceDate(), invoice.getBillingAddress(), invoice.getBillingCity(), invoice.getBillingState(),
                invoice.getBillingCountry(), invoice.getBillingPostalCode(), invoice.getTotal());
        return invoice;
    }

    /**
     * Method to query the database for the maximum id of all invoices
     *
     * @return maxmum id
     */
    public int getMaxId() {
        log.debug("get max Id of invoices");
        return jdbc.queryForObject("SELECT COALESCE(MAX(\"InvoiceId\"),0) FROM \"Invoice\";", maxIdMapper);
    }

    /**
     * Method to update the database with new data for a certain invoice.
     *
     * @param invoice data to update
     * @return updated invoice
     */
    public Invoice updateInvoice(Invoice invoice) {
        log.debug("update invoice {}", invoice.getInvoiceId());
        jdbc.update("UPDATE \"Invoice\" SET \"CustomerId\" = ?, \"InvoiceDate\" = ?, \"BillingAddress\" = ?," +
                " \"BillingCity\" = ?, \"BillingState\" = ?, \"BillingCountry\" = ?, \"BillingPostalCode\" = ?," +
                " \"Total\" = ?  WHERE \"InvoiceId\" = ?;",
                invoice.getCustomerId(),
                invoice.getInvoiceDate(), invoice.getBillingAddress(), invoice.getBillingCity(), invoice.getBillingState(),
                invoice.getBillingCountry(), invoice.getBillingPostalCode(), invoice.getTotal(), invoice.getInvoiceId());
        return invoice;
    }

    /**
     * Method to delete invoice from the database.
     *
     * @param invoiceId id of the invoice to delete
     */
    public void deleteInvoice(int invoiceId) {
        log.debug("delete invoice {}", invoiceId);
        jdbc.update("DELETE FROM \"Invoice\" WHERE \"InvoiceId\" = ?;", invoiceId);
    }

    /**
     * Method to delete all invoices from the database.
     */
    public void deleteInvoices() {
        log.debug("delete all invoices");
        jdbc.update("DELETE  * FROM \"Invoice\";");
    }


    private static final RowMapper<Invoice> invoiceMapper = new RowMapper<Invoice>() {
        public Invoice mapRow(ResultSet rs, int rowNum) throws SQLException {
            Invoice invoice = new Invoice(rs.getInt("InvoiceId"),rs.getInt("CustomerId"), rs.getTimestamp("InvoiceDate"),
                    rs.getString("BillingAddress"), rs.getString("BillingCity"), rs.getString("BillingState"),
                    rs.getString("BillingCountry"), rs.getString("BillingPostalCode"), rs.getFloat("Total"));
            return invoice;
        }
    };

    private static final RowMapper<Integer> maxIdMapper = new RowMapper<Integer>() {
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt(1);
            return id;
        }
    };
}
