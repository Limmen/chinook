package limmen.controllers;

import limmen.business.representations.array_representations.InvoicesArrayRepresentation;
import limmen.business.representations.entity_representation.InvoiceRepresentation;
import limmen.business.services.InvoiceService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.InvoiceFilter;
import limmen.integration.entities.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * RestController for the resource pointed by the url: /resources/invoices*
 *
 * @author Kim Hammar on 2016-03-22.
 */
@RestController
@CrossOrigin
@RequestMapping("/resources/invoices")
public class InvoiceController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final InvoiceService invoiceService;

    /**
     * Method to be injected by the Spring container. Initializes the service.
     *
     * @param invoiceService service that handles invoice-data.
     */
    @Inject
    public InvoiceController(final InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    /**
     * Method to handle HTTP GET-requests for /resources/invoices
     *
     * @return HTTP-response, JSON array of invoices
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<InvoicesArrayRepresentation> getAllInvoices(@RequestParam(name = "invoiceId", required = false) String invoiceId,
                                                                  @RequestParam(name = "customerId", required = false) String customerId,
                                                                  @RequestParam(name = "invoiceDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date invoiceDate,
                                                                  @RequestParam(name = "billingAddress", required = false) String billingAddress,
                                                                  @RequestParam(name = "billingCity", required = false) String billingCity,
                                                                  @RequestParam(name = "billingState", required = false) String billingState,
                                                                  @RequestParam(name = "billingCountry", required = false) String billingCountry,
                                                                  @RequestParam(name = "billingPostalCode", required = false) String billingPostalCode,
                                                                  @RequestParam(name = "total", required = false) String total,
                                                                  @RequestParam(name = "sort", required = false) String sort)
            throws SortException {
        log.debug("HTTP GET-request /resources/invoices");
        InvoiceFilter invoiceFilter = new InvoiceFilter();
        invoiceFilter.setInvoiceId((invoiceId));
        invoiceFilter.setCustomerId(customerId);
        invoiceFilter.setInvoiceDate(invoiceDate);
        invoiceFilter.setBillingAddress(billingAddress);
        invoiceFilter.setBillingCity(billingCity);
        invoiceFilter.setBillingState(billingState);
        invoiceFilter.setBillingCountry(billingCountry);
        invoiceFilter.setBillingPostalCode(billingPostalCode);
        invoiceFilter.setTotal(total);
        invoiceFilter.setSort(sort);
        List<InvoiceRepresentation> invoiceRepresentations = new ArrayList();
        List<Invoice> invoices = invoiceService.getAllInvoices(invoiceFilter);
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

    /**
     * Method to handle HTTP GET-requests for /resources/invoices/{invoiceId}
     *
     * @param invoiceId id of the invoice
     * @return HTTP-response, JSON-representation of the invoice.
     */
    @CrossOrigin
    @RequestMapping(value = "/{invoiceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<InvoiceRepresentation> getInvoice(@PathVariable int invoiceId) {
        log.debug("HTTP GET-request /resources/invoices/{}", invoiceId);
        InvoiceRepresentation invoiceRepresentation = new InvoiceRepresentation(invoiceService.getInvoice(invoiceId));
        invoiceRepresentation.add(linkTo(methodOn(InvoiceController.class).getInvoice(invoiceId)).withSelfRel());
        invoiceRepresentation.add(linkTo(methodOn(CustomerController.class).
                getCustomer(invoiceRepresentation.getInvoice().getCustomerId())).withRel("customer"));
        return new ResponseEntity<InvoiceRepresentation>(invoiceRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP POST-requests for /resources/invoices
     *
     * @param invoice posted invoice data
     * @return HTTP-response, JSON representation of the created invoice
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<InvoiceRepresentation> createNewInvoice(@RequestBody Invoice invoice) {
        log.debug("HTTP POST-request /resources/invoices");
        InvoiceRepresentation invoiceRepresentation = new InvoiceRepresentation(invoiceService.createNewInvoice(invoice));
        invoiceRepresentation.add(linkTo(methodOn(InvoiceController.class).getInvoice(invoice.getInvoiceId())).
                withSelfRel());
        invoiceRepresentation.add(linkTo(methodOn(CustomerController.class).
                getCustomer(invoiceRepresentation.getInvoice().getCustomerId())).withRel("customer"));
        return new ResponseEntity<InvoiceRepresentation>(invoiceRepresentation, HttpStatus.CREATED);
    }

    /**
     * Method to handle HTTP PUT-requests for /resources/invoices/{invoiceId}
     *
     * @param invoiceId id of the invoice
     * @param invoice   put invoice data
     * @return HTTP-Response, JSON representation of the updated invoice representation
     */
    @CrossOrigin
    @RequestMapping(value = "/{invoiceId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<InvoiceRepresentation> updateInvoice(@PathVariable int invoiceId, @RequestBody Invoice invoice) {
        log.debug("HTTP PUT-request /resources/invoices/{}", invoiceId);
        invoice.setInvoiceId(invoiceId);
        InvoiceRepresentation invoiceRepresentation = new InvoiceRepresentation(invoiceService.updateInvoice(invoice));
        invoiceRepresentation.add(linkTo(methodOn(InvoiceController.class).getInvoice(invoice.getInvoiceId())).
                withSelfRel());
        invoiceRepresentation.add(linkTo(methodOn(CustomerController.class).
                getCustomer(invoiceRepresentation.getInvoice().getCustomerId())).withRel("customer"));
        return new ResponseEntity<InvoiceRepresentation>(invoiceRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle PUT-requests for /resources/invoices
     *
     * @param putInvoices
     * @return HTTP-Response, JSON representation of the updated invoices resource
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<InvoicesArrayRepresentation> updateInvoices(@RequestBody List<Invoice> putInvoices) {
        log.debug("HTTP PUT-request /resources/invoices");
        List<InvoiceRepresentation> invoiceRepresentations = new ArrayList();
        List<Invoice> invoices = invoiceService.updateInvoices(putInvoices);
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

    /**
     * Method to handle HTTP DELETE-requests for /resources/invoices/{invoiceId}
     *
     * @param invoiceId id of the invoice
     * @return HTTP-Response, JSON representation of the deleted invoice.
     */
    @CrossOrigin
    @RequestMapping(value = "/{invoiceId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<InvoiceRepresentation> deleteInvoice(@PathVariable int invoiceId) {
        log.debug("HTTP DELETE-request /resources/invoices/{}", invoiceId);
        InvoiceRepresentation invoiceRepresentation = new InvoiceRepresentation(invoiceService.deleteInvoice(invoiceId));
        invoiceRepresentation.add(linkTo(methodOn(InvoiceController.class).getInvoice(invoiceId)).
                withSelfRel());
        invoiceRepresentation.add(linkTo(methodOn(CustomerController.class).
                getCustomer(invoiceRepresentation.getInvoice().getCustomerId())).withRel("customer"));
        return new ResponseEntity<InvoiceRepresentation>(invoiceRepresentation, HttpStatus.OK);
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
