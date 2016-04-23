package limmen.controllers;

import limmen.business.representations.array_representations.InvoiceLinesArrayRepresentation;
import limmen.business.representations.entity_representation.InvoiceLineRepresentation;
import limmen.business.services.InvoiceLineService;
import limmen.integration.entities.InvoiceLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * RestController for the resource pointed by the url: /resources/invoicelines*
 *
 * @author Kim Hammar on 2016-03-22.
 */
@RestController
@CrossOrigin
@RequestMapping("/resources/invoicelines")
public class InvoiceLineController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final InvoiceLineService invoiceLineService;

    /**
     * Method to be injected by the Spring container. Initializes the service.
     *
     * @param invoiceLineService service to handle invoiceline-data
     */
    @Inject
    public InvoiceLineController(final InvoiceLineService invoiceLineService) {
        this.invoiceLineService = invoiceLineService;
    }

    /**
     * Method to handle HTTP GET-requests for /resources/invoicelines
     *
     * @return HTTP-response, JSON array of invoicelines
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<InvoiceLinesArrayRepresentation> getAllInvoiceLines() {
        log.debug("HTTP GET-request /resources/invoicelines");
        List<InvoiceLineRepresentation> invoiceLineRepresentations = new ArrayList();
        List<InvoiceLine> invoiceLines = invoiceLineService.getAllInvoiceLines();
        invoiceLines.forEach((invoiceLine) -> {
            InvoiceLineRepresentation invoiceLineRepresentation = new InvoiceLineRepresentation(invoiceLine);
            invoiceLineRepresentation.add(linkTo(methodOn(InvoiceLineController.class).
                    getInvoiceLine(invoiceLine.getInvoiceLineId())).withSelfRel());
            invoiceLineRepresentation.add(linkTo(methodOn(InvoiceController.class).
                    getInvoice(invoiceLineRepresentation.getInvoiceLine().getInvoiceId())).withRel("invoice"));
            invoiceLineRepresentation.add(linkTo(methodOn(TrackController.class).
                    getTrack(invoiceLineRepresentation.getInvoiceLine().getTrackId())).withRel("track"));
            invoiceLineRepresentations.add(invoiceLineRepresentation);
        });
        InvoiceLinesArrayRepresentation arrayRepresentation = new InvoiceLinesArrayRepresentation(invoiceLineRepresentations);
        return new ResponseEntity<InvoiceLinesArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP GET-requests for /resources/invoicelines/{invoicelinesId}
     *
     * @param invoiceLineId id of the invoiceline.
     * @return HTTP-response, JSON-representation of the invoiceline.
     */
    @CrossOrigin
    @RequestMapping(value = "/{invoiceLineId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<InvoiceLineRepresentation> getInvoiceLine(@PathVariable int invoiceLineId) {
        log.debug("HTTP GET-request /resources/invoicelines/{}", invoiceLineId);
        InvoiceLineRepresentation invoiceLineRepresentation =
                new InvoiceLineRepresentation(invoiceLineService.getInvoiceLine(invoiceLineId));
        invoiceLineRepresentation.add(linkTo(methodOn(InvoiceLineController.class).getInvoiceLine(invoiceLineId)).
                withSelfRel());
        invoiceLineRepresentation.add(linkTo(methodOn(InvoiceController.class).
                getInvoice(invoiceLineRepresentation.getInvoiceLine().getInvoiceId())).withRel("invoice"));
        invoiceLineRepresentation.add(linkTo(methodOn(TrackController.class).
                getTrack(invoiceLineRepresentation.getInvoiceLine().getTrackId())).withRel("track"));
        return new ResponseEntity<InvoiceLineRepresentation>(invoiceLineRepresentation, HttpStatus.OK);
    }
}
