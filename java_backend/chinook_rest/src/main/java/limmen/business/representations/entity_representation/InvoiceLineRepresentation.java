package limmen.business.representations.entity_representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.integration.entities.InvoiceLine;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class InvoiceLineRepresentation extends ResourceSupport {
    private final InvoiceLine invoiceLine;

    @JsonCreator
    public InvoiceLineRepresentation(@JsonProperty("invoiceLine") InvoiceLine invoiceLine) {
        this.invoiceLine = invoiceLine;
    }

    public InvoiceLine getInvoiceLine() {
        return invoiceLine;
    }
}
