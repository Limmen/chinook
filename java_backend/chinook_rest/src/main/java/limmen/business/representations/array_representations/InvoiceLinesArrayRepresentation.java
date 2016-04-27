package limmen.business.representations.array_representations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.business.representations.entity_representation.InvoiceLineRepresentation;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * JSON-Representation of a Array of invoicelines.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class InvoiceLinesArrayRepresentation extends ResourceSupport {
    private final List<InvoiceLineRepresentation> invoiceLines;

    /**
     * Class constructor. Initializes the invoicelines property of the JSON representation.
     *
     * @param invoiceLines value of the invoicelines property
     */
    @JsonCreator
    public InvoiceLinesArrayRepresentation(@JsonProperty("invoicelines") List<InvoiceLineRepresentation> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public List<InvoiceLineRepresentation> getInvoiceLines() {
        return invoiceLines;
    }
}
