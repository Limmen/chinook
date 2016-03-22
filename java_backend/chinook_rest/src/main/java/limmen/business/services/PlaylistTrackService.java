package limmen.business.services;

import limmen.integration.entities.PlaylistTrack;

import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public interface PlaylistTrackService {

    public List<PlaylistTrack> getAllPlaylistTracks();
    public PlaylistTrack getPlaylistTrack(int playlistTrackId, int playlistId);
}
