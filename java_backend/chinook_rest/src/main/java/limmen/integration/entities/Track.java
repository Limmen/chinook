package limmen.integration.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class Track {
    @NotNull
    private int trackId;
    @NotNull
    @Size(max = 120)
    private String name;
    private int albumId;
    @NotNull
    private int mediaTypeId;
    private int genreId;
    @Size(max = 220)
    private String composer;
    @NotNull
    private int milliseconds;
    private int bytes;
    @NotNull
    private float unitPrice;

    public Track(int trackId, String name, int albumId, int mediaTypeId, int genreId, String composer, int milliseconds,
                 int bytes, float unitPrice) {
        this.trackId = trackId;
        this.name = name;
        this.albumId = albumId;
        this.mediaTypeId = mediaTypeId;
        this.genreId = genreId;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
    }

    public int getTrackId() {
        return trackId;
    }

    public String getName() {
        return name;
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getMediaTypeId() {
        return mediaTypeId;
    }

    public int getGenreId() {
        return genreId;
    }

    public String getComposer() {
        return composer;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public int getBytes() {
        return bytes;
    }

    public float getUnitPrice() {
        return unitPrice;
    }
}
