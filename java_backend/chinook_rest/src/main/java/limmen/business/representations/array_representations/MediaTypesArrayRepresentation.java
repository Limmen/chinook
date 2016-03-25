package limmen.business.representations.array_representations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.business.representations.entity_representation.MediaTypeRepresentation;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * JSON-Representation of a Array of mediatypes.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class MediaTypesArrayRepresentation extends ResourceSupport {
    private final List<MediaTypeRepresentation> mediaTypes;

    /**
     * Class constructor. Initializes the mediatypes property of the JSON representation.
     *
     * @param mediaTypes value of the mediatypes property
     */
    @JsonCreator
    public MediaTypesArrayRepresentation(@JsonProperty("mediatypes") List<MediaTypeRepresentation> mediaTypes) {
        this.mediaTypes = mediaTypes;
    }

    public List<MediaTypeRepresentation> getMediaTypes() {
        return mediaTypes;
    }
}
