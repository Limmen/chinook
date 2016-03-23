package limmen.business.services;

import limmen.integration.entities.Album;

import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public interface AlbumService {

    public List<Album> getAllAlbums();
    public Album getAlbum(int albumId);
}
