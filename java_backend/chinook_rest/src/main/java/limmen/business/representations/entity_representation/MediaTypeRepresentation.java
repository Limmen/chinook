package limmen.business.representations.entity_representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.integration.entities.MediaTypeEntity;
import org.springframework.hateoas.ResourceSupport;

/**
 * JSON-Representation of a MediaType entity.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class MediaTypeRepresentation extends ResourceSupport {
    private final MediaTypeEntity mediaTypeEntity;

    /**
     * Class constructor. Initializes the mediatype property of the json representation.
     *
     * @param mediaTypeEntity value of the mediatype property
     */
    @JsonCreator
    public MediaTypeRepresentation(@JsonProperty("mediatype") MediaTypeEntity mediaTypeEntity) {
        this.mediaTypeEntity = mediaTypeEntity;
    }

    public MediaTypeEntity getMediaTypeEntity() {
        return mediaTypeEntity;
    }
}
