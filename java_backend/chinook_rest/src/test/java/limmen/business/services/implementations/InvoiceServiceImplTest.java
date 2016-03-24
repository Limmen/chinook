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

    @Before
    public void setUp() throws Exception {
        invoiceService = new InvoiceServiceImpl((invoiceRepository));
    }

    @Test
    public void testGetAllInvoices() throws Exception {
        final List<Invoice> databaseList = new ArrayList();
        when(invoiceRepository.getAllInvoices()).thenReturn(databaseList);
        List<Invoice> returnedList = invoiceService.getAllInvoices();
        assertEquals("Asserting getAllInvoices", databaseList, returnedList);
    }

    @Test
    public void testGetInvoice() throws Exception {
        when(invoiceRepository.getInvoice(1)).thenReturn(invoiceOne);
        when(invoiceRepository.getInvoice(2)).thenReturn(invoiceTwo);
        assertEquals("Asserting getInvoice", invoiceOne, invoiceService.getInvoice(1));
        assertEquals("Asserting getInvoice", invoiceTwo, invoiceService.getInvoice(2));
        assertNotEquals("Asserting getInvoice", invoiceOne, invoiceService.getInvoice(2));
        assertNotEquals("Asserting getInvoice", invoiceTwo, invoiceService.getInvoice(1));
    }
}