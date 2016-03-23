package limmen.business.representations.array_representations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.business.representations.entity_representation.InvoiceLineRepresentation;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class InvoiceLinesArrayRepresentation extends ResourceSupport {
    private final List<InvoiceLineRepresentation> invoiceLines;

    @JsonCreator
    public InvoiceLinesArrayRepresentation(@JsonProperty("invoiceLines") List<InvoiceLineRepresentation> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public List<InvoiceLineRepresentation> getInvoiceLines() {
        return invoiceLines;
    }
}
