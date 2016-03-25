package limmen.integration.entities;

import javax.validation.constraints.NotNull;

/**
 * POJO that represents a PlaylistTrack entity from the chinook database
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class PlaylistTrack {
    @NotNull
    private int trackId;
    @NotNull
    private int playlistId;

    /**
     * Class constructor. Initializes an immutable entity class.
     *
     * @param trackId id of the track.
     * @param playlistId id of the playlist.
     */
    public PlaylistTrack(int trackId, int playlistId) {
        this.trackId = trackId;
        this.playlistId = playlistId;
    }

    public PlaylistTrack(){}

    public int getTrackId() {
        return trackId;
    }

    public int getPlaylistId() {
        return playlistId;
    }
}
