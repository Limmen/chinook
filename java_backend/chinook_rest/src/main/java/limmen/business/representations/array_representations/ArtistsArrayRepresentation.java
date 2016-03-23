package limmen.business.representations.array_representations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.business.representations.entity_representation.ArtistRepresentation;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class ArtistsArrayRepresentation extends ResourceSupport {
    private final List<ArtistRepresentation> artists;

    @JsonCreator
    public ArtistsArrayRepresentation(@JsonProperty("artists") List<ArtistRepresentation> artists) {
        this.artists = artists;
    }

    public List<ArtistRepresentation> getArtists() {
        return artists;
    }
}
