package limmen.business.services;

import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.AlbumFilter;
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
     * Method to get all albums (filtered).
     *
     * @param albumFilter properties to filter the list of albums on
     * @return list of filtered albums
     */
    public List<Album> getAllAlbums(AlbumFilter albumFilter) throws SortException;
    
    /**
     * Method to get a album with a specified id.
     *
     * @param albumId id of the album.
     * @return Album with the specified id.
     */
    public Album getAlbum(int albumId);

    /**
     * Method to create a new album.
     *
     * @param album data of the album to create
     * @return the created  album
     */
    public Album createNewAlbum(Album album);

    /**
     * Method to update a album.
     *
     * @param album album to update
     * @return updated album
     */
    public Album updateAlbum(Album album);

    /**
     * Method to update the list of albums
     *
     * @param albums data to update albums list with
     * @return new list of albums
     */
    public List<Album> updateAlbums(List<Album> albums);

    /**
     * Method to delete a album.
     *
     * @param albumId id of the album to delete
     * @return the deleted album.
     */
    public Album deleteAlbum(int albumId);
}
