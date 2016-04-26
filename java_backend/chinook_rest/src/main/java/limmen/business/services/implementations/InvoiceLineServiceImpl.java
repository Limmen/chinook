package limmen.business.services.implementations;

import limmen.business.services.InvoiceLineService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.InvoiceLineFilter;
import limmen.integration.entities.InvoiceLine;
import limmen.integration.repositories.InvoiceLineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the InvoiceLineService interface, uses a repository for database interaction.
 *
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
    public List<InvoiceLine> getAllInvoiceLines(InvoiceLineFilter invoiceLineFilter) throws SortException {
        List<InvoiceLine> invoiceLines = getAllInvoiceLines();
        invoiceLines = invoiceLineFilter.filter(invoiceLines);
        try {
            return invoiceLineFilter.sort(invoiceLines);
        } catch (Exception e) {
            throw new SortException("Invalid query string for sorting: " + invoiceLineFilter.getSort());
        }
    }

    @Override
    public List<InvoiceLine> getAllInvoiceLines() {
        return invoiceLineRepository.getAllInvoiceLines();
    }

    @Override
    public InvoiceLine getInvoiceLine(int invoiceLineId) {
        return invoiceLineRepository.getInvoiceLine(invoiceLineId);
    }

    @Override
    public InvoiceLine createNewInvoiceLine(InvoiceLine invoiceLine) {
        invoiceLine.setInvoiceLineId(invoiceLineRepository.getMaxId() + 1);
        return invoiceLineRepository.createNewInvoiceLine(invoiceLine);
    }

    @Override
    public InvoiceLine updateInvoiceLine(InvoiceLine invoiceLine) {
        return invoiceLineRepository.updateInvoiceLine(invoiceLine);
    }

    @Override
    public List<InvoiceLine> updateInvoiceLines(List<InvoiceLine> invoiceLines) {
        invoiceLineRepository.deleteInvoiceLines();
        invoiceLines.forEach((invoiceLine) -> {
            createNewInvoiceLine(invoiceLine);
        });
        return getAllInvoiceLines();
    }

    @Override
    public InvoiceLine deleteInvoiceLine(int invoiceLineId) {
        InvoiceLine invoiceLine = getInvoiceLine(invoiceLineId);
        invoiceLineRepository.deleteInvoiceLine(invoiceLineId);
        return invoiceLine;
    }
}
