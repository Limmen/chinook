package limmen.integration.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * POJO that represents a Artist entity from the chinook database
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class Artist {
    @NotNull
    private int artistId;
    @Size(max = 120)
    private String name;

    /**
     * Class constructor. Initializes an entity class
     *
     * @param artistId id of the artist, unique.
     * @param name name of the artist
     */
    public Artist(int artistId, String name) {
        this.artistId = artistId;
        this.name = name;
    }

    /**
     * Alternative constructor when Id is not available
     *
     * @param name name of the artist
     */
    public Artist(String name) {
        this.name = name;
    }

    public Artist() {}

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

}
