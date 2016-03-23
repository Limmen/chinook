package limmen.business.services.implementations;

import limmen.business.services.TrackService;
import limmen.integration.entities.Track;
import limmen.integration.repositories.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
@Service
public class TrackServiceImpl implements TrackService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final TrackRepository trackRepository;

    @Inject
    public TrackServiceImpl(final TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.getAllTracks();
    }

    @Override
    public Track getTrack(int trackId) {
        return trackRepository.getTrack(trackId);
    }
}
