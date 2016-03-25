package limmen.business.representations.array_representations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.business.representations.entity_representation.InvoiceRepresentation;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * JSON-Representation of a Array of invoices.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class InvoicesArrayRepresentation extends ResourceSupport {
    private final List<InvoiceRepresentation> invoices;

    /**
     * Class constructor. Initializes the invoices property of the JSON representation.
     *
     * @param invoices value of the invoices property
     */
    @JsonCreator
    public InvoicesArrayRepresentation(@JsonProperty("invoices") List<InvoiceRepresentation> invoices) {
        this.invoices = invoices;
    }

    public List<InvoiceRepresentation> getInvoices() {
        return invoices;
    }
}
