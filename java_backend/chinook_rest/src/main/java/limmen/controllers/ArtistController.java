package limmen.controllers;

import limmen.business.representations.array_representations.ArtistsArrayRepresentation;
import limmen.business.representations.entity_representation.ArtistRepresentation;
import limmen.business.services.ArtistService;
import limmen.integration.entities.Artist;
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
 * RestController for the resource pointed by the url: /resources/artists*
 *
 * @author Kim Hammar on 2016-03-22.
 */
@RestController
@CrossOrigin
@RequestMapping("/resources/artists")
public class ArtistController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ArtistService artistService;

    /**
     * Method to be injected by the Spring container. Initializes the service.
     *
     * @param artistService service to handle artist-data
     */
    @Inject
    public ArtistController(final ArtistService artistService) {
        this.artistService = artistService;
    }

    /**
     * Method to handle HTTP GET-requests for /resources/artists
     *
     * @return HTTP-response, JSON array of artists
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<ArtistsArrayRepresentation> getAllArtists() {
        log.debug("HTTP GET-request /resources/artists");
        List<ArtistRepresentation> artistRepresentations = new ArrayList();
        List<Artist> artists = artistService.getAllArtists();
        artists.forEach((artist) -> {
            ArtistRepresentation artistRepresentation = new ArtistRepresentation(artist);
            artistRepresentation.add(linkTo(methodOn(ArtistController.class).getArtist(artist.getArtistId())).withSelfRel());
            artistRepresentations.add(artistRepresentation);
        });
        ArtistsArrayRepresentation arrayRepresentation = new ArtistsArrayRepresentation(artistRepresentations);
        return new ResponseEntity<ArtistsArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP GET-requests for /resources/artists/{artistId}
     *
     * @param artistId id of the artist.
     * @return HTTP-response, JSON representation of the artist
     */
    @CrossOrigin
    @RequestMapping(value = "/{artistId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<ArtistRepresentation> getArtist(@PathVariable int artistId) {
        log.debug("HTTP GET-request /resources/artists/{}", artistId);
        ArtistRepresentation artistRepresentation = new ArtistRepresentation(artistService.getArtist(artistId));
        artistRepresentation.add(linkTo(methodOn(ArtistController.class).getArtist(artistId)).withSelfRel());
        return new ResponseEntity<ArtistRepresentation>(artistRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP POST-requests for /resources/artists
     *
     * @param artist posted artist data
     * @return HTTP-response, JSON representation of the created artist
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<ArtistRepresentation> createNewArtist(@RequestBody Artist artist) {
        log.debug("HTTP POST-request /resources/artists");
        ArtistRepresentation artistRepresentation = new ArtistRepresentation(artistService.createNewArtist(artist));
        artistRepresentation.add(linkTo(methodOn(ArtistController.class).getArtist(artist.getArtistId())).withSelfRel());
        return new ResponseEntity<ArtistRepresentation>(artistRepresentation, HttpStatus.CREATED);
    }

    /**
     * Method to handle HTTP PUT-requests for /resources/artists/{artistId}
     *
     * @param artistId id of the artist
     * @param artist put artist data
     * @return HTTP-Response, JSON representation of the updated artist representation
     */
    @CrossOrigin
    @RequestMapping(value = "/{artistId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<ArtistRepresentation> updateArtist(@PathVariable int artistId, @RequestBody Artist artist) {
        log.debug("HTTP PUT-request /resources/artists/{}", artistId);
        artist.setArtistId(artistId);
        ArtistRepresentation artistRepresentation = new ArtistRepresentation(artistService.updateArtist(artist));
        artistRepresentation.add(linkTo(methodOn(ArtistController.class).getArtist(artist.getArtistId())).withSelfRel());
        return new ResponseEntity<ArtistRepresentation>(artistRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP DELETE-requests for /resources/artists/{artistId}
     *
     * @param artistId id of the artist
     * @return HTTP-Response, JSON representation of the deleted artist.
     */
    @CrossOrigin
    @RequestMapping(value = "/{artistId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<ArtistRepresentation> deleteArtist(@PathVariable int artistId) {
        log.debug("HTTP DELETE-request /resources/artists/{}", artistId);
        ArtistRepresentation artistRepresentation = new ArtistRepresentation(artistService.deleteArtist(artistId));
        artistRepresentation.add(linkTo(methodOn(ArtistController.class).getArtist(artistId)).withSelfRel());
        return new ResponseEntity<ArtistRepresentation>(artistRepresentation, HttpStatus.OK);
    }

    /**
     * ExceptionHandler for when a requested resource was not found.
     *
     * @param response HTTP response to send back to client
     * @throws IOException
     */
    @ExceptionHandler(org.springframework.dao.EmptyResultDataAccessException.class)
    void notFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), "Resource not Found");
    }

}
