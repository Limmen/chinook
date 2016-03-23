package limmen.business.services;

import limmen.integration.entities.Track;

import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public interface TrackService {

    public List<Track> getAllTracks();
    public Track getTrack(int trackId);
}
