package limmen.business.representations.entity_representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.integration.entities.Customer;
import org.springframework.hateoas.ResourceSupport;

/**
 * JSON-Representation of a Customer entity.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class CustomerRepresentation extends ResourceSupport {
    private final Customer customer;

    /**
     * Class constructor. Initializes the "customer" property of the JSON representation.
     *
     * @param customer value of the customer property.
     */
    @JsonCreator
    public CustomerRepresentation(@JsonProperty("customer") Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
