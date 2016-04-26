package limmen.business.services;

import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.PlaylistFilter;
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
     * Method to get all playlists (filtered).
     *
     * @param playlistFilter properties to filter the list of playlists on
     * @return list of filtered playlists
     */
    public List<Playlist> getAllPlaylists(PlaylistFilter playlistFilter) throws SortException;

    /**
     * Method to get a playlist with a specified id.
     *
     * @param playlistId id of the playlist.
     * @return Playlist with the specified id.
     */
    public Playlist getPlaylist(int playlistId);

    /**
     * Method to create a new playlist.
     *
     * @param playlist data of the playlist to create
     * @return the created  playlist
     */
    public Playlist createNewPlaylist(Playlist playlist);

    /**
     * Method to update a playlist.
     *
     * @param playlist playlist to update
     * @return updated playlist
     */
    public Playlist updatePlaylist(Playlist playlist);

    /**
     * Method to update the list of playlists
     *
     * @param playlists data to update playlists list with
     * @return new list of playlists
     */
    public List<Playlist> updatePlaylists(List<Playlist> playlists);

    /**
     * Method to delete a playlist.
     *
     * @param playlistId id of the playlist to delete
     * @return the deleted playlist.
     */
    public Playlist deletePlaylist(int playlistId);
}
