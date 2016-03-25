package limmen.business.services.implementations;

import limmen.business.services.PlaylistService;
import limmen.integration.entities.Playlist;
import limmen.integration.repositories.PlaylistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the PlaylistService interface, uses a repository for database interaction.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Service
public class PlaylistServiceImpl implements PlaylistService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PlaylistRepository playlistRepository;

    @Inject
    public PlaylistServiceImpl(final PlaylistRepository playlistRepository) {
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
