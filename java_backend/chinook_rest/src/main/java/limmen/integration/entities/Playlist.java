package limmen.integration.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class Playlist {
    @NotNull
    private int playlistId;
    @NotNull
    @Size(max = 120)
    private String name;

    public Playlist(int playlistId, String name) {
        this.playlistId = playlistId;
        this.name = name;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public String getName() {
        return name;
    }
}
