package limmen.business.services;

import limmen.integration.entities.InvoiceLine;

import java.util.List;

/**
 * Interface for a service that provides CRUD services for invoiceline-data.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public interface InvoiceLineService {

    /**
     * Method to get all invoicelines.
     *
     * @return list of invoicelines
     */
    public List<InvoiceLine> getAllInvoiceLines();

    /**
     * Method to get a invoiceline with a specified id.
     *
     * @param invoiceLineId id of the invoiceline.
     * @return Invoiceline with the id.
     */
    public InvoiceLine getInvoiceLine(int invoiceLineId);
}
