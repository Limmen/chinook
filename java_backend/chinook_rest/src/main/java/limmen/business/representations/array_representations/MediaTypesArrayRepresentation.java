package limmen.business.representations.array_representations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.business.representations.entity_representation.MediaTypeRepresentation;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class MediaTypesArrayRepresentation extends ResourceSupport {
    private final List<MediaTypeRepresentation> mediaTypes;

    @JsonCreator
    public MediaTypesArrayRepresentation(@JsonProperty("mediaTypes") List<MediaTypeRepresentation> mediaTypes) {
        this.mediaTypes = mediaTypes;
    }

    public List<MediaTypeRepresentation> getMediaTypes() {
        return mediaTypes;
    }
}
