package limmen.business.representations.array_representations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.business.representations.entity_representation.AlbumRepresentation;
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
