package limmen.business.services;

import limmen.integration.entities.Track;

import java.util.List;

/**
 * Interface for a service that provides CRUD services for track-data.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public interface TrackService {

    /**
     * Method to get all tracks.
     *
     * @return list of tracks
     */
    public List<Track> getAllTracks();

    /**
     * Method to get a track with a specified id.
     *
     * @param trackId id of the track.
     * @return Track with the specified id.
     */
    public Track getTrack(int trackId);
}
