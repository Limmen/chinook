package limmen.business.services.filters;

import limmen.integration.entities.Artist;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for implementing filtering and sorting functionality for the Artist-Resource.
 *
 * @author Kim Hammar on 2016-04-25.
 */
public class ArtistFilter {

    private String sort;
    private String artistId;
    private String name;

    /**
     * Default constructor
     */
    public ArtistFilter() {
    }

    /**
     * Method to filter a list of artists.
     *
     * @param artists list to filter
     * @return filtered list
     */
    public List<Artist> filter(List<Artist> artists) {
        if (name != null)
            artists = artists.stream().filter(artist -> artist.getName().equals(name)).collect(Collectors.toList());
        if (artistId != null)
            artists = artists.stream().filter(artist -> artist.getArtistId() == Integer.parseInt(artistId)).collect(Collectors.toList());
        return artists;
    }

    /**
     * Method to sort a list of artists.
     *
     * @param artists list to sort
     * @return sorted list
     */
    public List<Artist> sort(List<Artist> artists) {
        if(sort == null)
            return artists;
        String order = sort.substring(0, 1);
        String property = sort.substring(1, sort.length());
        Comparator<Artist> comparator = null;
        if (property.equals("artistId")) {
            comparator = (artist1, artist2) ->
            {
                if (artist1.getArtistId() > artist2.getArtistId())
                    return 1;
                else if (artist1.getArtistId() < artist2.getArtistId())
                    return -1;
                return 0;
            };
        }
        if (property.equals("name")) {
            comparator = (artist1, artist2) ->
            {
                if (artist1.getName().compareTo(artist2.getName()) > 0)
                    return 1;
                else if (artist1.getName().compareTo(artist2.getName()) < 0)
                    return -1;
                return 0;
            };
        }
        Collections.sort(artists, comparator);
        if (order.equals("-")) {
            Collections.reverse(artists);
        }
        return artists;
    }


    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
