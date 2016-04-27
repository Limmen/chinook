package limmen.integration.repositories;

import limmen.integration.entities.PlaylistTrack;
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
 * CRUD-repository for the "PlaylistTrack" table in the chinook database.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Repository
public class PlaylistTrackRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbc;

    /**
     * Method to query the database for a playlisttrack with a specified trackid and playlistid..
     *
     * @param trackId id of the track.
     * @param playlistId id of the playlist.
     * @return playlisttrack with the specified trackid and playlistid.
     */
    public PlaylistTrack getPlaylistTrack(int trackId, int playlistId) {
        log.debug("getPlaylistTrack, trackId: {}, playlistId: {} . from Database", trackId, playlistId);
        return jdbc.queryForObject("SELECT * FROM \"PlaylistTrack\" WHERE \"TrackId\"=? AND \"PlaylistId\"=? ",
                playlistTrackMapper, trackId, playlistId);
    }

    /**
     * Method to query the database for a list of all playlisttracks.
     *
     * @return list of playlisttracks.
     */
    public List<PlaylistTrack> getAllPlaylistTracks(){
        log.debug("getAllPlaylistTracks from Database");
        return jdbc.query("SELECT * FROM \"PlaylistTrack\";", playlistTrackMapper);
    }

    /**
     * Method to update the database with a new playlistTrack.
     *
     * @param playlistTrack playlistTrack to insert
     * @return the inserted playlistTrack
     */
    public PlaylistTrack createNewPlaylistTrack(PlaylistTrack playlistTrack) {
        log.info("Update Database with new PlaylistTrack. TrackId: {}, PlaylistId: {}", playlistTrack.getTrackId(), playlistTrack.getPlaylistId());
        jdbc.update("INSERT INTO \"PlaylistTrack\" (\"TrackId\", \"PlaylistId\") VALUES (?, ?);", playlistTrack.getTrackId(), playlistTrack.getPlaylistId());
        return playlistTrack;
    }

    /**
     * Method to update the database with new data for a certain playlistTrack.
     *
     * @param playlistTrack data to update
     * @return updated playlistTrack
     */
    public PlaylistTrack updatePlaylistTrack(PlaylistTrack playlistTrack) {
        log.info("Update PlaylistTrack. TrackId: {}, PlaylistId: {}", playlistTrack.getTrackId(), playlistTrack.getPlaylistId());
        jdbc.update("UPDATE \"PlaylistTrack\" SET \"TrackId\" = ?, \"PlaylistId\" = ? " +
                "WHERE \"TrackId\" = ? AND \"PlaylistTrackId\" = ?;", playlistTrack.getTrackId(), playlistTrack.getPlaylistId(),
                playlistTrack.getTrackId(), playlistTrack.getPlaylistId());
        return playlistTrack;
    }

    /**
     * Method to delete playlistTrack from the database.
     *
     * @param trackId id of the track.
     * @param playlistId id of the playlist
     */
    public void deletePlaylistTrack(int trackId, int playlistId) {
        log.debug("delete playlistTrack, TrackId: {}, PlaylistId: {}", trackId, playlistId);
        jdbc.update("DELETE FROM \"PlaylistTrack\" WHERE \"TrackId\" = ? AND \"PlaylistId\" = ?;",
                trackId, playlistId);
    }

    /**
     * Method to delete all playlistTracks from the database.
     */
    public void deletePlaylistTracks() {
        log.debug("delete all playlistTracks");
        jdbc.update("DELETE  * FROM \"PlaylistTrack\";");
    }
    
    private static final RowMapper<PlaylistTrack> playlistTrackMapper = new RowMapper<PlaylistTrack>() {
        public PlaylistTrack mapRow(ResultSet rs, int rowNum) throws SQLException {
            PlaylistTrack playlistTrack = new PlaylistTrack(rs.getInt("TrackId"), rs.getInt("PlaylistId"));
            return playlistTrack;
        }
    };
}
