package limmen.controllers;

import limmen.business.representations.entity_representation.ArtistRepresentation;
import limmen.business.representations.array_representations.ArtistsArrayRepresentation;
import limmen.business.services.ArtistService;
import limmen.integration.entities.Artist;
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
@RequestMapping("/resources/artists")
public class TrackController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ArtistService artistService;

    @Inject
    public TrackController(final ArtistService artistService) {
        this.artistService = artistService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<ArtistsArrayRepresentation> getAllArtists() {
        log.debug("HTTP GET-request /resources/artists");
        List<ArtistRepresentation> artistRepresentations = new ArrayList();
        List<Artist> artists = artistService.getAllArtists();
        artists.forEach((artist) -> {
            ArtistRepresentation artistRepresentation = new ArtistRepresentation(artist);
            artistRepresentation.add(linkTo(methodOn(TrackController.class).getArtist(artist.getArtistId())).withSelfRel());
            artistRepresentations.add(artistRepresentation);
        });
        ArtistsArrayRepresentation arrayRepresentation = new ArtistsArrayRepresentation(artistRepresentations);
        return new ResponseEntity<ArtistsArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    @RequestMapping(value = "/{artistId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<ArtistRepresentation> getArtist(@PathVariable int artistId) {
        log.debug("HTTP GET-request /resources/artists/{}", artistId);
        ArtistRepresentation artistRepresentation = new ArtistRepresentation(artistService.getArtist(artistId));
        artistRepresentation.add(linkTo(methodOn(TrackController.class).getArtist(artistId)).withSelfRel());
        return new ResponseEntity<ArtistRepresentation>(artistRepresentation, HttpStatus.OK);
    }
}
