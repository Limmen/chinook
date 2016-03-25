package limmen.business.services;

import limmen.integration.entities.Genre;

import java.util.List;

/**
 * Interface for a service that provides CRUD services for genre-data.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public interface GenreService {

    /**
     * Method to get all genres.
     *
     * @return list of genres
     */
    public List<Genre> getAllGenres();

    /**
     * Method to get a genre with a specified id.
     *
     * @param genreId id of the genre.
     * @return Genre with the specified id.
     */
    public Genre getGenre(int genreId);
}
