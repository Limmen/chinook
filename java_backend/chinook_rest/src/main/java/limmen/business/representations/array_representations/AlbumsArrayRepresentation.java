package limmen.business.representations.array_representations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.business.representations.entity_representation.AlbumRepresentation;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * JSON-Representation of a Array of albums.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class AlbumsArrayRepresentation extends ResourceSupport {
    private final List<AlbumRepresentation> albums;

    /**
     * Class constructor. Initializes the albums property of the JSON representation.
     *
     * @param albums value of the albums property.
     */
    @JsonCreator
    public AlbumsArrayRepresentation(@JsonProperty("albums") List<AlbumRepresentation> albums) {
        this.albums = albums;
    }

    public List<AlbumRepresentation> getAlbums() {
        return albums;
    }
}
