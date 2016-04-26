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

    /**
     * Method to update the database with a new playlist.
     *
     * @param playlist playlist to insert
     * @return the inserted playlist
     */
    public Playlist createNewPlaylist(Playlist playlist) {
        log.info("Update Database with new Playlist. playlistId: {}, name: {}", playlist.getPlaylistId(), playlist.getName());
        jdbc.update("INSERT INTO \"Playlist\" (\"PlaylistId\", \"Name\") VALUES (?, ?);", playlist.getPlaylistId(), playlist.getName());
        return playlist;
    }

    /**
     * Method to query the database for the maximum id of all playlists
     *
     * @return maxmum id
     */
    public int getMaxId() {
        log.debug("get max Id of playlists");
        return jdbc.queryForObject("SELECT COALESCE(MAX(\"PlaylistId\"),0) FROM \"Playlist\";", maxIdMapper);
    }

    /**
     * Method to update the database with new data for a certain playlist.
     *
     * @param playlist data to update
     * @return updated playlist
     */
    public Playlist updatePlaylist(Playlist playlist) {
        log.debug("update playlist {}", playlist.getPlaylistId());
        jdbc.update("UPDATE \"Playlist\" SET \"Name\" = ? WHERE \"PlaylistId\" = ?;", playlist.getName(), playlist.getPlaylistId());
        return playlist;
    }

    /**
     * Method to delete playlist from the database.
     *
     * @param playlistId id of the playlist to delete
     */
    public void deletePlaylist(int playlistId) {
        log.debug("delete playlist {}", playlistId);
        jdbc.update("DELETE FROM \"Playlist\" WHERE \"PlaylistId\" = ?;", playlistId);
    }

    /**
     * Method to delete all playlists from the database.
     */
    public void deletePlaylists() {
        log.debug("delete all playlists");
        jdbc.update("DELETE  * FROM \"Playlist\";");
    }
    
    private static final RowMapper<Playlist> playlistMapper = new RowMapper<Playlist>() {
        public Playlist mapRow(ResultSet rs, int rowNum) throws SQLException {
            Playlist playlist = new Playlist(rs.getInt("PlaylistId"), rs.getString("Name"));
            return playlist;
        }
    };

    private static final RowMapper<Integer> maxIdMapper = new RowMapper<Integer>() {
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt(1);
            return id;
        }
    };
}
