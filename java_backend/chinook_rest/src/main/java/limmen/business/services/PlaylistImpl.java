package limmen.business.services;

import limmen.integration.entities.Playlist;
import limmen.integration.repositories.PlaylistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
@Service
public class PlaylistImpl implements PlaylistService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PlaylistRepository playlistRepository;

    @Inject
    public PlaylistImpl(final PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.getAllPlaylists();
    }

    @Override
    public Playlist getPlaylist(int playlistId) {
        return playlistRepository.getPlaylist(playlistId);
    }
}
