package limmen.business.services;

import limmen.integration.entities.Artist;

import java.util.List;

/**
 * Interface for a service that provides CRUD services for artist-data.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public interface ArtistService {

    /**
     * Method to get all artists.
     *
     * @return list of artists
     */
    public List<Artist> getAllArtists();

    /**
     * Method to get a artist with a specified id.
     *
     * @param artistId id of the artist.
     * @return artis with the specified id.
     */
    public Artist getArtist(int artistId);

    /**
     * Method to create a new artist
     *
     * @param artist data of the artist to create
     * @return the created  artist
     */
    public Artist createNewArtist(Artist artist);
}
