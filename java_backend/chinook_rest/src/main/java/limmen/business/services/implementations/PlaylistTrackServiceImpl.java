package limmen.business.services.implementations;

import limmen.business.services.PlaylistTrackService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.PlaylistTrackFilter;
import limmen.integration.entities.PlaylistTrack;
import limmen.integration.repositories.PlaylistTrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the PlaylistTrackService interface, uses a repository for database interaction.
 *
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
    public List<PlaylistTrack> getAllPlaylistTracks(PlaylistTrackFilter playlistTrackFilter) throws SortException {
        List<PlaylistTrack> playlistTracks = getAllPlaylistTracks();
        playlistTracks = playlistTrackFilter.filter(playlistTracks);
        try {
            return playlistTrackFilter.sort(playlistTracks);
        } catch (Exception e) {
            throw new SortException("Invalid query string for sorting: " + playlistTrackFilter.getSort());
        }
    }

    @Override
    public List<PlaylistTrack> getAllPlaylistTracks() {
        return playlistTrackRepository.getAllPlaylistTracks();
    }

    @Override
    public PlaylistTrack getPlaylistTrack(int trackId, int playlistId) {
        return playlistTrackRepository.getPlaylistTrack(trackId, playlistId);
    }

    @Override
    public PlaylistTrack createNewPlaylistTrack(PlaylistTrack playlistTrack) {
        return playlistTrackRepository.createNewPlaylistTrack(playlistTrack);
    }

    @Override
    public PlaylistTrack updatePlaylistTrack(PlaylistTrack playlistTrack) {
        return playlistTrackRepository.updatePlaylistTrack(playlistTrack);
    }

    @Override
    public List<PlaylistTrack> updatePlaylistTracks(List<PlaylistTrack> playlistTracks) {
        playlistTrackRepository.deletePlaylistTracks();
        playlistTracks.forEach((playlistTrack) -> {
            createNewPlaylistTrack(playlistTrack);
        });
        return getAllPlaylistTracks();
    }

    @Override
    public PlaylistTrack deletePlaylistTrack(int trackId, int playlistId) {
        PlaylistTrack playlistTrack = getPlaylistTrack(trackId, playlistId);
        playlistTrackRepository.deletePlaylistTrack(trackId, playlistId);
        return playlistTrack;
    }
}
