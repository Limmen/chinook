package limmen.business.representations.entity_representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.integration.entities.Track;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class TrackRepresentation extends ResourceSupport {
    private final Track track;

    @JsonCreator
    public TrackRepresentation(@JsonProperty("track") Track track) {
        this.track = track;
    }

    public Track getTrack() {
        return track;
    }
}
