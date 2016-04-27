package limmen.business.services.implementations;

import limmen.business.services.PlaylistService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.PlaylistFilter;
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
    public List<Playlist> getAllPlaylists(PlaylistFilter playlistFilter) throws SortException {
        List<Playlist> playlists = getAllPlaylists();
        playlists = playlistFilter.filter(playlists);
        try {
            return playlistFilter.sort(playlists);
        } catch (Exception e) {
            throw new SortException("Invalid query string for sorting: " + playlistFilter.getSort());
        }
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.getAllPlaylists();
    }

    @Override
    public Playlist getPlaylist(int playlistId) {
        return playlistRepository.getPlaylist(playlistId);
    }

    @Override
    public Playlist createNewPlaylist(Playlist playlist) {
        playlist.setPlaylistId(playlistRepository.getMaxId() + 1);
        return playlistRepository.createNewPlaylist(playlist);
    }

    @Override
    public Playlist updatePlaylist(Playlist playlist) {
        return playlistRepository.updatePlaylist(playlist);
    }

    @Override
    public List<Playlist> updatePlaylists(List<Playlist> playlists) {
        playlistRepository.deletePlaylists();
        playlists.forEach((playlist) -> {
            createNewPlaylist(playlist);
        });
        return getAllPlaylists();
    }

    @Override
    public Playlist deletePlaylist(int playlistId) {
        Playlist playlist = getPlaylist(playlistId);
        playlistRepository.deletePlaylist(playlistId);
        return playlist;
    }
}
