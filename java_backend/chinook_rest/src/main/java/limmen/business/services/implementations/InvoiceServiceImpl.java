package limmen.business.services.implementations;

import limmen.business.services.InvoiceService;
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
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.getAllInvoices();
    }

    @Override
    public Invoice getInvoice(int invoiceId) {
        return invoiceRepository.getInvoice(invoiceId);
    }
}
