package limmen.business.services;

import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.TrackFilter;
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
     * Method to get all tracks (filtered).
     *
     * @param trackFilter properties to filter the list of tracks on
     * @return list of filtered tracks
     */
    public List<Track> getAllTracks(TrackFilter trackFilter) throws SortException;
    
    /**
     * Method to get a track with a specified id.
     *
     * @param trackId id of the track.
     * @return Track with the specified id.
     */
    public Track getTrack(int trackId);

    /**
     * Method to create a new track.
     *
     * @param track data of the track to create
     * @return the created  track
     */
    public Track createNewTrack(Track track);

    /**
     * Method to update a track.
     *
     * @param track track to update
     * @return updated track
     */
    public Track updateTrack(Track track);

    /**
     * Method to update the list of tracks
     *
     * @param tracks data to update tracks list with
     * @return new list of tracks
     */
    public List<Track> updateTracks(List<Track> tracks);

    /**
     * Method to delete a track.
     *
     * @param trackId id of the track to delete
     * @return the deleted track.
     */
    public Track deleteTrack(int trackId);
}
