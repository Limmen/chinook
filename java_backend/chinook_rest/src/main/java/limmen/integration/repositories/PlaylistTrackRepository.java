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
        log.debug("getPlaylistTrack from Database");
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

    private static final RowMapper<PlaylistTrack> playlistTrackMapper = new RowMapper<PlaylistTrack>() {
        public PlaylistTrack mapRow(ResultSet rs, int rowNum) throws SQLException {
            PlaylistTrack playlistTrack = new PlaylistTrack(rs.getInt("TrackId"), rs.getInt("PlaylistId"));
            return playlistTrack;
        }
    };
}
