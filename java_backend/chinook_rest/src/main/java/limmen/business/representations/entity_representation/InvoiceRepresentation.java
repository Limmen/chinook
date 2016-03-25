package limmen.business.representations.entity_representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.integration.entities.Invoice;
import org.springframework.hateoas.ResourceSupport;

/**
 * JSON-Representation of a Invoice entity.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class InvoiceRepresentation extends ResourceSupport {
    private final Invoice invoice;

    /**
     * Class constructor. Initializes the "invoice" property of the JSON representation.
     *
     * @param invoice value of the invoice property.
     */
    @JsonCreator
    public InvoiceRepresentation(@JsonProperty("invoice") Invoice invoice) {
        this.invoice = invoice;
    }

    public Invoice getInvoice() {
        return invoice;
    }
}
