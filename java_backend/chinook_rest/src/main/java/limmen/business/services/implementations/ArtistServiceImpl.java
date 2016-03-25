package limmen.business.services.implementations;

import limmen.business.services.ArtistService;
import limmen.integration.entities.Artist;
import limmen.integration.repositories.ArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the ArtistService interface, uses a repository for database interaction.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Service
public class ArtistServiceImpl implements ArtistService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ArtistRepository artistRepository;

    @Inject
    public ArtistServiceImpl(final ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.getAllArtists();
    }

    @Override
    public Artist getArtist(int artistId) {
        return artistRepository.getArtist(artistId);
    }
}
