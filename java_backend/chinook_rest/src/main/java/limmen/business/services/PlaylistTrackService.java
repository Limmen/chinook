package limmen.business.services;

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
     * Method to get a playlist with a specified trackid and playlistid
     *
     * @param playlistTrackId id of the track
     * @param playlistId id of the playlist
     * @return PlaylistId with the track and playlist id
     */
    public PlaylistTrack getPlaylistTrack(int playlistTrackId, int playlistId);
}
