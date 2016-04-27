package limmen.controllers;

import limmen.business.representations.array_representations.PlaylistsArrayRepresentation;
import limmen.business.representations.entity_representation.PlaylistRepresentation;
import limmen.business.services.PlaylistService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.PlaylistFilter;
import limmen.integration.entities.Playlist;
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
 * RestController for the resource pointed by the url: /resources/playlistst*
 *
 * @author Kim Hammar on 2016-03-22.
 */
@RestController
@CrossOrigin
@RequestMapping("/resources/playlists")
public class PlaylistController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PlaylistService playlistService;

    /**
     * Method to be injected by the Spring container. Initializes the service.
     *
     * @param playlistService service to handle playlist-data
     */
    @Inject
    public PlaylistController(final PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    /**
     * Method to handle HTTP GET-requests for /resources/playlists
     *
     * @return HTTP-response, JSON array of playlists
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PlaylistsArrayRepresentation> getAllPlaylists(@RequestParam(name = "playlistId", required = false) String playlistId,
                                                                    @RequestParam(name = "name", required = false) String name,
                                                                    @RequestParam(name = "sort", required = false) String sort)
            throws SortException {
        log.debug("HTTP GET-request /resources/playlists");
        PlaylistFilter playlistFilter = new PlaylistFilter();
        playlistFilter.setPlaylistId(playlistId);
        playlistFilter.setName(name);
        playlistFilter.setSort(sort);
        List<PlaylistRepresentation> playlistRepresentations = new ArrayList();
        List<Playlist> playlists = playlistService.getAllPlaylists(playlistFilter);
        playlists.forEach((playlist) -> {
            PlaylistRepresentation playlistRepresentation = new PlaylistRepresentation(playlist);
            playlistRepresentation.add(linkTo(methodOn(PlaylistController.class).getPlaylist(playlist.getPlaylistId())).withSelfRel());
            playlistRepresentations.add(playlistRepresentation);
        });
        PlaylistsArrayRepresentation arrayRepresentation = new PlaylistsArrayRepresentation(playlistRepresentations);
        return new ResponseEntity<PlaylistsArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP GET-requests for /resources/playlists/{playlistId}
     *
     * @param playlistId id of the playlist
     * @return HTTP-response, JSON-representation of the playlist
     */
    @CrossOrigin
    @RequestMapping(value = "/{playlistId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PlaylistRepresentation> getPlaylist(@PathVariable int playlistId) {
        log.debug("HTTP GET-request /resources/playlists/{}", playlistId);
        PlaylistRepresentation playlistRepresentation = new PlaylistRepresentation(playlistService.getPlaylist(playlistId));
        playlistRepresentation.add(linkTo(methodOn(PlaylistController.class).getPlaylist(playlistId)).withSelfRel());
        return new ResponseEntity<PlaylistRepresentation>(playlistRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP POST-requests for /resources/playlists
     *
     * @param playlist posted playlist data
     * @return HTTP-response, JSON representation of the created playlist
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PlaylistRepresentation> createNewPlaylist(@RequestBody Playlist playlist) {
        log.debug("HTTP POST-request /resources/playlists");
        PlaylistRepresentation playlistRepresentation = new PlaylistRepresentation(playlistService.createNewPlaylist(playlist));
        playlistRepresentation.add(linkTo(methodOn(PlaylistController.class).getPlaylist(playlist.getPlaylistId())).withSelfRel());
        return new ResponseEntity<PlaylistRepresentation>(playlistRepresentation, HttpStatus.CREATED);
    }

    /**
     * Method to handle HTTP PUT-requests for /resources/playlists/{playlistId}
     *
     * @param playlistId id of the playlist
     * @param playlist   put playlist data
     * @return HTTP-Response, JSON representation of the updated playlist representation
     */
    @CrossOrigin
    @RequestMapping(value = "/{playlistId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PlaylistRepresentation> updatePlaylist(@PathVariable int playlistId, @RequestBody Playlist playlist) {
        log.debug("HTTP PUT-request /resources/playlists/{}", playlistId);
        playlist.setPlaylistId(playlistId);
        PlaylistRepresentation playlistRepresentation = new PlaylistRepresentation(playlistService.updatePlaylist(playlist));
        playlistRepresentation.add(linkTo(methodOn(PlaylistController.class).getPlaylist(playlist.getPlaylistId())).withSelfRel());
        return new ResponseEntity<PlaylistRepresentation>(playlistRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle PUT-requests for /resources/playlists
     *
     * @param putPlaylists
     * @return HTTP-Response, JSON representation of the updated playlists resource
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PlaylistsArrayRepresentation> updatePlaylists(@RequestBody List<Playlist> putPlaylists) {
        log.debug("HTTP PUT-request /resources/playlists");
        List<PlaylistRepresentation> playlistRepresentations = new ArrayList();
        List<Playlist> playlists = playlistService.updatePlaylists(putPlaylists);
        playlists.forEach((playlist) -> {
            PlaylistRepresentation playlistRepresentation = new PlaylistRepresentation(playlist);
            playlistRepresentation.add(linkTo(methodOn(PlaylistController.class).getPlaylist(playlist.getPlaylistId())).withSelfRel());
            playlistRepresentations.add(playlistRepresentation);
        });
        PlaylistsArrayRepresentation arrayRepresentation = new PlaylistsArrayRepresentation(playlistRepresentations);
        return new ResponseEntity<PlaylistsArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP DELETE-requests for /resources/playlists/{playlistId}
     *
     * @param playlistId id of the playlist
     * @return HTTP-Response, JSON representation of the deleted playlist.
     */
    @CrossOrigin
    @RequestMapping(value = "/{playlistId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PlaylistRepresentation> deletePlaylist(@PathVariable int playlistId) {
        log.debug("HTTP DELETE-request /resources/playlists/{}", playlistId);
        PlaylistRepresentation playlistRepresentation = new PlaylistRepresentation(playlistService.deletePlaylist(playlistId));
        playlistRepresentation.add(linkTo(methodOn(PlaylistController.class).getPlaylist(playlistId)).withSelfRel());
        return new ResponseEntity<PlaylistRepresentation>(playlistRepresentation, HttpStatus.OK);
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
