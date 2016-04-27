package limmen.business.representations.entity_representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.integration.entities.Genre;
import org.springframework.hateoas.ResourceSupport;

/**
 * JSON-Representation of a Genre entity.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class GenreRepresentation extends ResourceSupport {
    private final Genre genre;

    /**
     * Class constructor. Initializes the genre property of the JSON representation.
     *
     * @param genre value of the genre property.
     */
    @JsonCreator
    public GenreRepresentation(@JsonProperty("genre") Genre genre) {
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }
}
