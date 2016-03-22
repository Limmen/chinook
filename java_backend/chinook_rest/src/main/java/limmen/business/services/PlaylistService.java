package limmen.business.services;

import limmen.integration.entities.Playlist;

import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public interface PlaylistService {

    public List<Playlist> getAllPlaylists();
    public Playlist getPlaylist(int PlaylistId);
}
