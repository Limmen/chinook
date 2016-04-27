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


    /**
     * Method to update the database with a new invoiceLine.
     *
     * @param invoiceLine invoiceLine to insert
     * @return the inserted invoiceLine
     */
    public InvoiceLine createNewInvoiceLine(InvoiceLine invoiceLine) {
        log.info("Update Database with new InvoiceLine. invoiceLineId: {}, invoiceId: {}, trackId: {}, unitPrice: {}," +
                " quantity: {}", invoiceLine.getInvoiceLineId(), invoiceLine.getInvoiceId(), invoiceLine.getTrackId(),
                invoiceLine.getUnitPrice(), invoiceLine.getQuantity());
        jdbc.update("INSERT INTO \"InvoiceLine\" (\"InvoiceLineId\", \"InvoiceId\", \"TrackId\", \"UnitPrice\"," +
                " \"Quantity\") VALUES (?, ?, ?, ?, ?);",
                invoiceLine.getInvoiceLineId(), invoiceLine.getInvoiceId(), invoiceLine.getTrackId(),
                invoiceLine.getUnitPrice(), invoiceLine.getQuantity());
        return invoiceLine;
    }

    /**
     * Method to query the database for the maximum id of all invoiceLines
     *
     * @return maxmum id
     */
    public int getMaxId() {
        log.debug("get max Id of invoiceLines");
        return jdbc.queryForObject("SELECT COALESCE(MAX(\"InvoiceLineId\"),0) FROM \"InvoiceLine\";", maxIdMapper);
    }

    /**
     * Method to update the database with new data for a certain invoiceLine.
     *
     * @param invoiceLine data to update
     * @return updated invoiceLine
     */
    public InvoiceLine updateInvoiceLine(InvoiceLine invoiceLine) {
        log.debug("update invoiceLine {}", invoiceLine.getInvoiceLineId());
        jdbc.update("UPDATE \"InvoiceLine\" SET \"InvoiceId\" = ?, \"TrackId\" = ?, \"UnitPrice\" = ?," +
                " \"Quantity\" = ? WHERE \"InvoiceLineId\" = ?;",
                invoiceLine.getInvoiceId(), invoiceLine.getTrackId(),
                invoiceLine.getUnitPrice(), invoiceLine.getQuantity(),invoiceLine.getInvoiceLineId());
        return invoiceLine;
    }

    /**
     * Method to delete invoiceLine from the database.
     *
     * @param invoiceLineId id of the invoiceLine to delete
     */
    public void deleteInvoiceLine(int invoiceLineId) {
        log.debug("delete invoiceLine {}", invoiceLineId);
        jdbc.update("DELETE FROM \"InvoiceLine\" WHERE \"InvoiceLineId\" = ?;", invoiceLineId);
    }

    /**
     * Method to delete all invoiceLines from the database.
     */
    public void deleteInvoiceLines() {
        log.debug("delete all invoiceLines");
        jdbc.update("DELETE  * FROM \"InvoiceLine\";");
    }
    
    private static final RowMapper<InvoiceLine> invoiceLineMapper = new RowMapper<InvoiceLine>() {
        public InvoiceLine mapRow(ResultSet rs, int rowNum) throws SQLException {
            InvoiceLine invoiceLine = new InvoiceLine(rs.getInt("InvoiceLineId"), rs.getInt("InvoiceId"),
                    rs.getInt("TrackId"), rs.getFloat("UnitPrice"), rs.getInt("Quantity"));
            return invoiceLine;
        }
    };

    private static final RowMapper<Integer> maxIdMapper = new RowMapper<Integer>() {
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt(1);
            return id;
        }
    };
}
