package limmen.integration.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class Artist {
    @NotNull
    private int artistId;
    @Size(max = 120)
    private String name;

    public Artist(int artistId, String name) {
        this.artistId = artistId;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "Artist[ArtistId=%d, Name='%s', lastName='%s']",
                artistId, name);
    }

    public int getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }
}
