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
 * CRUD-repository for the "Playlist" table in the chinook database.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Repository
public class PlaylistRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbc;

    /**
     * Method to query the database for a playlist with a certain id.
     *
     * @param playlistId id of the playlist.
     * @return playlist with the specified id.
     */
    public Playlist getPlaylist(int playlistId) {
        log.debug("getPlaylist {} from Database", playlistId);
        return jdbc.queryForObject("SELECT * FROM \"Playlist\" WHERE \"PlaylistId\"=?", playlistMapper, playlistId);
    }

    /**
     * Method to query the database for a list of all playlists.
     *
     * @return list of playlists
     */
    public List<Playlist> getAllPlaylists(){
        log.debug("getAllPlaylists from Database");
        return jdbc.query("SELECT * FROM \"Playlist\";", playlistMapper);
    }

    private static final RowMapper<Playlist> playlistMapper = new RowMapper<Playlist>() {
        public Playlist mapRow(ResultSet rs, int rowNum) throws SQLException {
            Playlist playlist = new Playlist(rs.getInt("PlaylistId"), rs.getString("Name"));
            return playlist;
        }
    };
}
