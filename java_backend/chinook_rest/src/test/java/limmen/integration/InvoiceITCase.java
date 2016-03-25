package limmen.integration;

import limmen.ChinookRestApplication;
import limmen.business.representations.array_representations.InvoicesArrayRepresentation;
import limmen.business.representations.entity_representation.InvoiceRepresentation;
import limmen.integration.entities.Invoice;
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
public class InvoiceITCase {
    private final String BASE_URL = "http://localhost:7777/resources/invoices";
    private JdbcTemplate jdbc;
    private RestTemplate rest;
    private List<Invoice> invoices;
    @Autowired
    DataSource dataSource;

    @Before
    public void setup() {
        rest = new RestTemplate();
        jdbc = new JdbcTemplate(dataSource);
        invoices = jdbc.query("SELECT * FROM \"Invoice\";", invoiceMapper);
    }

    @Test
    public void getInvoiceTest() {
        if (invoices.size() > 0) {
            InvoiceRepresentation expectedInvoiceRepresenation = new InvoiceRepresentation(invoices.get(0));
            ResponseEntity<InvoiceRepresentation> responseEntity = rest.getForEntity(BASE_URL + "/" +
                    expectedInvoiceRepresenation.getInvoice().getInvoiceId(), InvoiceRepresentation.class, Collections.EMPTY_MAP);
            assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals("Asserting entity", expectedInvoiceRepresenation, responseEntity.getBody());
        }
    }

    @Test
    public void getInvoices() {
        ResponseEntity<InvoicesArrayRepresentation> responseEntity = rest.getForEntity(BASE_URL, InvoicesArrayRepresentation.class, Collections.EMPTY_MAP);
        assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Asserting array size", invoices.size(), responseEntity.getBody().getInvoices().size());
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
