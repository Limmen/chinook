package limmen.business.resourcerepresentations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**@author Kim Hammar on 2016-03-22.
 */
public class PlaylistTracksArrayRepresentation extends ResourceSupport {
    private final List<PlaylistTrackRepresentation> playlistTracks;

    @JsonCreator
    public PlaylistTracksArrayRepresentation(@JsonProperty("playlistTracks") List<PlaylistTrackRepresentation> playlistTracks) {
        this.playlistTracks = playlistTracks;
    }

    public List<PlaylistTrackRepresentation> getPlaylistTracks() {
        return playlistTracks;
    }
}
