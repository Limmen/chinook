package limmen.controllers;

import limmen.business.representations.array_representations.MediaTypesArrayRepresentation;
import limmen.business.representations.entity_representation.MediaTypeRepresentation;
import limmen.business.services.MediaTypeService;
import limmen.integration.entities.MediaTypeEntity;
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
@RequestMapping("/resources/mediatypes")
public class MediaTypeController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final MediaTypeService mediaTypeService;

    @Inject
    public MediaTypeController(final MediaTypeService mediaTypeService) {
        this.mediaTypeService = mediaTypeService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<MediaTypesArrayRepresentation> getAllMediaTypes() {
        log.debug("HTTP GET-request /resources/mediatypes");
        List<MediaTypeRepresentation> mediaTypeRepresentations = new ArrayList();
        List<MediaTypeEntity> mediaTypes = mediaTypeService.getAllMediaTypes();
        mediaTypes.forEach((mediaType) -> {
            MediaTypeRepresentation mediaTypeRepresentation = new MediaTypeRepresentation(mediaType);
            mediaTypeRepresentation.add(linkTo(methodOn(MediaTypeController.class).getMediaType(mediaType.getMediaTypeId())).withSelfRel());
            mediaTypeRepresentations.add(mediaTypeRepresentation);
        });
        MediaTypesArrayRepresentation arrayRepresentation = new MediaTypesArrayRepresentation(mediaTypeRepresentations);
        return new ResponseEntity<MediaTypesArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    @RequestMapping(value = "/{mediaTypeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<MediaTypeRepresentation> getMediaType(@PathVariable int mediaTypeId) {
        log.debug("HTTP GET-request /resources/mediaTypes/{}", mediaTypeId);
        MediaTypeRepresentation mediaTypeRepresentation = new MediaTypeRepresentation(mediaTypeService.getMediaType(mediaTypeId));
        mediaTypeRepresentation.add(linkTo(methodOn(MediaTypeController.class).getMediaType(mediaTypeId)).withSelfRel());
        return new ResponseEntity<MediaTypeRepresentation>(mediaTypeRepresentation, HttpStatus.OK);
    }
}
