package limmen.business.representations.array_representations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.business.representations.entity_representation.InvoiceRepresentation;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class InvoicesArrayRepresentation extends ResourceSupport {
    private final List<InvoiceRepresentation> invoices;

    @JsonCreator
    public InvoicesArrayRepresentation(@JsonProperty("invoices") List<InvoiceRepresentation> invoices) {
        this.invoices = invoices;
    }

    public List<InvoiceRepresentation> getInvoices() {
        return invoices;
    }
}
