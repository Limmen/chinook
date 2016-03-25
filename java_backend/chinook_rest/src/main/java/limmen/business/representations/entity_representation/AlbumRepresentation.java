package limmen.business.representations.entity_representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.integration.entities.Album;
import org.springframework.hateoas.ResourceSupport;

/**
 * JSON-Representation of a Album entity.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class AlbumRepresentation extends ResourceSupport {
    private final Album album;

    /**
     * Class constructor. Sets the "album" property of the JSON representation.
     *
     * @param album value of the album property.
     */
    @JsonCreator
    public AlbumRepresentation(@JsonProperty("album") Album album) {
        this.album = album;
    }

    public Album getAlbum() {
        return album;
    }
}
