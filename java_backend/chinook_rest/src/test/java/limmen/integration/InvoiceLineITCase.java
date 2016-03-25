package limmen.integration;

import limmen.ChinookRestApplication;
import limmen.business.representations.array_representations.InvoiceLinesArrayRepresentation;
import limmen.business.representations.entity_representation.InvoiceLineRepresentation;
import limmen.integration.entities.InvoiceLine;
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
 * @author Kim Hammar on 2016-03-24.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChinookRestApplication.class)
@WebAppConfiguration
@IntegrationTest
public class InvoiceLineITCase {
    private final String BASE_URL = "http://localhost:7777/resources/invoicelines";
    private JdbcTemplate jdbc;
    private RestTemplate rest;
    private List<InvoiceLine> invoiceLines;
    @Autowired
    DataSource dataSource;

    @Before
    public void setup() {
        rest = new RestTemplate();
        jdbc = new JdbcTemplate(dataSource);
        invoiceLines = jdbc.query("SELECT * FROM \"InvoiceLine\";", invoiceLineMapper);
    }

    @Test
    public void getInvoiceLineTest() {
        if (invoiceLines.size() > 0) {
            InvoiceLineRepresentation expectedInvoiceLineRepresenation = new InvoiceLineRepresentation(invoiceLines.get(0));
            ResponseEntity<InvoiceLineRepresentation> responseEntity = rest.getForEntity(BASE_URL + "/" +
                    expectedInvoiceLineRepresenation.getInvoiceLine().getInvoiceLineId(), InvoiceLineRepresentation.class, Collections.EMPTY_MAP);
            assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals("Asserting entity", expectedInvoiceLineRepresenation, responseEntity.getBody());
        }
    }

    @Test
    public void getInvoiceLines() {
        ResponseEntity<InvoiceLinesArrayRepresentation> responseEntity = rest.getForEntity(BASE_URL, InvoiceLinesArrayRepresentation.class, Collections.EMPTY_MAP);
        assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Asserting array size", invoiceLines.size(), responseEntity.getBody().getInvoiceLines().size());
    }

    private static final RowMapper<InvoiceLine> invoiceLineMapper = new RowMapper<InvoiceLine>() {
        public InvoiceLine mapRow(ResultSet rs, int rowNum) throws SQLException {
            InvoiceLine invoiceLine = new InvoiceLine(rs.getInt("InvoiceLineId"), rs.getInt("InvoiceId"),
                    rs.getInt("TrackId"), rs.getFloat("UnitPrice"), rs.getInt("Quantity"));
            return invoiceLine;
        }
    };

}
