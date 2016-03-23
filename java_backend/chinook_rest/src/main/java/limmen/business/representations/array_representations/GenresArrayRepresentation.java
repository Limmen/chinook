package limmen.business.representations.array_representations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.business.representations.entity_representation.GenreRepresentation;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class GenresArrayRepresentation extends ResourceSupport {
    private final List<GenreRepresentation> genres;

    @JsonCreator
    public GenresArrayRepresentation(@JsonProperty("genres") List<GenreRepresentation> genres) {
        this.genres = genres;
    }

    public List<GenreRepresentation> getGenres() {
        return genres;
    }
}
