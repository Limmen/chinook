package limmen.business.services;

import limmen.integration.entities.PlaylistTrack;
import limmen.integration.repositories.PlaylistTrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
@Service
public class PlaylistTrackServiceImpl implements PlaylistTrackService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PlaylistTrackRepository playlistTrackRepository;

    @Inject
    public PlaylistTrackServiceImpl(final PlaylistTrackRepository playlistTrackRepository) {
        this.playlistTrackRepository = playlistTrackRepository;
    }

    @Override
    public List<PlaylistTrack> getAllPlaylistTracks() {
        return playlistTrackRepository.getAllPlaylistTracks();
    }

    @Override
    public PlaylistTrack getPlaylistTrack(int trackId, int playlistId) {
        return playlistTrackRepository.getPlaylistTrack(trackId, playlistId);
    }
}
