package limmen.business.services.filters;

import limmen.integration.entities.Album;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for implementing filtering and sorting functionality for the Album-Resource.
 *
 * @author Kim Hammar on 2016-04-25.
 */
public class AlbumFilter {

    private String sort;
    private String albumId;
    private String title;
    private String artistId;


    /**
     * Default constructor
     */
    public AlbumFilter() {
    }

    /**
     * Method to filter a list of albums.
     *
     * @param albums list to filter
     * @return filtered list
     */
    public List<Album> filter(List<Album> albums) {
        if (title != null)
            albums = albums.stream().filter(album -> {if(album.getTitle() != null)
                return album.getTitle().equals(title); else return false;}).collect(Collectors.toList());
        if (albumId != null)
            albums = albums.stream().filter(album -> album.getAlbumId() == Integer.parseInt(albumId)).collect(Collectors.toList());
        if (artistId != null)
            albums = albums.stream().filter(album -> album.getAlbumId() == Integer.parseInt(artistId)).collect(Collectors.toList());
        return albums;
    }

    /**
     * Method to sort a list of albums.
     *
     * @param albums list to sort
     * @return sorted list
     */
    public List<Album> sort(List<Album> albums) {
        if(sort == null)
            return albums;
        String order = sort.substring(0, 1);
        String property = sort.substring(1, sort.length());
        Comparator<Album> comparator = null;
        if (property.equals("albumId")) {
            comparator = (album1, album2) ->
            {
                if (album1.getAlbumId() > album2.getAlbumId())
                    return 1;
                else if (album1.getAlbumId() < album2.getAlbumId())
                    return -1;
                return 0;
            };
        }
        if (property.equals("title")) {
            comparator = (album1, album2) ->
            {
                if(album1.getTitle() == null && album2.getTitle() == null)
                    return 0;
                if(album1.getTitle() == null)
                    return -1;
                if(album2.getTitle() == null)
                    return 1;
                if (album1.getTitle().compareTo(album2.getTitle()) > 0)
                    return 1;
                else if (album1.getTitle().compareTo(album2.getTitle()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("artistId")) {
            comparator = (album1, album2) ->
            {
                if (album1.getArtistId() > album2.getArtistId())
                    return 1;
                else if (album1.getArtistId() < album2.getArtistId())
                    return -1;
                return 0;
            };
        }
        Collections.sort(albums, comparator);
        if (order.equals("-")) {
            Collections.reverse(albums);
        }
        return albums;
    }


    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }
}
