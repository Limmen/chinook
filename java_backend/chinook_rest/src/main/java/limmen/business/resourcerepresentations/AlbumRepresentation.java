package limmen.business.resourcerepresentations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.integration.entities.Album;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class AlbumRepresentation extends ResourceSupport {
    private final Album album;

    @JsonCreator
    public AlbumRepresentation(@JsonProperty("album") Album album) {
        this.album = album;
    }

    public Album getAlbum() {
        return album;
    }
}
