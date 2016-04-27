package limmen.controllers;

import limmen.business.representations.array_representations.InvoiceLinesArrayRepresentation;
import limmen.business.representations.entity_representation.InvoiceLineRepresentation;
import limmen.business.services.InvoiceLineService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.InvoiceLineFilter;
import limmen.integration.entities.InvoiceLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public HttpEntity<InvoiceLinesArrayRepresentation> getAllInvoiceLines(@RequestParam(name = "invoiceLineId", required = false) String invoiceLineId,
                                                                          @RequestParam(name = "invoiceId", required = false) String invoiceId,
                                                                          @RequestParam(name = "trackId", required = false) String trackId,
                                                                          @RequestParam(name = "unitPrice", required = false) String unitPrice,
                                                                          @RequestParam(name = "quantity", required = false) String quantity,
                                                                          @RequestParam(name = "sort", required = false) String sort)
            throws SortException {
        log.debug("HTTP GET-request /resources/invoicelines");
        InvoiceLineFilter invoiceLineFilter = new InvoiceLineFilter();
        invoiceLineFilter.setInvoiceLineId(invoiceLineId);
        invoiceLineFilter.setInvoiceId(invoiceId);
        invoiceLineFilter.setTrackId(trackId);
        invoiceLineFilter.setUnitPrice(unitPrice);
        invoiceLineFilter.setQuantity(quantity);
        invoiceLineFilter.setSort(sort);
        List<InvoiceLineRepresentation> invoiceLineRepresentations = new ArrayList();
        List<InvoiceLine> invoiceLines = invoiceLineService.getAllInvoiceLines(invoiceLineFilter);
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

    /**
     * Method to handle HTTP POST-requests for /resources/invoiceLines
     *
     * @param invoiceLine posted invoiceLine data
     * @return HTTP-response, JSON representation of the created invoiceLine
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<InvoiceLineRepresentation> createNewInvoiceLine(@RequestBody InvoiceLine invoiceLine) {
        log.debug("HTTP POST-request /resources/invoicelines");
        InvoiceLineRepresentation invoiceLineRepresentation = new InvoiceLineRepresentation(invoiceLineService.createNewInvoiceLine(invoiceLine));
        invoiceLineRepresentation.add(linkTo(methodOn(InvoiceLineController.class).
                getInvoiceLine(invoiceLine.getInvoiceLineId())).withSelfRel());
        invoiceLineRepresentation.add(linkTo(methodOn(InvoiceController.class).
                getInvoice(invoiceLineRepresentation.getInvoiceLine().getInvoiceId())).withRel("invoice"));
        invoiceLineRepresentation.add(linkTo(methodOn(TrackController.class).
                getTrack(invoiceLineRepresentation.getInvoiceLine().getTrackId())).withRel("track"));
        return new ResponseEntity<InvoiceLineRepresentation>(invoiceLineRepresentation, HttpStatus.CREATED);
    }

    /**
     * Method to handle HTTP PUT-requests for /resources/invoiceLines/{invoiceLineId}
     *
     * @param invoiceLineId id of the invoiceLine
     * @param invoiceLine   put invoiceLine data
     * @return HTTP-Response, JSON representation of the updated invoiceLine representation
     */
    @CrossOrigin
    @RequestMapping(value = "/{invoiceLineId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<InvoiceLineRepresentation> updateInvoiceLine(@PathVariable int invoiceLineId, @RequestBody InvoiceLine invoiceLine) {
        log.debug("HTTP PUT-request /resources/invoicelines/{}", invoiceLineId);
        invoiceLine.setInvoiceLineId(invoiceLineId);
        InvoiceLineRepresentation invoiceLineRepresentation = new InvoiceLineRepresentation(invoiceLineService.updateInvoiceLine(invoiceLine));
        invoiceLineRepresentation.add(linkTo(methodOn(InvoiceLineController.class).
                getInvoiceLine(invoiceLine.getInvoiceLineId())).withSelfRel());
        invoiceLineRepresentation.add(linkTo(methodOn(InvoiceController.class).
                getInvoice(invoiceLineRepresentation.getInvoiceLine().getInvoiceId())).withRel("invoice"));
        invoiceLineRepresentation.add(linkTo(methodOn(TrackController.class).
                getTrack(invoiceLineRepresentation.getInvoiceLine().getTrackId())).withRel("track"));
        return new ResponseEntity<InvoiceLineRepresentation>(invoiceLineRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle PUT-requests for /resources/invoiceLines
     *
     * @param putInvoiceLines
     * @return HTTP-Response, JSON representation of the updated invoiceLines resource
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<InvoiceLinesArrayRepresentation> updateInvoiceLines(@RequestBody List<InvoiceLine> putInvoiceLines) {
        log.debug("HTTP PUT-request /resources/invoicelines");
        List<InvoiceLineRepresentation> invoiceLineRepresentations = new ArrayList();
        List<InvoiceLine> invoiceLines = invoiceLineService.updateInvoiceLines(putInvoiceLines);
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
     * Method to handle HTTP DELETE-requests for /resources/invoiceLines/{invoiceLineId}
     *
     * @param invoiceLineId id of the invoiceLine
     * @return HTTP-Response, JSON representation of the deleted invoiceLine.
     */
    @CrossOrigin
    @RequestMapping(value = "/{invoiceLineId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<InvoiceLineRepresentation> deleteInvoiceLine(@PathVariable int invoiceLineId) {
        log.debug("HTTP DELETE-request /resources/invoicelines/{}", invoiceLineId);
        InvoiceLineRepresentation invoiceLineRepresentation = new InvoiceLineRepresentation(invoiceLineService.deleteInvoiceLine(invoiceLineId));
        invoiceLineRepresentation.add(linkTo(methodOn(InvoiceLineController.class).
                getInvoiceLine(invoiceLineId)).withSelfRel());
        invoiceLineRepresentation.add(linkTo(methodOn(InvoiceController.class).
                getInvoice(invoiceLineRepresentation.getInvoiceLine().getInvoiceId())).withRel("invoice"));
        invoiceLineRepresentation.add(linkTo(methodOn(TrackController.class).
                getTrack(invoiceLineRepresentation.getInvoiceLine().getTrackId())).withRel("track"));
        return new ResponseEntity<InvoiceLineRepresentation>(invoiceLineRepresentation, HttpStatus.OK);
    }

    /**
     * ExceptionHandler for when a requested resource was not found.
     *
     * @param response HTTP response to send back to client
     * @throws IOException
     */
    @ExceptionHandler(org.springframework.dao.EmptyResultDataAccessException.class)
    void notFound(HttpServletResponse response) throws IOException {
        log.debug("org.springframework.dao.EmptyResultDataAccessException.class exception caught");
        response.sendError(HttpStatus.NOT_FOUND.value(), "Resource not Found");
    }

    /**
     * ExceptionHandler for when query string was invalid.
     *
     * @param response HTTP response to send back to client
     * @throws IOException
     */
    @ExceptionHandler(SortException.class)
    void invalidQueryString(HttpServletResponse response) throws IOException {
        log.debug("invalid query string exception caught");
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid Query String");
    }
}
