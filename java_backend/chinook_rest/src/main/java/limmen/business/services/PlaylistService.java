package limmen.business.services;

import limmen.integration.entities.Playlist;

import java.util.List;

/**
 * Interface for a service that provides CRUD services for playlist-data.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public interface PlaylistService {

    /**
     * Method to get all playlists.
     *
     * @return list of playlists.
     */
    public List<Playlist> getAllPlaylists();

    /**
     * Method to get a playlist with a specified id.
     *
     * @param playlistId id of the playlist.
     * @return Playlist with the specified id.
     */
    public Playlist getPlaylist(int playlistId);
}
