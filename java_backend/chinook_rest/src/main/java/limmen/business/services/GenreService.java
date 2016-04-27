package limmen.business.services;

import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.GenreFilter;
import limmen.integration.entities.Genre;
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

    /**
     * Method to get all genres (filtered).
     *
     * @param genreFilter properties to filter the list of genres on
     * @return list of filtered genres
     */
    public List<Genre> getAllGenres(GenreFilter genreFilter) throws SortException;

    public Genre getGenre(int genreId);

    /**
     * Method to create a new genre.
     *
     * @param genre data of the genre to create
     * @return the created  genre
     */
    public Genre createNewGenre(Genre genre);

    /**
     * Method to update a genre.
     *
     * @param genre genre to update
     * @return updated genre
     */
    public Genre updateGenre(Genre genre);

    /**
     * Method to update the list of genres
     *
     * @param genres data to update genres list with
     * @return new list of genres
     */
    public List<Genre> updateGenres(List<Genre> genres);

    /**
     * Method to delete a genre.
     *
     * @param genreId id of the genre to delete
     * @return the deleted genre.
     */
    public Genre deleteGenre(int genreId);
}
