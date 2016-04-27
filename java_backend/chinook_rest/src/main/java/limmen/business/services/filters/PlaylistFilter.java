package limmen.business.services.filters;

import limmen.integration.entities.Playlist;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for implementing filtering and sorting functionality for the Playlist-Resource.
 *
 * @author Kim Hammar on 2016-04-25.
 */
public class PlaylistFilter {

    private String sort;
    private String playlistId;
    private String name;

    /**
     * Default constructor
     */
    public PlaylistFilter() {
    }

    /**
     * Method to filter a list of playlists.
     *
     * @param playlists list to filter
     * @return filtered list
     */
    public List<Playlist> filter(List<Playlist> playlists) {
        if (name != null)
            playlists = playlists.stream().filter(playlist -> { if(playlist.getName() != null) return playlist.getName().equals(name); else return false;}).collect(Collectors.toList());
        if (playlistId != null)
            playlists = playlists.stream().filter(playlist -> playlist.getPlaylistId() == Integer.parseInt(playlistId)).collect(Collectors.toList());
        return playlists;
    }

    /**
     * Method to sort a list of playlists.
     *
     * @param playlists list to sort
     * @return sorted list
     */
    public List<Playlist> sort(List<Playlist> playlists) {
        if(sort == null)
            return playlists;
        String order = sort.substring(0, 1);
        String property = sort.substring(1, sort.length());
        Comparator<Playlist> comparator = null;
        if (property.equals("playlistId")) {
            comparator = (playlist1, playlist2) ->
            {
                if (playlist1.getPlaylistId() > playlist2.getPlaylistId())
                    return 1;
                else if (playlist1.getPlaylistId() < playlist2.getPlaylistId())
                    return -1;
                return 0;
            };
        }
        if (property.equals("name")) {
            comparator = (playlist1, playlist2) ->
            {
                if(playlist1.getName() == null && playlist2.getName() == null)
                    return 0;
                if(playlist1.getName() == null)
                    return -1;
                if(playlist2.getName() == null)
                    return 1;
                if (playlist1.getName().compareTo(playlist2.getName()) > 0)
                    return 1;
                else if (playlist1.getName().compareTo(playlist2.getName()) < 0)
                    return -1;
                return 0;
            };
        }
        Collections.sort(playlists, comparator);
        if (order.equals("-")) {
            Collections.reverse(playlists);
        }
        return playlists;
    }


    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
