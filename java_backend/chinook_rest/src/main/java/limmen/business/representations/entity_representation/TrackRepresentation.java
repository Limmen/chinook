package limmen.business.representations.entity_representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.integration.entities.Track;
import org.springframework.hateoas.ResourceSupport;

/**
 * JSON-Representation of a Track entity.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class TrackRepresentation extends ResourceSupport {
    private final Track track;

    /**
     * Class constructor. Initializes the track property of the JSON representation.
     *
     * @param track value of the track property.
     */
    @JsonCreator
    public TrackRepresentation(@JsonProperty("track") Track track) {
        this.track = track;
    }

    public Track getTrack() {
        return track;
    }
}
