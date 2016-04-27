package limmen.business.services;

import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.InvoiceLineFilter;
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
     * Method to get all InvoiceLines (filtered).
     *
     * @param InvoiceLineFilter properties to filter the list of InvoiceLines on
     * @return list of filtered InvoiceLines
     */
    public List<InvoiceLine> getAllInvoiceLines(InvoiceLineFilter InvoiceLineFilter) throws SortException;
    
    /**
     * Method to get a invoiceline with a specified id.
     *
     * @param invoiceLineId id of the invoiceline.
     * @return Invoiceline with the id.
     */
    public InvoiceLine getInvoiceLine(int invoiceLineId);

    /**
     * Method to create a new InvoiceLine.
     *
     * @param InvoiceLine data of the InvoiceLine to create
     * @return the created  InvoiceLine
     */
    public InvoiceLine createNewInvoiceLine(InvoiceLine InvoiceLine);

    /**
     * Method to update a InvoiceLine.
     *
     * @param InvoiceLine InvoiceLine to update
     * @return updated InvoiceLine
     */
    public InvoiceLine updateInvoiceLine(InvoiceLine InvoiceLine);

    /**
     * Method to update the list of InvoiceLines
     *
     * @param InvoiceLines data to update InvoiceLines list with
     * @return new list of InvoiceLines
     */
    public List<InvoiceLine> updateInvoiceLines(List<InvoiceLine> InvoiceLines);

    /**
     * Method to delete a InvoiceLine.
     *
     * @param InvoiceLineId id of the InvoiceLine to delete
     * @return the deleted InvoiceLine.
     */
    public InvoiceLine deleteInvoiceLine(int InvoiceLineId);
}
