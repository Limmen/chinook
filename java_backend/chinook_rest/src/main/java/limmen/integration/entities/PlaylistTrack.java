package limmen.integration.entities;

import javax.validation.constraints.NotNull;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class PlaylistTrack {
    @NotNull
    private int trackId;
    @NotNull
    private int playlistId;

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
