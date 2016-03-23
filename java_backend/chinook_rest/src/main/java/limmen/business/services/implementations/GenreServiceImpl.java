package limmen.business.services.implementations;

import limmen.business.services.GenreService;
import limmen.integration.entities.Genre;
import limmen.integration.repositories.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
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
    public List<Genre> getAllGenres() {
        return genreRepository.getAllGenres();
    }

    @Override
    public Genre getGenre(int genreId) {
        return genreRepository.getGenre(genreId);
    }
}
