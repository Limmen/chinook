package limmen.business.services;

import limmen.integration.entities.Genre;

import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public interface GenreService {

    public List<Genre> getAllGenres();
    public Genre getGenre(int genreId);
}
