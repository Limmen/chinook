package limmen.business.representations.entity_representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.integration.entities.PlaylistTrack;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class PlaylistTrackRepresentation extends ResourceSupport {
    private final PlaylistTrack playlistTrack;

    @JsonCreator
    public PlaylistTrackRepresentation(@JsonProperty("playlisttrack") PlaylistTrack playlistTrack) {
        this.playlistTrack = playlistTrack;
    }

    public PlaylistTrack getPlaylistTrack() {
        return playlistTrack;
    }
}
