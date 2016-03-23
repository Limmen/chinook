package limmen.business.representations.array_representations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.business.representations.entity_representation.PlaylistRepresentation;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class PlaylistsArrayRepresentation extends ResourceSupport {
    private final List<PlaylistRepresentation> playlists;

    @JsonCreator
    public PlaylistsArrayRepresentation(@JsonProperty("playlists") List<PlaylistRepresentation> playlists) {
        this.playlists = playlists;
    }

    public List<PlaylistRepresentation> getPlaylists() {
        return playlists;
    }
}
