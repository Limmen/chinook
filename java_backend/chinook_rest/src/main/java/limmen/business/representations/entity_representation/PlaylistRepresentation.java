package limmen.business.representations.entity_representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.integration.entities.Playlist;
import org.springframework.hateoas.ResourceSupport;

/**
 * JSON-Representation of a Playlist entity.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class PlaylistRepresentation extends ResourceSupport {
    private final Playlist playlist;

    /**
     * Class constructor. Initializes the playlist property of the JSON representation.
     *
     * @param playlist value of the playlist property.
     */
    @JsonCreator
    public PlaylistRepresentation(@JsonProperty("playlist") Playlist playlist) {
        this.playlist = playlist;
    }

    public Playlist getPlaylist() {
        return playlist;
    }
}
