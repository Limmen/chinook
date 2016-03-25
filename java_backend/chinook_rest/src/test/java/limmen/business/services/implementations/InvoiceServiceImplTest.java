package limmen.business.services.implementations;

import limmen.business.services.InvoiceService;
import limmen.integration.entities.Invoice;
import limmen.integration.repositories.InvoiceRepository;
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
 * Unit test-suite for the invoice service implementation
 *
 * @author Kim Hammar on 2016-03-24.
 */
@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceImplTest {

    @Mock
    private InvoiceRepository invoiceRepository;
    private InvoiceService invoiceService;
    @Mock
    private Invoice invoiceOne;
    @Mock
    private Invoice invoiceTwo;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        invoiceService = new InvoiceServiceImpl((invoiceRepository));
    }

    /**
     * Test for the getAllInvoices method
     */
    @Test
    public void testGetAllInvoices() {
        final List<Invoice> databaseList = new ArrayList();
        when(invoiceRepository.getAllInvoices()).thenReturn(databaseList);
        List<Invoice> returnedList = invoiceService.getAllInvoices();
        assertEquals("Asserting getAllInvoices", databaseList, returnedList);
    }

    /**
     * Test of the getInvoice method
     */
    @Test
    public void testGetInvoice() {
        when(invoiceRepository.getInvoice(1)).thenReturn(invoiceOne);
        when(invoiceRepository.getInvoice(2)).thenReturn(invoiceTwo);
        assertEquals("Asserting getInvoice", invoiceOne, invoiceService.getInvoice(1));
        assertEquals("Asserting getInvoice", invoiceTwo, invoiceService.getInvoice(2));
        assertNotEquals("Asserting getInvoice", invoiceOne, invoiceService.getInvoice(2));
        assertNotEquals("Asserting getInvoice", invoiceTwo, invoiceService.getInvoice(1));
    }
}