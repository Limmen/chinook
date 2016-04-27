package limmen.business.services.filters;

import limmen.integration.entities.InvoiceLine;
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
public class InvoiceLineFilterTest {

    private InvoiceLineFilter invoiceLineFilter;
    private List<InvoiceLine> invoiceLines;

    @Mock
    InvoiceLine invoiceLineOne;
    @Mock
    InvoiceLine invoiceLineTwo;
    @Mock
    InvoiceLine invoiceLineThree;
    @Mock
    InvoiceLine invoiceLineFour;
    @Mock
    InvoiceLine invoiceLineFive;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        invoiceLineFilter = new InvoiceLineFilter();
        invoiceLines = new ArrayList<>();
        invoiceLines.add(invoiceLineOne);
        invoiceLines.add(invoiceLineTwo);
        invoiceLines.add(invoiceLineThree);
        invoiceLines.add(invoiceLineFour);
        invoiceLines.add(invoiceLineFive);
        when(invoiceLineOne.getInvoiceLineId()).thenReturn(1);
        when(invoiceLineTwo.getInvoiceLineId()).thenReturn(2);
        when(invoiceLineThree.getInvoiceLineId()).thenReturn(3);
        when(invoiceLineFour.getInvoiceLineId()).thenReturn(4);
        when(invoiceLineFive.getInvoiceLineId()).thenReturn(5);
    }

    /**
     * Test of the filter method
     */
    @Test
    public void testFilter() {
        invoiceLineFilter.setInvoiceLineId("1");
        assertEquals("Asserting filter invoiceLines", invoiceLineOne, invoiceLineFilter.filter(invoiceLines).get(0));
        invoiceLineFilter.setInvoiceLineId("2");
        assertNotEquals("Asserting filter invoiceLines", invoiceLineOne, invoiceLineFilter.filter(invoiceLines).get(0));
        assertEquals("Asserting filter invoiceLines", invoiceLineTwo, invoiceLineFilter.filter(invoiceLines).get(0));
    }

    /**
     * Test of the sort method
     */
    @Test
    public void testSort() {
        invoiceLineFilter.setSort("+invoiceLineId");
        assertEquals("Asserting filter invoiceLines", invoiceLineOne, invoiceLineFilter.sort(invoiceLines).get(0));
        assertEquals("Asserting filter invoiceLines", invoiceLineFive, invoiceLineFilter.sort(invoiceLines).get(4));
        invoiceLineFilter.setSort("-invoiceLineId");
        assertEquals("Asserting filter invoiceLines", invoiceLineFive, invoiceLineFilter.sort(invoiceLines).get(0));
        assertEquals("Asserting filter invoiceLines", invoiceLineFour, invoiceLineFilter.sort(invoiceLines).get(1));
    }
}