package limmen.business.representations.entity_representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.integration.entities.Genre;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class GenreRepresentation extends ResourceSupport {
    private final Genre genre;

    @JsonCreator
    public GenreRepresentation(@JsonProperty("genre") Genre genre) {
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }
}
