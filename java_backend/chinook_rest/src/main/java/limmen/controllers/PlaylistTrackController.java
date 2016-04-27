package limmen.controllers;

import limmen.business.representations.array_representations.PlaylistTracksArrayRepresentation;
import limmen.business.representations.entity_representation.PlaylistTrackRepresentation;
import limmen.business.services.PlaylistTrackService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.PlaylistTrackFilter;
import limmen.integration.entities.PlaylistTrack;
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
 * RestController for the resource pointed by the url: /resources/playlisttracks*
 *
 * @author Kim Hammar on 2016-03-22.
 */
@RestController
@CrossOrigin
@RequestMapping("/resources/playlisttracks")
public class PlaylistTrackController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PlaylistTrackService playlistTrackService;

    /**
     * Method to be injected by the Spring container. Initializes the service.
     *
     * @param playlistTrackService service to handle playlisttrack-data.
     */
    @Inject
    public PlaylistTrackController(final PlaylistTrackService playlistTrackService) {
        this.playlistTrackService = playlistTrackService;
    }

    /**
     * Method to handle HTTP GET-requests for /resources/playlisttracks
     *
     * @return HTTP-response, JSON array of playlisttracks
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PlaylistTracksArrayRepresentation> getAllPlaylistTracks(@RequestParam(name = "trackId", required = false) String trackId,
                                                                              @RequestParam(name = "playlistId", required = false) String playlistId,
                                                                              @RequestParam(name = "sort", required = false) String sort)
            throws SortException {
        log.debug("HTTP GET-request /resources/playlisttracks");
        PlaylistTrackFilter playlistTrackFilter = new PlaylistTrackFilter();
        playlistTrackFilter.setTrackId(trackId);
        playlistTrackFilter.setPlaylistId(playlistId);
        playlistTrackFilter.setSort(sort);
        List<PlaylistTrackRepresentation> playlistTrackRepresentations = new ArrayList();
        List<PlaylistTrack> playlistTracks = playlistTrackService.getAllPlaylistTracks(playlistTrackFilter);
        playlistTracks.forEach((playlistTrack) -> {
            PlaylistTrackRepresentation playlistTrackRepresentation = new PlaylistTrackRepresentation(playlistTrack);
            playlistTrackRepresentation.add(linkTo(methodOn(PlaylistTrackController.class).getPlaylistTrack(playlistTrack.
                    getTrackId(), playlistTrack.getPlaylistId())).withSelfRel());
            playlistTrackRepresentation.add(linkTo(methodOn(PlaylistController.class).
                    getPlaylist(playlistTrackRepresentation.getPlaylistTrack().getPlaylistId())).withRel("playlist"));
            playlistTrackRepresentation.add(linkTo(methodOn(TrackController.class).getTrack(playlistTrackRepresentation.
                    getPlaylistTrack().getTrackId())).withRel("track"));
            playlistTrackRepresentations.add(playlistTrackRepresentation);
        });
        PlaylistTracksArrayRepresentation arrayRepresentation = new PlaylistTracksArrayRepresentation(playlistTrackRepresentations);
        return new ResponseEntity<PlaylistTracksArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP GET-requests for /resources/playlisttrakcs/{trackId}/{playlistId}
     *
     * @param trackId    id of the track
     * @param playlistId id of the playlist
     * @return HTTP-response, JSON-representation of the playlisttrack
     */
    @CrossOrigin
    @RequestMapping(value = "/{trackId}/{playlistId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PlaylistTrackRepresentation> getPlaylistTrack(@PathVariable int trackId, @PathVariable int playlistId) {
        log.debug("HTTP GET-request /resources/playlisttracks/{}", trackId);
        PlaylistTrackRepresentation playlistTrackRepresentation = new PlaylistTrackRepresentation(playlistTrackService.
                getPlaylistTrack(trackId, playlistId));
        playlistTrackRepresentation.add(linkTo(methodOn(PlaylistTrackController.class).getPlaylistTrack(trackId, playlistId)).withSelfRel());
        playlistTrackRepresentation.add(linkTo(methodOn(PlaylistController.class).getPlaylist(playlistTrackRepresentation.getPlaylistTrack()
                .getPlaylistId())).withRel("playlist"));
        playlistTrackRepresentation.add(linkTo(methodOn(TrackController.class).getTrack(playlistTrackRepresentation.getPlaylistTrack()
                .getTrackId())).withRel("track"));
        return new ResponseEntity<PlaylistTrackRepresentation>(playlistTrackRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP POST-requests for /resources/playlistTracks
     *
     * @param playlistTrack posted playlistTrack data
     * @return HTTP-response, JSON representation of the created playlistTrack
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PlaylistTrackRepresentation> createNewPlaylistTrack(@RequestBody PlaylistTrack playlistTrack) {
        log.debug("HTTP POST-request /resources/playlisttracks");
        PlaylistTrackRepresentation playlistTrackRepresentation = new PlaylistTrackRepresentation(playlistTrackService.createNewPlaylistTrack(playlistTrack));
        playlistTrackRepresentation.add(linkTo(methodOn(PlaylistTrackController.class).
                getPlaylistTrack(playlistTrack.getTrackId(), playlistTrack.getPlaylistId())).withSelfRel());
        playlistTrackRepresentation.add(linkTo(methodOn(PlaylistController.class).
                getPlaylist(playlistTrackRepresentation.getPlaylistTrack().getPlaylistId())).withRel("playlist"));
        playlistTrackRepresentation.add(linkTo(methodOn(TrackController.class).getTrack(playlistTrackRepresentation.
                getPlaylistTrack().getTrackId())).withRel("track"));
        return new ResponseEntity<PlaylistTrackRepresentation>(playlistTrackRepresentation, HttpStatus.CREATED);
    }

    /**
     * Method to handle HTTP PUT-requests for /resources/playlistTracks/{playlistTrackId}
     *
     * @param trackId       id of the track
     * @param playlistId    id of the playlist
     * @param playlistTrack put playlistTrack data
     * @return HTTP-Response, JSON representation of the updated playlistTrack representation
     */
    @CrossOrigin
    @RequestMapping(value = "/{trackId}/{playlistId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PlaylistTrackRepresentation> updatePlaylistTrack(@PathVariable int trackId, @PathVariable int playlistId, @RequestBody PlaylistTrack playlistTrack) {
        log.debug("HTTP PUT-request /resources/playlisttracks/{}/{}", trackId, playlistId);
        PlaylistTrackRepresentation playlistTrackRepresentation = new PlaylistTrackRepresentation(playlistTrackService.updatePlaylistTrack(playlistTrack));
        playlistTrackRepresentation.add(linkTo(methodOn(PlaylistTrackController.class).getPlaylistTrack(trackId, playlistId)).withSelfRel());
        playlistTrackRepresentation.add(linkTo(methodOn(PlaylistController.class).
                getPlaylist(playlistTrackRepresentation.getPlaylistTrack().getPlaylistId())).withRel("playlist"));
        playlistTrackRepresentation.add(linkTo(methodOn(TrackController.class).getTrack(playlistTrackRepresentation.
                getPlaylistTrack().getTrackId())).withRel("track"));
        return new ResponseEntity<PlaylistTrackRepresentation>(playlistTrackRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle PUT-requests for /resources/playlistTracks
     *
     * @param putPlaylistTracks
     * @return HTTP-Response, JSON representation of the updated playlistTracks resource
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PlaylistTracksArrayRepresentation> updatePlaylistTracks(@RequestBody List<PlaylistTrack> putPlaylistTracks) {
        log.debug("HTTP PUT-request /resources/playlisttracks");
        List<PlaylistTrackRepresentation> playlistTrackRepresentations = new ArrayList();
        List<PlaylistTrack> playlistTracks = playlistTrackService.updatePlaylistTracks(putPlaylistTracks);
        playlistTracks.forEach((playlistTrack) -> {
            PlaylistTrackRepresentation playlistTrackRepresentation = new PlaylistTrackRepresentation(playlistTrack);
            playlistTrackRepresentation.add(linkTo(methodOn(PlaylistTrackController.class).getPlaylistTrack(playlistTrack.getTrackId(), playlistTrack.getPlaylistId())).withSelfRel());
            playlistTrackRepresentation.add(linkTo(methodOn(PlaylistController.class).
                    getPlaylist(playlistTrackRepresentation.getPlaylistTrack().getPlaylistId())).withRel("playlist"));
            playlistTrackRepresentation.add(linkTo(methodOn(TrackController.class).getTrack(playlistTrackRepresentation.
                    getPlaylistTrack().getTrackId())).withRel("track"));
            playlistTrackRepresentations.add(playlistTrackRepresentation);
        });
        PlaylistTracksArrayRepresentation arrayRepresentation = new PlaylistTracksArrayRepresentation(playlistTrackRepresentations);
        return new ResponseEntity<PlaylistTracksArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP DELETE-requests for /resources/playlistTracks/{playlistTrackId}
     *
     * @param trackId    id of the track
     * @param playlistId id of the playlist
     * @return HTTP-Response, JSON representation of the deleted playlistTrack.
     */
    @CrossOrigin
    @RequestMapping(value = "/{playlistTrackId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PlaylistTrackRepresentation> deletePlaylistTrack(@PathVariable int trackId, @PathVariable int playlistId) {
        log.debug("HTTP DELETE-request /resources/playlisttracks/{}/{}", trackId, playlistId);
        PlaylistTrackRepresentation playlistTrackRepresentation = new PlaylistTrackRepresentation(playlistTrackService.deletePlaylistTrack(trackId, playlistId));
        playlistTrackRepresentation.add(linkTo(methodOn(PlaylistTrackController.class).getPlaylistTrack(trackId, playlistId)).withSelfRel());
        playlistTrackRepresentation.add(linkTo(methodOn(PlaylistController.class).
                getPlaylist(playlistTrackRepresentation.getPlaylistTrack().getPlaylistId())).withRel("playlist"));
        playlistTrackRepresentation.add(linkTo(methodOn(TrackController.class).getTrack(playlistTrackRepresentation.
                getPlaylistTrack().getTrackId())).withRel("track"));
        return new ResponseEntity<PlaylistTrackRepresentation>(playlistTrackRepresentation, HttpStatus.OK);
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
