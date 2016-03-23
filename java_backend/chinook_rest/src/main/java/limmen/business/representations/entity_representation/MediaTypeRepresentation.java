package limmen.business.representations.entity_representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.integration.entities.MediaTypeEntity;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class MediaTypeRepresentation extends ResourceSupport {
    private final MediaTypeEntity mediaTypeEntity;

    @JsonCreator
    public MediaTypeRepresentation(@JsonProperty("mediatype") MediaTypeEntity mediaTypeEntity) {
        this.mediaTypeEntity = mediaTypeEntity;
    }

    public MediaTypeEntity getMediaTypeEntity() {
        return mediaTypeEntity;
    }
}
