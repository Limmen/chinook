package limmen.business.representations.array_representations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.business.representations.entity_representation.PlaylistRepresentation;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * JSON-Representation of a Array of playlists.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class PlaylistsArrayRepresentation extends ResourceSupport {
    private final List<PlaylistRepresentation> playlists;

    /**
     * Class constructor. Initializes the playlists property of the JSON representation.
     *
     * @param playlists value of the playlists property
     */
    @JsonCreator
    public PlaylistsArrayRepresentation(@JsonProperty("playlists") List<PlaylistRepresentation> playlists) {
        this.playlists = playlists;
    }

    public List<PlaylistRepresentation> getPlaylists() {
        return playlists;
    }
}
