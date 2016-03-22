package limmen.integration.repositories;

import limmen.integration.entities.Playlist;
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
public class PlaylistRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbc;

    public Playlist getPlaylist(int playlistId) {
        return jdbc.queryForObject("SELECT * FROM \"Playlist\" WHERE \"PlaylistId\"=?", playlistMapper, playlistId);
    }

    public List<Playlist> getAllPlaylists(){
        log.info("getAllPlaylists from Database");
        return jdbc.query("SELECT * FROM \"Playlist\";", playlistMapper);
    }

    private static final RowMapper<Playlist> playlistMapper = new RowMapper<Playlist>() {
        public Playlist mapRow(ResultSet rs, int rowNum) throws SQLException {
            Playlist playlist = new Playlist(rs.getInt("PlaylistId"), rs.getString("Name"));
            return playlist;
        }
    };
}
