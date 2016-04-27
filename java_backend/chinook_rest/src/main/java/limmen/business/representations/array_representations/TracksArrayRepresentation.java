package limmen.business.representations.array_representations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import limmen.business.representations.entity_representation.TrackRepresentation;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * JSON-Representation of a Array of tracks.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class TracksArrayRepresentation extends ResourceSupport {
    private final List<TrackRepresentation> tracks;

    /**
     * Class constructor. Initializes the tracks property of the JSON representation.
     *
     * @param tracks value of the tracks property.
     */
    @JsonCreator
    public TracksArrayRepresentation(@JsonProperty("tracks") List<TrackRepresentation> tracks) {
        this.tracks = tracks;
    }

    public List<TrackRepresentation> getTracks() {
        return tracks;
    }
}
