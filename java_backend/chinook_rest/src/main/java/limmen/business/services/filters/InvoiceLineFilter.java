package limmen.business.services.filters;

import limmen.integration.entities.InvoiceLine;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for implementing filtering and sorting functionality for the InvoiceLine-Resource.
 *
 * @author Kim Hammar on 2016-04-25.
 */
public class InvoiceLineFilter {

    private String sort;
    private String invoiceLineId;
    private String invoiceId;
    private String trackId;
    private String unitPrice;
    private String quantity;

    /**
     * Default constructor
     */
    public InvoiceLineFilter() {
    }

    /**
     * Method to filter a list of invoiceLines.
     *
     * @param invoiceLines list to filter
     * @return filtered list
     */
    public List<InvoiceLine> filter(List<InvoiceLine> invoiceLines) {
        if (invoiceLineId != null)
            invoiceLines = invoiceLines.stream().filter(invoiceLine -> invoiceLine.getInvoiceLineId() == Integer.parseInt(invoiceLineId)).collect(Collectors.toList());
        if (invoiceId != null)
            invoiceLines = invoiceLines.stream().filter(invoiceLine -> invoiceLine.getInvoiceId() == Integer.parseInt(invoiceId)).collect(Collectors.toList());
        if (trackId != null)
            invoiceLines = invoiceLines.stream().filter(invoiceLine -> invoiceLine.getTrackId() == Integer.parseInt(trackId)).collect(Collectors.toList());
        if (unitPrice != null)
            invoiceLines = invoiceLines.stream().filter(invoiceLine -> invoiceLine.getUnitPrice() == Float.parseFloat(unitPrice)).collect(Collectors.toList());
        if (quantity != null)
            invoiceLines = invoiceLines.stream().filter(invoiceLine -> invoiceLine.getQuantity() == Integer.parseInt(quantity)).collect(Collectors.toList());
        return invoiceLines;
    }

    /**
     * Method to sort a list of invoiceLines.
     *
     * @param invoiceLines list to sort
     * @return sorted list
     */
    public List<InvoiceLine> sort(List<InvoiceLine> invoiceLines) {
        if(sort == null)
            return invoiceLines;
        String order = sort.substring(0, 1);
        String property = sort.substring(1, sort.length());
        Comparator<InvoiceLine> comparator = null;
        if (property.equals("invoiceLineId")) {
            comparator = (invoiceLine1, invoiceLine2) ->
            {
                if (invoiceLine1.getInvoiceLineId() > invoiceLine2.getInvoiceLineId())
                    return 1;
                else if (invoiceLine1.getInvoiceLineId() < invoiceLine2.getInvoiceLineId())
                    return -1;
                return 0;
            };
        }
        if (property.equals("invoiceId")) {
            comparator = (invoiceLine1, invoiceLine2) ->
            {
                if (invoiceLine1.getInvoiceId() > invoiceLine2.getInvoiceId())
                    return 1;
                else if (invoiceLine1.getInvoiceId() < invoiceLine2.getInvoiceId())
                    return -1;
                return 0;
            };
        }
        if (property.equals("trackId")) {
            comparator = (invoiceLine1, invoiceLine2) ->
            {
                if (invoiceLine1.getTrackId() > invoiceLine2.getTrackId())
                    return 1;
                else if (invoiceLine1.getTrackId() < invoiceLine2.getTrackId())
                    return -1;
                return 0;
            };
        }
        if (property.equals("unitPrice")) {
            comparator = (invoiceLine1, invoiceLine2) ->
            {
                if (invoiceLine1.getUnitPrice() > invoiceLine2.getUnitPrice())
                    return 1;
                else if (invoiceLine1.getUnitPrice() < invoiceLine2.getUnitPrice())
                    return -1;
                return 0;
            };
        }
        if (property.equals("quantity")) {
            comparator = (invoiceLine1, invoiceLine2) ->
            {
                if (invoiceLine1.getQuantity() > invoiceLine2.getQuantity())
                    return 1;
                else if (invoiceLine1.getQuantity() < invoiceLine2.getQuantity())
                    return -1;
                return 0;
            };
        }
        Collections.sort(invoiceLines, comparator);
        if (order.equals("-")) {
            Collections.reverse(invoiceLines);
        }
        return invoiceLines;
    }


    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public String getInvoiceLineId() {
        return invoiceLineId;
    }

    public void setInvoiceLineId(String invoiceLineId) {
        this.invoiceLineId = invoiceLineId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
