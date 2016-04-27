package limmen.business.services.implementations;

import limmen.business.services.GenreService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.GenreFilter;
import limmen.integration.entities.Genre;
import limmen.integration.repositories.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the GenreService interface, uses a repository for database interaction.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Service
public class GenreServiceImpl implements GenreService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final GenreRepository genreRepository;

    @Inject
    public GenreServiceImpl(final GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    @Override
    public List<Genre> getAllGenres(GenreFilter genreFilter) throws SortException {
        List<Genre> genres = getAllGenres();
        genres = genreFilter.filter(genres);
        try {
            return genreFilter.sort(genres);
        } catch (Exception e) {
            throw new SortException("Invalid query string for sorting: " + genreFilter.getSort());
        }
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.getAllGenres();
    }

    @Override
    public Genre getGenre(int genreId) {
        return genreRepository.getGenre(genreId);
    }

    @Override
    public Genre createNewGenre(Genre genre) {
        genre.setGenreId(genreRepository.getMaxId() + 1);
        return genreRepository.createNewGenre(genre);
    }

    @Override
    public Genre updateGenre(Genre genre) {
        return genreRepository.updateGenre(genre);
    }

    @Override
    public List<Genre> updateGenres(List<Genre> genres) {
        genreRepository.deleteGenres();
        genres.forEach((genre) -> {
            createNewGenre(genre);
        });
        return getAllGenres();
    }

    @Override
    public Genre deleteGenre(int genreId) {
        Genre genre = getGenre(genreId);
        genreRepository.deleteGenre(genreId);
        return genre;
    }
}
