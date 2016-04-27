package limmen.business.representations.entity_representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.integration.entities.InvoiceLine;
import org.springframework.hateoas.ResourceSupport;

/**
 * JSON-Representation of a InvoiceLine entity.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class InvoiceLineRepresentation extends ResourceSupport {
    private final InvoiceLine invoiceLine;

    /**
     * Class construcotr. Initializes the invoiceLine property of the JSON representation.
     *
     * @param invoiceLine value of the invoiceline property.
     */
    @JsonCreator
    public InvoiceLineRepresentation(@JsonProperty("invoiceline") InvoiceLine invoiceLine) {
        this.invoiceLine = invoiceLine;
    }

    public InvoiceLine getInvoiceLine() {
        return invoiceLine;
    }
}
