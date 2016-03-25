package limmen.business.services;

import limmen.integration.entities.Album;

import java.util.List;

/**
 * Interface for a service that provides CRUD services for album-data.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public interface AlbumService {

    /**
     * Method to get all albums.
     *
     * @return list of albums
     */
    public List<Album> getAllAlbums();

    /**
     * Method to get a album with a specified id.
     *
     * @param albumId id of the album.
     * @return Album with the specified id.
     */
    public Album getAlbum(int albumId);
}
