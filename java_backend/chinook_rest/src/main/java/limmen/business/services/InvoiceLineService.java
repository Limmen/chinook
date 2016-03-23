package limmen.business.services;

import limmen.integration.entities.InvoiceLine;

import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public interface InvoiceLineService {

    public List<InvoiceLine> getAllInvoiceLines();
    public InvoiceLine getInvoiceLine(int invoiceLineId);
}
