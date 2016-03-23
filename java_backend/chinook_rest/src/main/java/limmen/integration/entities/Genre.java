package limmen.integration.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class Genre {
    @NotNull
    private int genreId;
    @Size(max = 120)
    private String name;

    public Genre(int genreId, String name) {
        this.genreId = genreId;
        this.name = name;
    }

    public int getGenreId() {
        return genreId;
    }

    public String getName() {
        return name;
    }
}
