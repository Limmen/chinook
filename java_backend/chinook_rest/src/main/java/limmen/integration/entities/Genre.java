package limmen.integration.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * POJO that represents a Genre entity from the chinook database
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class Genre {
    @NotNull
    private int genreId;
    @Size(max = 120)
    private String name;

    /**
     * Class constructor. Initializes an entity class.
     *
     * @param genreId id of the genre, unique.
     * @param name name of the genre.
     */
    public Genre(int genreId, String name) {
        this.genreId = genreId;
        this.name = name;
    }

    public Genre() {
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }
}
