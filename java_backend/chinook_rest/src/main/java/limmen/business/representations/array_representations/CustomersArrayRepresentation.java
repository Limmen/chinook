package limmen.business.representations.array_representations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.business.representations.entity_representation.CustomerRepresentation;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class CustomersArrayRepresentation extends ResourceSupport {
    private final List<CustomerRepresentation> customers;

    @JsonCreator
    public CustomersArrayRepresentation(@JsonProperty("customers") List<CustomerRepresentation> customers) {
        this.customers = customers;
    }

    public List<CustomerRepresentation> getCustomers() {
        return customers;
    }
}
