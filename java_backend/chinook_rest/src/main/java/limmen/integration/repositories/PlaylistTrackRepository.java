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
 * @author Kim Hammar on 2016-03-22.
 */
@Repository
public class PlaylistTrackRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbc;

    public PlaylistTrack getPlaylistTrack(int playlistTrackId, int playlistId) {
        return jdbc.queryForObject("SELECT * FROM \"PlaylistTrack\" WHERE \"PlaylistTrackId\"=? AND \"PlaylistId\"=? ",
                playlistTrackMapper, playlistTrackId, playlistId);
    }

    public List<PlaylistTrack> getAllPlaylistTracks(){
        log.info("getAllPlaylistTracks from Database");
        return jdbc.query("SELECT * FROM \"PlaylistTrack\";", playlistTrackMapper);
    }

    private static final RowMapper<PlaylistTrack> playlistTrackMapper = new RowMapper<PlaylistTrack>() {
        public PlaylistTrack mapRow(ResultSet rs, int rowNum) throws SQLException {
            PlaylistTrack playlistTrack = new PlaylistTrack(rs.getInt("TrackId"), rs.getInt("PlaylistId"));
            return playlistTrack;
        }
    };
}
