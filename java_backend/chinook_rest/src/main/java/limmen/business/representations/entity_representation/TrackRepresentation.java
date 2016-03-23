package limmen.business.representations.entity_representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.integration.entities.Artist;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class TrackRepresentation extends ResourceSupport {
    private final Artist artist;

    @JsonCreator
    public TrackRepresentation(@JsonProperty("artist") Artist artist) {
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }
}
