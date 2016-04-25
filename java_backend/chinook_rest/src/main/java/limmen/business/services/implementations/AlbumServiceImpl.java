package limmen.business.services.implementations;

import limmen.business.services.AlbumService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.AlbumFilter;
import limmen.integration.entities.Album;
import limmen.integration.repositories.AlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the AlbumService interface, uses a repository for database interaction.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Service
public class AlbumServiceImpl  implements AlbumService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final AlbumRepository albumRepository;

    @Inject
    public AlbumServiceImpl(final AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> getAllAlbums(AlbumFilter albumFilter) throws SortException {
        List<Album> albums = getAllAlbums();
        albums = albumFilter.filter(albums);
        try {
            return albumFilter.sort(albums);
        } catch (Exception e) {
            throw new SortException("Invalid query string for sorting: " + albumFilter.getSort());
        }
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.getAllAlbums();
    }

    @Override
    public Album getAlbum(int albumId) {
        return albumRepository.getAlbum(albumId);
    }

    @Override
    public Album createNewAlbum(Album album) {
        album.setAlbumId(albumRepository.getMaxId() + 1);
        return albumRepository.createNewAlbum(album);
    }

    @Override
    public Album updateAlbum(Album album) {
        return albumRepository.updateAlbum(album);
    }

    @Override
    public List<Album> updateAlbums(List<Album> albums) {
        albumRepository.deleteAlbums();
        albums.forEach((album) -> {
            createNewAlbum(album);
        });
        return getAllAlbums();
    }

    @Override
    public Album deleteAlbum(int albumId) {
        Album album = getAlbum(albumId);
        albumRepository.deleteAlbum(albumId);
        return album;
    }
}
