package limmen.business.services;

import limmen.integration.entities.Album;
import limmen.integration.repositories.AlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
@Service
public class AlbumServiceImpl  implements AlbumService{
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final AlbumRepository albumRepository;

    @Inject
    public AlbumServiceImpl(final AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }
    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.getAllAlbums();
    }

    @Override
    public Album getAlbum(int albumId) {
        return albumRepository.getAlbum(albumId);
    }
}
