package limmen.business.services.filters;

import limmen.integration.entities.PlaylistTrack;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for implementing filtering and sorting functionality for the PlaylistTrack-Resource.
 *
 * @author Kim Hammar on 2016-04-25.
 */
public class PlaylistTrackFilter {

    private String sort;
    private String trackId;
    private String playlistId;

    /**
     * Default constructor
     */
    public PlaylistTrackFilter() {
    }

    /**
     * Method to filter a list of playlistTracks.
     *
     * @param playlistTracks list to filter
     * @return filtered list
     */
    public List<PlaylistTrack> filter(List<PlaylistTrack> playlistTracks) {
        if (trackId != null)
            playlistTracks = playlistTracks.stream().filter(playlistTrack -> playlistTrack.getTrackId() == Integer.parseInt(trackId)).collect(Collectors.toList());
        if (playlistId != null)
            playlistTracks = playlistTracks.stream().filter(playlistTrack -> playlistTrack.getPlaylistId() == Integer.parseInt(playlistId)).collect(Collectors.toList());
        return playlistTracks;
    }

    /**
     * Method to sort a list of playlistTracks.
     *
     * @param playlistTracks list to sort
     * @return sorted list
     */
    public List<PlaylistTrack> sort(List<PlaylistTrack> playlistTracks) {
        if(sort == null)
            return playlistTracks;
        String order = sort.substring(0, 1);
        String property = sort.substring(1, sort.length());
        Comparator<PlaylistTrack> comparator = null;
        if (property.equals("trackId")) {
            comparator = (playlistTrack1, playlistTrack2) ->
            {
                if (playlistTrack1.getTrackId() > playlistTrack2.getTrackId())
                    return 1;
                else if (playlistTrack1.getTrackId() < playlistTrack2.getTrackId())
                    return -1;
                return 0;
            };
        }
        if (property.equals("playlistId")) {
            comparator = (playlistTrack1, playlistTrack2) ->
            {
                if (playlistTrack1.getPlaylistId() > playlistTrack2.getPlaylistId())
                    return 1;
                else if (playlistTrack1.getPlaylistId() < playlistTrack2.getPlaylistId())
                    return -1;
                return 0;
            };
        }
        Collections.sort(playlistTracks, comparator);
        if (order.equals("-")) {
            Collections.reverse(playlistTracks);
        }
        return playlistTracks;
    }


    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }
}
