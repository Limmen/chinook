package limmen.controllers;

import limmen.business.resourcerepresentations.PlaylistTrackRepresentation;
import limmen.business.resourcerepresentations.PlaylistTracksArrayRepresentation;
import limmen.business.services.PlaylistTrackService;
import limmen.integration.entities.PlaylistTrack;
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
@RequestMapping("/resources/playlisttracks")
public class PlaylistTrackController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PlaylistTrackService playlistTrackService;

    @Inject
    public PlaylistTrackController(final PlaylistTrackService playlistTrackService) {
        this.playlistTrackService = playlistTrackService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PlaylistTracksArrayRepresentation> getAllPlaylistTracks() {
        log.debug("HTTP GET-request /resources/playlisttracks");
        List<PlaylistTrackRepresentation> playlistTrackRepresentations = new ArrayList();
        List<PlaylistTrack> playlistTracks = playlistTrackService.getAllPlaylistTracks();
        playlistTracks.forEach((playlistTrack) -> {
            PlaylistTrackRepresentation playlistTrackRepresentation = new PlaylistTrackRepresentation(playlistTrack);
            playlistTrackRepresentation.add(linkTo(methodOn(PlaylistTrackController.class).getPlaylistTrack(playlistTrack.getTrackId(), playlistTrack.getPlaylistId())).withSelfRel());
            playlistTrackRepresentation.add(linkTo(methodOn(PlaylistController.class).getPlaylist(playlistTrackRepresentation.getPlaylistTrack()
                    .getPlaylistId())).withRel("playlist"));
            playlistTrackRepresentations.add(playlistTrackRepresentation);
        });
        PlaylistTracksArrayRepresentation arrayRepresentation = new PlaylistTracksArrayRepresentation(playlistTrackRepresentations);
        return new ResponseEntity<PlaylistTracksArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    @RequestMapping(value = "/{playlistTrackId}/{playlistId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PlaylistTrackRepresentation> getPlaylistTrack(@PathVariable int playlistTrackId, @PathVariable int playlistId) {
        log.debug("HTTP GET-request /resources/playlistTracks/{}", playlistTrackId);
        PlaylistTrackRepresentation playlistTrackRepresentation = new PlaylistTrackRepresentation(playlistTrackService.
                getPlaylistTrack(playlistTrackId, playlistId));
        playlistTrackRepresentation.add(linkTo(methodOn(PlaylistTrackController.class).getPlaylistTrack(playlistTrackId, playlistId)).withSelfRel());
        playlistTrackRepresentation.add(linkTo(methodOn(PlaylistController.class).getPlaylist(playlistTrackRepresentation.getPlaylistTrack()
                .getPlaylistId())).withRel("playlist"));
        return new ResponseEntity<PlaylistTrackRepresentation>(playlistTrackRepresentation, HttpStatus.OK);
    }
}
