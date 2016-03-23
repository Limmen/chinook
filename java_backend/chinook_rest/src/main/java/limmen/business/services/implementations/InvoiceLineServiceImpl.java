package limmen.business.services.implementations;

import limmen.business.services.InvoiceLineService;
import limmen.integration.entities.InvoiceLine;
import limmen.integration.repositories.InvoiceLineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
@Service
public class InvoiceLineServiceImpl implements InvoiceLineService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final InvoiceLineRepository invoiceLineRepository;

    @Inject
    public InvoiceLineServiceImpl(final InvoiceLineRepository invoiceLineRepository) {
        this.invoiceLineRepository = invoiceLineRepository;
    }

    @Override
    public List<InvoiceLine> getAllInvoiceLines() {
        return invoiceLineRepository.getAllInvoiceLines();
    }

    @Override
    public InvoiceLine getInvoiceLine(int invoiceLineId) {
        return invoiceLineRepository.getInvoiceLine(invoiceLineId);
    }
}
