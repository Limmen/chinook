package limmen.business.services.implementations;

import limmen.business.services.TrackService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.TrackFilter;
import limmen.integration.entities.Track;
import limmen.integration.repositories.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the TrackService interface, uses a repository for database interaction.
 *
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
    public List<Track> getAllTracks(TrackFilter trackFilter) throws SortException {
        List<Track> tracks = getAllTracks();
        tracks = trackFilter.filter(tracks);
        try {
            return trackFilter.sort(tracks);
        } catch (Exception e) {
            throw new SortException("Invalid query string for sorting: " + trackFilter.getSort());
        }
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.getAllTracks();
    }

    @Override
    public Track getTrack(int trackId) {
        return trackRepository.getTrack(trackId);
    }

    @Override
    public Track createNewTrack(Track track) {
        track.setTrackId(trackRepository.getMaxId() + 1);
        return trackRepository.createNewTrack(track);
    }

    @Override
    public Track updateTrack(Track track) {
        return trackRepository.updateTrack(track);
    }

    @Override
    public List<Track> updateTracks(List<Track> tracks) {
        trackRepository.deleteTracks();
        tracks.forEach((track) -> {
            createNewTrack(track);
        });
        return getAllTracks();
    }

    @Override
    public Track deleteTrack(int trackId) {
        Track track = getTrack(trackId);
        trackRepository.deleteTrack(trackId);
        return track;
    }
}
