package limmen.integration.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class Album {

    @NotNull
    private int albumId;
    @NotNull
    @Size(max = 160)
    private String title;
    @NotNull
    private int artistId;

    public Album(int albumId, String title, int artistId){
        this.albumId = albumId;
        this.title = title;
        this.artistId = artistId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getTitle() {
        return title;
    }

    public int getArtistId() {
        return artistId;
    }
}
