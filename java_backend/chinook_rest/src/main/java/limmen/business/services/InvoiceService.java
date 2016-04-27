package limmen.business.services;

import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.InvoiceFilter;
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
     * Method to get all artists (filtered).
     *
     * @param artistFilter properties to filter the list of artists on
     * @return list of filtered artists
     */
    public List<Invoice> getAllInvoices(InvoiceFilter artistFilter) throws SortException;
    
    /**
     * Method to get a invoice with a specified id.
     *
     * @param invoiceId id of the invoice
     * @return Invoice with the specified id.
     */
    public Invoice getInvoice(int invoiceId);

    /**
     * Method to create a new artist.
     *
     * @param artist data of the artist to create
     * @return the created  artist
     */
    public Invoice createNewInvoice(Invoice artist);

    /**
     * Method to update a artist.
     *
     * @param artist artist to update
     * @return updated artist
     */
    public Invoice updateInvoice(Invoice artist);

    /**
     * Method to update the list of artists
     *
     * @param artists data to update artists list with
     * @return new list of artists
     */
    public List<Invoice> updateInvoices(List<Invoice> artists);

    /**
     * Method to delete a artist.
     *
     * @param artistId id of the artist to delete
     * @return the deleted artist.
     */
    public Invoice deleteInvoice(int artistId);
}
