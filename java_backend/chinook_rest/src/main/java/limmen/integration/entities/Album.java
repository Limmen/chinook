package limmen.integration.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * POJO that represents a Album entity from the chinook database
 *
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

    public Album() {
    }

    /**
     * Class constructor. Initializes an entity class.
     *
     * @param albumId  id of the album, unique
     * @param title    title of the album
     * @param artistId id of the artists that produced the album
     */
    public Album(int albumId, String title, int artistId) {
        this.albumId = albumId;
        this.title = title;
        this.artistId = artistId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public int getArtistId() {
        return artistId;
    }

}
