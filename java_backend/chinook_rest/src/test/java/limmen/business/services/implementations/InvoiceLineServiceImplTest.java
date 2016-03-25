package limmen.business.services.implementations;

import limmen.business.services.InvoiceLineService;
import limmen.integration.entities.InvoiceLine;
import limmen.integration.repositories.InvoiceLineRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

/**
 * Unit test-suite for the invoiceline service implementation
 *
 * @author Kim Hammar on 2016-03-24.
 */
@RunWith(MockitoJUnitRunner.class)
public class InvoiceLineServiceImplTest {

    @Mock
    private InvoiceLineRepository invoiceLineRepository;
    private InvoiceLineService invoiceLineService;
    @Mock
    private InvoiceLine invoiceLineOne;
    @Mock
    private InvoiceLine invoiceLineTwo;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        invoiceLineService = new InvoiceLineServiceImpl((invoiceLineRepository));
    }

    /**
     * Test for the getAllInvoiceLines method
     */
    @Test
    public void testGetAllInvoiceLines() {
        final List<InvoiceLine> databaseList = new ArrayList();
        when(invoiceLineRepository.getAllInvoiceLines()).thenReturn(databaseList);
        List<InvoiceLine> returnedList = invoiceLineService.getAllInvoiceLines();
        assertEquals("Asserting getAllInvoiceLines", databaseList, returnedList);
    }

    /**
     * Test of the getIncoiceLine method
     */
    @Test
    public void testGetInvoiceLine() {
        when(invoiceLineRepository.getInvoiceLine(1)).thenReturn(invoiceLineOne);
        when(invoiceLineRepository.getInvoiceLine(2)).thenReturn(invoiceLineTwo);
        assertEquals("Asserting getInvoiceLine", invoiceLineOne, invoiceLineService.getInvoiceLine(1));
        assertEquals("Asserting getInvoiceLine", invoiceLineTwo, invoiceLineService.getInvoiceLine(2));
        assertNotEquals("Asserting getInvoiceLine", invoiceLineOne, invoiceLineService.getInvoiceLine(2));
        assertNotEquals("Asserting getInvoiceLine", invoiceLineTwo, invoiceLineService.getInvoiceLine(1));
    }
}