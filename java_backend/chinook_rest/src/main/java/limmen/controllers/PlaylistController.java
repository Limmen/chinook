package limmen.controllers;

import limmen.business.representations.entity_representation.PlaylistRepresentation;
import limmen.business.representations.array_representations.PlaylistsArrayRepresentation;
import limmen.business.services.PlaylistService;
import limmen.integration.entities.Playlist;
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
     * Method to handle HTTP-requests for /resources/playlists
     *
     * @return HTTP-response, JSON array of playlists
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PlaylistsArrayRepresentation> getAllPlaylists() {
        log.debug("HTTP GET-request /resources/playlists");
        List<PlaylistRepresentation> playlistRepresentations = new ArrayList();
        List<Playlist> playlists = playlistService.getAllPlaylists();
        playlists.forEach((playlist) -> {
            PlaylistRepresentation playlistRepresentation = new PlaylistRepresentation(playlist);
            playlistRepresentation.add(linkTo(methodOn(PlaylistController.class).getPlaylist(playlist.getPlaylistId())).withSelfRel());
            playlistRepresentations.add(playlistRepresentation);
        });
        PlaylistsArrayRepresentation arrayRepresentation = new PlaylistsArrayRepresentation(playlistRepresentations);
        return new ResponseEntity<PlaylistsArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP-requests for /resources/playlists/{playlistId}
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
}
