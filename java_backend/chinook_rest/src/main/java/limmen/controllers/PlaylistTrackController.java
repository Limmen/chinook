package limmen.controllers;

import limmen.business.representations.entity_representation.PlaylistTrackRepresentation;
import limmen.business.representations.array_representations.PlaylistTracksArrayRepresentation;
import limmen.business.services.PlaylistTrackService;
import limmen.integration.entities.PlaylistTrack;
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
     * Method to handle HTTP-requests for /resources/playlisttracks
     *
     * @return HTTP-response, JSON array of playlisttracks
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PlaylistTracksArrayRepresentation> getAllPlaylistTracks() {
        log.debug("HTTP GET-request /resources/playlisttracks");
        List<PlaylistTrackRepresentation> playlistTrackRepresentations = new ArrayList();
        List<PlaylistTrack> playlistTracks = playlistTrackService.getAllPlaylistTracks();
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
     * Method to handle HTTP-requests for /resources/playlisttrakcs/{trackId}/{playlistId}
     *
     * @param trackId id of the track
     * @param playlistId id of the playlist
     * @return HTTP-response, JSON-representation of the playlisttrack
     */
    @CrossOrigin
    @RequestMapping(value = "/{trackId}/{playlistId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PlaylistTrackRepresentation> getPlaylistTrack(@PathVariable int trackId, @PathVariable int playlistId) {
        log.debug("HTTP GET-request /resources/playlistTracks/{}", trackId);
        PlaylistTrackRepresentation playlistTrackRepresentation = new PlaylistTrackRepresentation(playlistTrackService.
                getPlaylistTrack(trackId, playlistId));
        playlistTrackRepresentation.add(linkTo(methodOn(PlaylistTrackController.class).getPlaylistTrack(trackId, playlistId)).withSelfRel());
        playlistTrackRepresentation.add(linkTo(methodOn(PlaylistController.class).getPlaylist(playlistTrackRepresentation.getPlaylistTrack()
                .getPlaylistId())).withRel("playlist"));
        playlistTrackRepresentation.add(linkTo(methodOn(TrackController.class).getTrack(playlistTrackRepresentation.getPlaylistTrack()
                .getTrackId())).withRel("track"));
        return new ResponseEntity<PlaylistTrackRepresentation>(playlistTrackRepresentation, HttpStatus.OK);
    }
}
