package limmen.business.services;

import limmen.integration.entities.Artist;

import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public interface ArtistService {

    public List<Artist> getAllArtists();
    public Artist getArtist(int ArtistId);
}
