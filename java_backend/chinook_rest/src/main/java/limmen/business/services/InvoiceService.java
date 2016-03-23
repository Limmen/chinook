package limmen.business.services;

import limmen.integration.entities.Invoice;

import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public interface InvoiceService {

    public List<Invoice> getAllInvoices();
    public Invoice getInvoice(int invoiceId);
}
