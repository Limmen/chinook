package limmen.business.services;

import limmen.integration.entities.Invoice;

import java.util.List;

/**
 * Interface for a service that provides CRUD services for invoice-data.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public interface InvoiceService {

    /**
     * Method to get all invoices.
     *
     * @return list of invoices
     */
    public List<Invoice> getAllInvoices();

    /**
     * Method to get a invoice with a specified id.
     *
     * @param invoiceId id of the invoice
     * @return Invoice with the specified id.
     */
    public Invoice getInvoice(int invoiceId);
}
