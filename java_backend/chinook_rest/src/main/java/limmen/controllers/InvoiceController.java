package limmen.controllers;

import limmen.business.representations.array_representations.InvoicesArrayRepresentation;
import limmen.business.representations.entity_representation.InvoiceRepresentation;
import limmen.business.services.InvoiceService;
import limmen.integration.entities.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Kim Hammar on 2016-03-22.
 */
@RestController
@RequestMapping("/resources/invoices")
public class InvoiceController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final InvoiceService invoiceService;

    @Inject
    public InvoiceController(final InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<InvoicesArrayRepresentation> getAllInvoices() {
        log.debug("HTTP GET-request /resources/invoices");
        List<InvoiceRepresentation> invoiceRepresentations = new ArrayList();
        List<Invoice> invoices = invoiceService.getAllInvoices();
        invoices.forEach((invoice) -> {
            InvoiceRepresentation invoiceRepresentation = new InvoiceRepresentation(invoice);
            invoiceRepresentation.add(linkTo(methodOn(InvoiceController.class).getInvoice(invoice.getInvoiceId())).
                    withSelfRel());
            invoiceRepresentation.add(linkTo(methodOn(CustomerController.class).
                    getCustomer(invoiceRepresentation.getInvoice().getCustomerId())).withRel("customer"));
            invoiceRepresentations.add(invoiceRepresentation);
        });
        InvoicesArrayRepresentation arrayRepresentation = new InvoicesArrayRepresentation(invoiceRepresentations);
        return new ResponseEntity<InvoicesArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    @RequestMapping(value = "/{invoiceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<InvoiceRepresentation> getInvoice(@PathVariable int invoiceId) {
        log.debug("HTTP GET-request /resources/invoices/{}", invoiceId);
        InvoiceRepresentation invoiceRepresentation = new InvoiceRepresentation(invoiceService.getInvoice(invoiceId));
        invoiceRepresentation.add(linkTo(methodOn(InvoiceController.class).getInvoice(invoiceId)).withSelfRel());
        invoiceRepresentation.add(linkTo(methodOn(CustomerController.class).
                getCustomer(invoiceRepresentation.getInvoice().getCustomerId())).withRel("customer"));
        return new ResponseEntity<InvoiceRepresentation>(invoiceRepresentation, HttpStatus.OK);
    }
}
