package limmen.controllers;

import limmen.business.representations.array_representations.TracksArrayRepresentation;
import limmen.business.representations.entity_representation.TrackRepresentation;
import limmen.business.services.TrackService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.TrackFilter;
import limmen.integration.entities.Track;
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
     * Method to handle HTTP GET-requests for /resources/tracks
     *
     * @return HTTP-response, JSON array of tracks
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<TracksArrayRepresentation> getAllTracks(@RequestParam(name = "trackId", required = false) String trackId,
                                                              @RequestParam(name = "name", required = false) String name,
                                                              @RequestParam(name = "albumId", required = false) String albumId,
                                                              @RequestParam(name = "mediaTypeId", required = false) String mediaTypeId,
                                                              @RequestParam(name = "genreId", required = false) String genreId,
                                                              @RequestParam(name = "composer", required = false) String composer,
                                                              @RequestParam(name = "milliseconds", required = false) String milliseconds,
                                                              @RequestParam(name = "bytes", required = false) String bytes,
                                                              @RequestParam(name = "unitPrice", required = false) String unitPrice,
                                                              @RequestParam(name = "sort", required = false) String sort)
            throws SortException {
        log.debug("HTTP GET-request /resources/tracks");
        log.debug("RECEIVED QUERY PARAM COMPOSER: {}", composer);
        TrackFilter trackFilter = new TrackFilter();
        trackFilter.setTrackId(trackId);
        trackFilter.setName(name);
        trackFilter.setAlbumId(albumId);
        trackFilter.setMediaTypeId(mediaTypeId);
        trackFilter.setGenreId(genreId);
        trackFilter.setComposer(composer);
        trackFilter.setMilliseconds(milliseconds);
        trackFilter.setBytes(bytes);
        trackFilter.setUnitPrice(unitPrice);
        trackFilter.setSort(sort);
        List<TrackRepresentation> trackRepresentations = new ArrayList();
        List<Track> tracks = trackService.getAllTracks(trackFilter);
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
     * Method to handle HTTP GET-requests for /resources/tracks/{trackId}
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


    /**
     * Method to handle HTTP POST-requests for /resources/tracks
     *
     * @param track posted track data
     * @return HTTP-response, JSON representation of the created track
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<TrackRepresentation> createNewTrack(@RequestBody Track track) {
        log.debug("HTTP POST-request /resources/tracks");
        TrackRepresentation trackRepresentation = new TrackRepresentation(trackService.createNewTrack(track));
        trackRepresentation.add(linkTo(methodOn(TrackController.class).getTrack(track.getTrackId())).withSelfRel());
        trackRepresentation.add(linkTo(methodOn(AlbumController.class).
                getAlbum(trackRepresentation.getTrack().getAlbumId())).withRel("album"));
        trackRepresentation.add(linkTo(methodOn(MediaTypeController.class).
                getMediaType(trackRepresentation.getTrack().getMediaTypeId())).withRel("mediatype"));
        trackRepresentation.add(linkTo(methodOn(GenreController.class).
                getGenre(trackRepresentation.getTrack().getGenreId())).withRel("genre"));
        return new ResponseEntity<TrackRepresentation>(trackRepresentation, HttpStatus.CREATED);
    }

    /**
     * Method to handle HTTP PUT-requests for /resources/tracks/{trackId}
     *
     * @param trackId id of the track
     * @param track   put track data
     * @return HTTP-Response, JSON representation of the updated track representation
     */
    @CrossOrigin
    @RequestMapping(value = "/{trackId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<TrackRepresentation> updateTrack(@PathVariable int trackId, @RequestBody Track track) {
        log.debug("HTTP PUT-request /resources/tracks/{}", trackId);
        track.setTrackId(trackId);
        TrackRepresentation trackRepresentation = new TrackRepresentation(trackService.updateTrack(track));
        trackRepresentation.add(linkTo(methodOn(TrackController.class).getTrack(track.getTrackId())).withSelfRel());
        trackRepresentation.add(linkTo(methodOn(AlbumController.class).
                getAlbum(trackRepresentation.getTrack().getAlbumId())).withRel("album"));
        trackRepresentation.add(linkTo(methodOn(MediaTypeController.class).
                getMediaType(trackRepresentation.getTrack().getMediaTypeId())).withRel("mediatype"));
        trackRepresentation.add(linkTo(methodOn(GenreController.class).
                getGenre(trackRepresentation.getTrack().getGenreId())).withRel("genre"));
        return new ResponseEntity<TrackRepresentation>(trackRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle PUT-requests for /resources/tracks
     *
     * @param putTracks
     * @return HTTP-Response, JSON representation of the updated tracks resource
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<TracksArrayRepresentation> updateTracks(@RequestBody List<Track> putTracks) {
        log.debug("HTTP PUT-request /resources/tracks");
        List<TrackRepresentation> trackRepresentations = new ArrayList();
        List<Track> tracks = trackService.updateTracks(putTracks);
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
     * Method to handle HTTP DELETE-requests for /resources/tracks/{trackId}
     *
     * @param trackId id of the track
     * @return HTTP-Response, JSON representation of the deleted track.
     */
    @CrossOrigin
    @RequestMapping(value = "/{trackId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<TrackRepresentation> deleteTrack(@PathVariable int trackId) {
        log.debug("HTTP DELETE-request /resources/tracks/{}", trackId);
        TrackRepresentation trackRepresentation = new TrackRepresentation(trackService.deleteTrack(trackId));
        trackRepresentation.add(linkTo(methodOn(TrackController.class).getTrack(trackId)).withSelfRel());
        trackRepresentation.add(linkTo(methodOn(AlbumController.class).
                getAlbum(trackRepresentation.getTrack().getAlbumId())).withRel("album"));
        trackRepresentation.add(linkTo(methodOn(MediaTypeController.class).
                getMediaType(trackRepresentation.getTrack().getMediaTypeId())).withRel("mediatype"));
        trackRepresentation.add(linkTo(methodOn(GenreController.class).
                getGenre(trackRepresentation.getTrack().getGenreId())).withRel("genre"));
        return new ResponseEntity<TrackRepresentation>(trackRepresentation, HttpStatus.OK);
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
