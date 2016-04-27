package limmen.business.services.filters;

import limmen.integration.entities.Invoice;
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
 * @author Kim Hammar on 2016-04-27.
 */
@RunWith(MockitoJUnitRunner.class)
public class InvoiceFilterTest {

    private InvoiceFilter invoiceFilter;
    private List<Invoice> invoices;

    @Mock
    Invoice invoiceOne;
    @Mock
    Invoice invoiceTwo;
    @Mock
    Invoice invoiceThree;
    @Mock
    Invoice invoiceFour;
    @Mock
    Invoice invoiceFive;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        invoiceFilter = new InvoiceFilter();
        invoices = new ArrayList<>();
        invoices.add(invoiceOne);
        invoices.add(invoiceTwo);
        invoices.add(invoiceThree);
        invoices.add(invoiceFour);
        invoices.add(invoiceFive);
        when(invoiceOne.getInvoiceId()).thenReturn(1);
        when(invoiceTwo.getInvoiceId()).thenReturn(2);
        when(invoiceThree.getInvoiceId()).thenReturn(3);
        when(invoiceFour.getInvoiceId()).thenReturn(4);
        when(invoiceFive.getInvoiceId()).thenReturn(5);
    }

    /**
     * Test of the filter method
     */
    @Test
    public void testFilter() {
        invoiceFilter.setInvoiceId("1");
        assertEquals("Asserting filter invoices", invoiceOne, invoiceFilter.filter(invoices).get(0));
        invoiceFilter.setInvoiceId("2");
        assertNotEquals("Asserting filter invoices", invoiceOne, invoiceFilter.filter(invoices).get(0));
        assertEquals("Asserting filter invoices", invoiceTwo, invoiceFilter.filter(invoices).get(0));
    }

    /**
     * Test of the sort method
     */
    @Test
    public void testSort() {
        invoiceFilter.setSort("+invoiceId");
        assertEquals("Asserting filter invoices", invoiceOne, invoiceFilter.sort(invoices).get(0));
        assertEquals("Asserting filter invoices", invoiceFive, invoiceFilter.sort(invoices).get(4));
        invoiceFilter.setSort("-invoiceId");
        assertEquals("Asserting filter invoices", invoiceFive, invoiceFilter.sort(invoices).get(0));
        assertEquals("Asserting filter invoices", invoiceFour, invoiceFilter.sort(invoices).get(1));
    }
}