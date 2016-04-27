package limmen.integration.repositories;

import limmen.integration.entities.Track;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * CRUD-repository for the "Track" table in the chinook database.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Repository
public class TrackRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbc;

    /**
     * Method to query the database for a track with a certain id.
     *
     * @param trackId id of the track.
     * @return Track with the specified id.
     */
    public Track getTrack(int trackId) {
        log.debug("getTrack {} from Database", trackId);
        return jdbc.queryForObject("SELECT * FROM \"Track\" WHERE \"TrackId\"=?", trackMapper, trackId);
    }

    /**
     * Method to query the database for a list of all tracks.
     *
     * @return list of tracks.
     */
    public List<Track> getAllTracks(){
        log.debug("getAllTracks from Database");
        return jdbc.query("SELECT * FROM \"Track\";", trackMapper);
    }

    /**
     * Method to update the database with a new track.
     *
     * @param track track to insert
     * @return the inserted track
     */
    public Track createNewTrack(Track track) {
        log.info("Update Database with new Track. trackId: {}, name: {}, albumId: {}, mediaTypeId: {}," +
                " genreId: {}, composer: {}, milliseconds: {}, bytes: {}, unitPrice: {}", track.getTrackId(), track.getName(),
                track.getAlbumId(), track.getMediaTypeId(), track.getGenreId(), track.getComposer(),
                track.getMilliseconds(), track.getBytes(), track.getUnitPrice());
        jdbc.update("INSERT INTO \"Track\" (\"TrackId\", \"Name\", \"AlbumId\", \"MediaTypeId\"," +
                "\"GenreId\", \"Composer\", \"Milliseconds\", \"Bytes\", \"UnitPrice\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);",
                track.getTrackId(), track.getName(),
                track.getAlbumId(), track.getMediaTypeId(), track.getGenreId(), track.getComposer(),
                track.getMilliseconds(), track.getBytes(), track.getUnitPrice());
        return track;
    }

    /**
     * Method to query the database for the maximum id of all tracks
     *
     * @return maxmum id
     */
    public int getMaxId() {
        log.debug("get max Id of tracks");
        return jdbc.queryForObject("SELECT COALESCE(MAX(\"TrackId\"),0) FROM \"Track\";", maxIdMapper);
    }

    /**
     * Method to update the database with new data for a certain track.
     *
     * @param track data to update
     * @return updated track
     */
    public Track updateTrack(Track track) {
        log.debug("update track {}", track.getTrackId());
        jdbc.update("UPDATE \"Track\" SET \"Name\" = ?, \"AlbumId\" = ?, \"MediaTypeId\" = ?, \"GenreId\" = ?," +
                "\"Composer\" = ?, \"Milliseconds\" = ?, \"Bytes\" = ?, \"UnitPrice\" = ? WHERE \"TrackId\" = ?;",
                track.getName(),
                track.getAlbumId(), track.getMediaTypeId(), track.getGenreId(), track.getComposer(),
                track.getMilliseconds(), track.getBytes(), track.getUnitPrice(), track.getTrackId());
        return track;
    }

    /**
     * Method to delete track from the database.
     *
     * @param trackId id of the track to delete
     */
    public void deleteTrack(int trackId) {
        log.debug("delete track {}", trackId);
        jdbc.update("DELETE FROM \"Track\" WHERE \"TrackId\" = ?;", trackId);
    }

    /**
     * Method to delete all tracks from the database.
     */
    public void deleteTracks() {
        log.debug("delete all tracks");
        jdbc.update("DELETE  * FROM \"Track\";");
    }


    private static final RowMapper<Track> trackMapper = new RowMapper<Track>() {
        public Track mapRow(ResultSet rs, int rowNum) throws SQLException {
            Track track = new Track(rs.getInt("TrackId"), rs.getString("Name"), rs.getInt("AlbumId"),
                    rs.getInt("MediaTypeId"), rs.getInt("GenreId"), rs.getString("Composer"), rs.getInt("Milliseconds"),
                    rs.getInt("Bytes"), rs.getFloat("UnitPrice"));
            return track;
        }
    };

    private static final RowMapper<Integer> maxIdMapper = new RowMapper<Integer>() {
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt(1);
            return id;
        }
    };
}
