package limmen.business.services.filters;

import limmen.integration.entities.Track;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for implementing filtering and sorting functionality for the Track-Resource.
 *
 * @author Kim Hammar on 2016-04-25.
 */
public class TrackFilter {

    private String sort;
    private String trackId;
    private String name;
    private String albumId;
    private String mediaTypeId;
    private String genreId;
    private String composer;
    private String milliseconds;
    private String bytes;
    private String unitPrice;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * Default constructor
     */
    public TrackFilter() {
    }

    /**
     * Method to filter a list of tracks.
     *
     * @param tracks list to filter
     * @return filtered list
     */
    public List<Track> filter(List<Track> tracks) {
        log.debug("INSIDE FILTER METHOD, composer: {}, first track composer: {}", composer, tracks.get(0).getComposer());
        if (trackId != null)
            tracks = tracks.stream().filter(track -> track.getTrackId() == Integer.parseInt(trackId)).collect(Collectors.toList());
        if (albumId != null)
            tracks = tracks.stream().filter(track -> track.getAlbumId() == Integer.parseInt(albumId)).collect(Collectors.toList());
        if (mediaTypeId != null)
            tracks = tracks.stream().filter(track -> track.getMediaTypeId() == Integer.parseInt(mediaTypeId)).collect(Collectors.toList());
        if (genreId != null)
            tracks = tracks.stream().filter(track -> track.getGenreId() == Integer.parseInt(genreId)).collect(Collectors.toList());
        if (milliseconds != null)
            tracks = tracks.stream().filter(track -> track.getMilliseconds() == Integer.parseInt(milliseconds)).collect(Collectors.toList());
        if (bytes != null)
            tracks = tracks.stream().filter(track -> track.getBytes() == Integer.parseInt(bytes)).collect(Collectors.toList());
        if (unitPrice != null)
            tracks = tracks.stream().filter(track -> track.getUnitPrice() == Float.parseFloat(unitPrice)).collect(Collectors.toList());
        if (name != null)
            tracks = tracks.stream().filter(track -> {if(track.getName() != null) return track.getName().equals(name); else return false;}).collect(Collectors.toList());
        if (composer != null)
            tracks = tracks.stream().filter(track -> {if(track.getComposer() != null) return track.getComposer().equals(composer); else  return false;}).collect(Collectors.toList());
        return tracks;
    }

    /**
     * Method to sort a list of tracks.
     *
     * @param tracks list to sort
     * @return sorted list
     */
    public List<Track> sort(List<Track> tracks) {
        if(sort == null)
            return tracks;
        String order = sort.substring(0, 1);
        String property = sort.substring(1, sort.length());
        Comparator<Track> comparator = null;
        if (property.equals("trackId")) {
            comparator = (track1, track2) ->
            {
                if (track1.getTrackId() > track2.getTrackId())
                    return 1;
                else if (track1.getTrackId() < track2.getTrackId())
                    return -1;
                return 0;
            };
        }
        if (property.equals("albumId")) {
            comparator = (track1, track2) ->
            {
                if (track1.getAlbumId() > track2.getAlbumId())
                    return 1;
                else if (track1.getAlbumId() < track2.getAlbumId())
                    return -1;
                return 0;
            };
        }
        if (property.equals("mediaTypeId")) {
            comparator = (track1, track2) ->
            {
                if (track1.getMediaTypeId() > track2.getMediaTypeId())
                    return 1;
                else if (track1.getMediaTypeId() < track2.getMediaTypeId())
                    return -1;
                return 0;
            };
        }
        if (property.equals("genreId")) {
            comparator = (track1, track2) ->
            {
                if (track1.getGenreId() > track2.getGenreId())
                    return 1;
                else if (track1.getGenreId() < track2.getGenreId())
                    return -1;
                return 0;
            };
        }
        if (property.equals("milliseconds")) {
            comparator = (track1, track2) ->
            {
                if (track1.getMilliseconds() > track2.getMilliseconds())
                    return 1;
                else if (track1.getMilliseconds() < track2.getMilliseconds())
                    return -1;
                return 0;
            };
        }
        if (property.equals("bytes")) {
            comparator = (track1, track2) ->
            {
                if (track1.getBytes() > track2.getBytes())
                    return 1;
                else if (track1.getBytes() < track2.getBytes())
                    return -1;
                return 0;
            };
        }
        if (property.equals("unitPrice")) {
            comparator = (track1, track2) ->
            {
                if (track1.getUnitPrice() > track2.getUnitPrice())
                    return 1;
                else if (track1.getUnitPrice() < track2.getUnitPrice())
                    return -1;
                return 0;
            };
        }
        if (property.equals("name")) {
            comparator = (track1, track2) ->
            {
                if(track1.getName() == null && track2.getName() == null)
                    return 0;
                if(track1.getName() == null)
                    return -1;
                if(track2.getName() == null)
                    return 1;
                if (track1.getName().compareTo(track2.getName()) > 0)
                    return 1;
                else if (track1.getName().compareTo(track2.getName()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("composer")) {
            comparator = (track1, track2) ->
            {
                if(track1.getComposer() == null && track2.getComposer() == null)
                    return 0;
                if(track1.getComposer() == null)
                    return -1;
                if(track2.getComposer() == null)
                    return 1;
                if (track1.getComposer().compareTo(track2.getComposer()) > 0)
                    return 1;
                else if (track1.getComposer().compareTo(track2.getComposer()) < 0)
                    return -1;
                return 0;
            };
        }
        Collections.sort(tracks, comparator);
        if (order.equals("-")) {
            Collections.reverse(tracks);
        }
        return tracks;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getMediaTypeId() {
        return mediaTypeId;
    }

    public void setMediaTypeId(String mediaTypeId) {
        this.mediaTypeId = mediaTypeId;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getBytes() {
        return bytes;
    }

    public void setBytes(String bytes) {
        this.bytes = bytes;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(String milliseconds) {
        this.milliseconds = milliseconds;
    }
}
