package limmen.controllers;

import limmen.business.representations.array_representations.TracksArrayRepresentation;
import limmen.business.representations.entity_representation.TrackRepresentation;
import limmen.business.services.TrackService;
import limmen.integration.entities.Track;
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
 * RestController for the resource pointed by the url: /resources/tracks*
 *
 * @author Kim Hammar on 2016-03-22.
 */
@RestController
@CrossOrigin
@RequestMapping("/resources/tracks")
public class TrackController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final TrackService trackService;

    /**
     * Method to be injected by the Spring container. Initializes the service.
     *
     * @param trackService service to handle track-data.
     */
    @Inject
    public TrackController(final TrackService trackService) {
        this.trackService = trackService;
    }

    /**
     * Method to handle HTTP-requests for /resources/tracks
     *
     * @return HTTP-response, JSON array of tracks
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<TracksArrayRepresentation> getAllTracks() {
        log.debug("HTTP GET-request /resources/tracks");
        List<TrackRepresentation> trackRepresentations = new ArrayList();
        List<Track> tracks = trackService.getAllTracks();
        tracks.forEach((track) -> {
            TrackRepresentation trackRepresentation = new TrackRepresentation(track);
            trackRepresentation.add(linkTo(methodOn(TrackController.class).getTrack(track.getTrackId())).withSelfRel());
            trackRepresentation.add(linkTo(methodOn(AlbumController.class).
                    getAlbum(trackRepresentation.getTrack().getAlbumId())).withRel("album"));
            trackRepresentation.add(linkTo(methodOn(MediaTypeController.class).
                    getMediaType(trackRepresentation.getTrack().getMediaTypeId())).withRel("mediatype"));
            trackRepresentation.add(linkTo(methodOn(GenreController.class).
                    getGenre(trackRepresentation.getTrack().getGenreId())).withRel("genre"));
            trackRepresentations.add(trackRepresentation);
        });
        TracksArrayRepresentation arrayRepresentation = new TracksArrayRepresentation(trackRepresentations);
        return new ResponseEntity<TracksArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP-requests for /resources/tracks/{trackId}
     *
     * @param trackId id of the track
     * @return HTTP-response, JSON-representation of the track.
     */
    @CrossOrigin
    @RequestMapping(value = "/{trackId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<TrackRepresentation> getTrack(@PathVariable int trackId) {
        log.debug("HTTP GET-request /resources/tracks/{}", trackId);
        TrackRepresentation trackRepresentation = new TrackRepresentation(trackService.getTrack(trackId));
        trackRepresentation.add(linkTo(methodOn(TrackController.class).getTrack(trackId)).withSelfRel());
        trackRepresentation.add(linkTo(methodOn(AlbumController.class).
                getAlbum(trackRepresentation.getTrack().getAlbumId())).withRel("album"));
        trackRepresentation.add(linkTo(methodOn(MediaTypeController.class).
                getMediaType(trackRepresentation.getTrack().getMediaTypeId())).withRel("mediatype"));
        trackRepresentation.add(linkTo(methodOn(GenreController.class).
                getGenre(trackRepresentation.getTrack().getGenreId())).withRel("genre"));
        return new ResponseEntity<TrackRepresentation>(trackRepresentation, HttpStatus.OK);
    }
}
