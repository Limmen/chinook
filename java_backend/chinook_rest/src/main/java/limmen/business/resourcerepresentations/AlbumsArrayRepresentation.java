package limmen.business.resourcerepresentations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**@author Kim Hammar on 2016-03-22.
 */
public class AlbumsArrayRepresentation extends ResourceSupport {
    private final List<AlbumRepresentation> albums;

    @JsonCreator
    public AlbumsArrayRepresentation(@JsonProperty("albums") List<AlbumRepresentation> albums) {
        this.albums = albums;
    }

    public List<AlbumRepresentation> getAlbums() {
        return albums;
    }
}
