package limmen.integration.repositories;

import limmen.integration.entities.InvoiceLine;
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
 * CRUD-repository for the "InvoiceLine" table in the chinook database.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Repository
public class InvoiceLineRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbc;

    /**
     * Method to query the database for a invoiceline with a certain id.
     *
     * @param invoiceLineId id of the invoiceline.
     * @return InvoiceLine with the specified id.
     */
    public InvoiceLine getInvoiceLine(int invoiceLineId) {
        log.debug("getInvoiceLine {} from Database", invoiceLineId);
        return jdbc.queryForObject("SELECT * FROM \"InvoiceLine\" WHERE \"InvoiceLineId\"=?", invoiceLineMapper, invoiceLineId);
    }

    /**
     * Method to query the database for a list of all invoicelines.
     *
     * @return list of invoicelines
     */
    public List<InvoiceLine> getAllInvoiceLines(){
        log.debug("getAllInvoiceLines from Database");
        return jdbc.query("SELECT * FROM \"InvoiceLine\";", invoiceLineMapper);
    }

    private static final RowMapper<InvoiceLine> invoiceLineMapper = new RowMapper<InvoiceLine>() {
        public InvoiceLine mapRow(ResultSet rs, int rowNum) throws SQLException {
            InvoiceLine invoiceLine = new InvoiceLine(rs.getInt("InvoiceLineId"), rs.getInt("InvoiceId"),
                    rs.getInt("TrackId"), rs.getFloat("UnitPrice"), rs.getInt("Quantity"));
            return invoiceLine;
        }
    };
}
