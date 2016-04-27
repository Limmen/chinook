package limmen.business.services;

import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.PlaylistTrackFilter;
import limmen.integration.entities.PlaylistTrack;

import java.util.List;

/**
 * Interface for a service that provides CRUD services for playlisttrack-data.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public interface PlaylistTrackService {

    /**
     * Method to get all playlisttracks
     *
     * @return list of playlisttyracks
     */
    public List<PlaylistTrack> getAllPlaylistTracks();

    /**
     * Method to get all playlistTracks (filtered).
     *
     * @param playlistTrackFilter properties to filter the list of playlistTracks on
     * @return list of filtered playlistTracks
     */
    public List<PlaylistTrack> getAllPlaylistTracks(PlaylistTrackFilter playlistTrackFilter) throws SortException;
    
    /**
     * Method to get a playlist with a specified trackid and playlistid
     *
     * @param trackId id of the track
     * @param playlistId id of the playlist
     * @return PlaylistId with the track and playlist id
     */
    public PlaylistTrack getPlaylistTrack(int trackId, int playlistId);

    /**
     * Method to create a new playlistTrack.
     *
     * @param playlistTrack data of the playlistTrack to create
     * @return the created  playlistTrack
     */
    public PlaylistTrack createNewPlaylistTrack(PlaylistTrack playlistTrack);

    /**
     * Method to update a playlistTrack.
     *
     * @param playlistTrack playlistTrack to update
     * @return updated playlistTrack
     */
    public PlaylistTrack updatePlaylistTrack(PlaylistTrack playlistTrack);

    /**
     * Method to update the list of playlistTracks
     *
     * @param playlistTracks data to update playlistTracks list with
     * @return new list of playlistTracks
     */
    public List<PlaylistTrack> updatePlaylistTracks(List<PlaylistTrack> playlistTracks);

    /**
     * Method to delete a playlistTrack.
     *
     * @param trackId id of the track
     * @param playlistId id of the playlist
     * @return the deleted playlistTrack.
     */
    public PlaylistTrack deletePlaylistTrack(int trackId, int playlistId);
}
