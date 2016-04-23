package limmen.integration.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * POJO that represents a Playlist entity from the chinook database
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class Playlist {
    @NotNull
    private int playlistId;
    @NotNull
    @Size(max = 120)
    private String name;

    /**
     * Class constructor
     *
     * @param playlistId id of the playlist, unique.
     * @param name name of the playlist.
     */
    public Playlist(int playlistId, String name) {
        this.playlistId = playlistId;
        this.name = name;
    }

    public Playlist(){}

    public int getPlaylistId() {
        return playlistId;
    }

    public String getName() {
        return name;
    }
}
