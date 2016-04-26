package limmen.business.services.filters;

import limmen.integration.entities.Invoice;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for implementing filtering and sorting functionality for the Invoice-Resource.
 *
 * @author Kim Hammar on 2016-04-25.
 */
public class InvoiceFilter {

    private String sort;
    private String invoiceId;
    private String customerId;
    private Date invoiceDate;
    private String billingAddress;
    private String billingCity;
    private String billingState;
    private String billingCountry;
    private String billingPostalCode;
    private String total;

    /**
     * Default constructor
     */
    public InvoiceFilter() {
    }

    /**
     * Method to filter a list of invoices.
     *
     * @param invoices list to filter
     * @return filtered list
     */
    public List<Invoice> filter(List<Invoice> invoices) {
        if (invoiceId != null)
            invoices = invoices.stream().filter(invoice -> invoice.getInvoiceId() == Integer.parseInt(invoiceId)).collect(Collectors.toList());
        if (customerId != null)
            invoices = invoices.stream().filter(invoice -> invoice.getCustomerId() == Integer.parseInt(customerId)).collect(Collectors.toList());
        if (total != null)
            invoices = invoices.stream().filter(invoice -> invoice.getTotal() == Float.parseFloat(total)).collect(Collectors.toList());
        if (invoiceDate != null)
            invoices = invoices.stream().filter(invoice -> invoice.getInvoiceDate().equals(invoiceDate)).collect(Collectors.toList());
        if (billingAddress != null)
            invoices = invoices.stream().filter(invoice -> invoice.getBillingAddress().equals(billingAddress)).collect(Collectors.toList());
        if (billingCity != null)
            invoices = invoices.stream().filter(invoice -> invoice.getBillingCity().equals(billingCity)).collect(Collectors.toList());
        if (billingState != null)
            invoices = invoices.stream().filter(invoice -> invoice.getBillingState().equals(billingState)).collect(Collectors.toList());
        if (billingCountry != null)
            invoices = invoices.stream().filter(invoice -> invoice.getBillingCountry().equals(billingCountry)).collect(Collectors.toList());
        if (billingPostalCode != null)
            invoices = invoices.stream().filter(invoice -> invoice.getBillingPostalCode().equals(billingPostalCode)).collect(Collectors.toList());
        return invoices;
    }

    /**
     * Method to sort a list of invoices.
     *
     * @param invoices list to sort
     * @return sorted list
     */
    public List<Invoice> sort(List<Invoice> invoices) {
        if(sort == null)
            return invoices;
        String order = sort.substring(0, 1);
        String property = sort.substring(1, sort.length());
        Comparator<Invoice> comparator = null;
        if (property.equals("invoiceId")) {
            comparator = (invoice1, invoice2) ->
            {
                if (invoice1.getInvoiceId() > invoice2.getInvoiceId())
                    return 1;
                else if (invoice1.getInvoiceId() < invoice2.getInvoiceId())
                    return -1;
                return 0;
            };
        }
        if (property.equals("customerId")) {
            comparator = (invoice1, invoice2) ->
            {
                if (invoice1.getCustomerId() > invoice2.getCustomerId())
                    return 1;
                else if (invoice1.getCustomerId() < invoice2.getCustomerId())
                    return -1;
                return 0;
            };
        }
        if (property.equals("total")) {
            comparator = (invoice1, invoice2) ->
            {
                if (invoice1.getTotal() > invoice2.getTotal())
                    return 1;
                else if (invoice1.getTotal() < invoice2.getTotal())
                    return -1;
                return 0;
            };
        }
        if (property.equals("invoiceDate")) {
            comparator = (invoice1, invoice2) ->
            {
                if (invoice1.getInvoiceDate().compareTo(invoice2.getInvoiceDate()) > 0)
                    return 1;
                else if (invoice1.getInvoiceDate().compareTo(invoice2.getInvoiceDate()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("billingAddress")) {
            comparator = (invoice1, invoice2) ->
            {
                if (invoice1.getBillingAddress().compareTo(invoice2.getBillingAddress()) > 0)
                    return 1;
                else if (invoice1.getBillingAddress().compareTo(invoice2.getBillingAddress()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("billingCity")) {
            comparator = (invoice1, invoice2) ->
            {
                if (invoice1.getBillingCity().compareTo(invoice2.getBillingCity()) > 0)
                    return 1;
                else if (invoice1.getBillingCity().compareTo(invoice2.getBillingCity()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("billingState")) {
            comparator = (invoice1, invoice2) ->
            {
                if (invoice1.getBillingState().compareTo(invoice2.getBillingState()) > 0)
                    return 1;
                else if (invoice1.getBillingState().compareTo(invoice2.getBillingState()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("billingCountry")) {
            comparator = (invoice1, invoice2) ->
            {
                if (invoice1.getBillingCountry().compareTo(invoice2.getBillingCountry()) > 0)
                    return 1;
                else if (invoice1.getBillingCountry().compareTo(invoice2.getBillingCountry()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("billingPostalCode")) {
            comparator = (invoice1, invoice2) ->
            {
                if (invoice1.getBillingPostalCode().compareTo(invoice2.getBillingPostalCode()) > 0)
                    return 1;
                else if (invoice1.getBillingPostalCode().compareTo(invoice2.getBillingPostalCode()) < 0)
                    return -1;
                return 0;
            };
        }
        Collections.sort(invoices, comparator);
        if (order.equals("-")) {
            Collections.reverse(invoices);
        }
        return invoices;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
