package limmen.controllers;

import limmen.business.representations.array_representations.MediaTypesArrayRepresentation;
import limmen.business.representations.entity_representation.MediaTypeRepresentation;
import limmen.business.services.MediaTypeService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.MediaTypeFilter;
import limmen.integration.entities.MediaTypeEntity;
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
 * RestController for the resource pointed by the url: /resources/mediatypes*
 *
 * @author Kim Hammar on 2016-03-22.
 */
@RestController
@CrossOrigin
@RequestMapping("/resources/mediatypes")
public class MediaTypeController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final MediaTypeService mediaTypeService;

    /**
     * Method to be injected by the Spring container. Initializes the service.
     *
     * @param mediaTypeService service to handle mediatype-data
     */
    @Inject
    public MediaTypeController(final MediaTypeService mediaTypeService) {
        this.mediaTypeService = mediaTypeService;
    }

    /**
     * Method to handle HTTP GET-requests for /resources/mediatypes
     *
     * @return HTTP-response, JSON array of mediatypes
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<MediaTypesArrayRepresentation> getAllMediaTypes(@RequestParam(name = "mediaTypeId", required = false) String mediaTypeId,
                                                                      @RequestParam(name = "name", required = false) String name,
                                                                      @RequestParam(name = "sort", required = false) String sort)
            throws SortException {
        log.debug("HTTP GET-request /resources/mediatypes");
        MediaTypeFilter mediaTypeFilter = new MediaTypeFilter();
        mediaTypeFilter.setMediaTypeId(mediaTypeId);
        mediaTypeFilter.setName(name);
        mediaTypeFilter.setSort(sort);
        List<MediaTypeRepresentation> mediaTypeRepresentations = new ArrayList();
        List<MediaTypeEntity> mediaTypes = mediaTypeService.getAllMediaTypes(mediaTypeFilter);
        mediaTypes.forEach((mediaType) -> {
            MediaTypeRepresentation mediaTypeRepresentation = new MediaTypeRepresentation(mediaType);
            mediaTypeRepresentation.add(linkTo(methodOn(MediaTypeController.class).getMediaType(mediaType.getMediaTypeId())).withSelfRel());
            mediaTypeRepresentations.add(mediaTypeRepresentation);
        });
        MediaTypesArrayRepresentation arrayRepresentation = new MediaTypesArrayRepresentation(mediaTypeRepresentations);
        return new ResponseEntity<MediaTypesArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP GET-requests for /resources/mediatypes/{mediatypeId}
     *
     * @param mediaTypeId id of the mediatype
     * @return HTTP-response, JSON-representation of the mediatype
     */
    @CrossOrigin
    @RequestMapping(value = "/{mediaTypeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<MediaTypeRepresentation> getMediaType(@PathVariable int mediaTypeId) {
        log.debug("HTTP GET-request /resources/mediaTypes/{}", mediaTypeId);
        MediaTypeRepresentation mediaTypeRepresentation = new MediaTypeRepresentation(mediaTypeService.getMediaType(mediaTypeId));
        mediaTypeRepresentation.add(linkTo(methodOn(MediaTypeController.class).getMediaType(mediaTypeId)).withSelfRel());
        return new ResponseEntity<MediaTypeRepresentation>(mediaTypeRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP POST-requests for /resources/mediaTypes
     *
     * @param mediaType posted mediaType data
     * @return HTTP-response, JSON representation of the created mediaType
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<MediaTypeRepresentation> createNewMediaType(@RequestBody MediaTypeEntity mediaType) {
        log.debug("HTTP POST-request /resources/mediaTypes");
        MediaTypeRepresentation mediaTypeRepresentation = new MediaTypeRepresentation(mediaTypeService.createNewMediaType(mediaType));
        mediaTypeRepresentation.add(linkTo(methodOn(MediaTypeController.class).getMediaType(mediaType.getMediaTypeId())).withSelfRel());
        return new ResponseEntity<MediaTypeRepresentation>(mediaTypeRepresentation, HttpStatus.CREATED);
    }

    /**
     * Method to handle HTTP PUT-requests for /resources/mediaTypes/{mediaTypeId}
     *
     * @param mediaTypeId id of the mediaType
     * @param mediaType   put mediaType data
     * @return HTTP-Response, JSON representation of the updated mediaType representation
     */
    @CrossOrigin
    @RequestMapping(value = "/{mediaTypeId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<MediaTypeRepresentation> updateMediaType(@PathVariable int mediaTypeId, @RequestBody MediaTypeEntity mediaType) {
        log.debug("HTTP PUT-request /resources/mediaTypes/{}", mediaTypeId);
        mediaType.setMediaTypeId(mediaTypeId);
        MediaTypeRepresentation mediaTypeRepresentation = new MediaTypeRepresentation(mediaTypeService.updateMediaType(mediaType));
        mediaTypeRepresentation.add(linkTo(methodOn(MediaTypeController.class).getMediaType(mediaType.getMediaTypeId())).withSelfRel());
        return new ResponseEntity<MediaTypeRepresentation>(mediaTypeRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle PUT-requests for /resources/mediaTypes
     *
     * @param putMediaTypes
     * @return HTTP-Response, JSON representation of the updated mediaTypes resource
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<MediaTypesArrayRepresentation> updateMediaTypes(@RequestBody List<MediaTypeEntity> putMediaTypes) {
        log.debug("HTTP PUT-request /resources/mediaTypes");
        List<MediaTypeRepresentation> mediaTypeRepresentations = new ArrayList();
        List<MediaTypeEntity> mediaTypes = mediaTypeService.updateMediaTypes(putMediaTypes);
        mediaTypes.forEach((mediaType) -> {
            MediaTypeRepresentation mediaTypeRepresentation = new MediaTypeRepresentation(mediaType);
            mediaTypeRepresentation.add(linkTo(methodOn(MediaTypeController.class).getMediaType(mediaType.getMediaTypeId())).withSelfRel());
            mediaTypeRepresentations.add(mediaTypeRepresentation);
        });
        MediaTypesArrayRepresentation arrayRepresentation = new MediaTypesArrayRepresentation(mediaTypeRepresentations);
        return new ResponseEntity<MediaTypesArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP DELETE-requests for /resources/mediaTypes/{mediaTypeId}
     *
     * @param mediaTypeId id of the mediaType
     * @return HTTP-Response, JSON representation of the deleted mediaType.
     */
    @CrossOrigin
    @RequestMapping(value = "/{mediaTypeId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<MediaTypeRepresentation> deleteMediaType(@PathVariable int mediaTypeId) {
        log.debug("HTTP DELETE-request /resources/mediaTypes/{}", mediaTypeId);
        MediaTypeRepresentation mediaTypeRepresentation = new MediaTypeRepresentation(mediaTypeService.deleteMediaType(mediaTypeId));
        mediaTypeRepresentation.add(linkTo(methodOn(MediaTypeController.class).getMediaType(mediaTypeId)).withSelfRel());
        return new ResponseEntity<MediaTypeRepresentation>(mediaTypeRepresentation, HttpStatus.OK);
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
