package limmen.business.services.implementations;

import limmen.business.services.InvoiceService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.InvoiceFilter;
import limmen.integration.entities.Invoice;
import limmen.integration.repositories.InvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the InvoiceService interface, uses a repository for database interaction.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final InvoiceRepository invoiceRepository;

    @Inject
    public InvoiceServiceImpl(final InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> getAllInvoices(InvoiceFilter invoiceFilter) throws SortException {
        List<Invoice> invoices = getAllInvoices();
        invoices = invoiceFilter.filter(invoices);
        try {
            return invoiceFilter.sort(invoices);
        } catch (Exception e) {
            throw new SortException("Invalid query string for sorting: " + invoiceFilter.getSort());
        }
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.getAllInvoices();
    }

    @Override
    public Invoice getInvoice(int invoiceId) {
        return invoiceRepository.getInvoice(invoiceId);
    }

    @Override
    public Invoice createNewInvoice(Invoice invoice) {
        invoice.setInvoiceId(invoiceRepository.getMaxId() + 1);
        return invoiceRepository.createNewInvoice(invoice);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        return invoiceRepository.updateInvoice(invoice);
    }

    @Override
    public List<Invoice> updateInvoices(List<Invoice> invoices) {
        invoiceRepository.deleteInvoices();
        invoices.forEach((invoice) -> {
            createNewInvoice(invoice);
        });
        return getAllInvoices();
    }

    @Override
    public Invoice deleteInvoice(int invoiceId) {
        Invoice invoice = getInvoice(invoiceId);
        invoiceRepository.deleteInvoice(invoiceId);
        return invoice;
    }
}
