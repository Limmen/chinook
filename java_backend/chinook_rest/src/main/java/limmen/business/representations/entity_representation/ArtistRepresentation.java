package limmen.business.representations.entity_representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.integration.entities.Artist;
import org.springframework.hateoas.ResourceSupport;

/**
 * JSON-Representation of a Artist entity.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class ArtistRepresentation extends ResourceSupport {
    @JsonProperty
    private Artist artist;

    /**
     * Class constructor. Initializes the "artist" property of the JSON representation.
     *
     * @param artist value of the artist property.
     */
    @JsonCreator
    public ArtistRepresentation(@JsonProperty("artist") Artist artist) {
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }

}
