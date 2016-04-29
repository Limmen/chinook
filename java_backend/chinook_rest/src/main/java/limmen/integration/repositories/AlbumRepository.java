package limmen.integration.repositories;

import limmen.integration.entities.Album;
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
 * CRUD-repository for the "Album" table in the chinook database.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Repository
public class AlbumRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbc;

    /**
     * Method to query the database for a album with a certain unique id.
     *
     * @param albumId id of the album.
     * @return Album with albumId.
     */
    public Album getAlbum(int albumId) {
        log.debug("getAlbum {} from Database", albumId);
        return jdbc.queryForObject("SELECT * FROM \"Album\" WHERE \"AlbumId\"=?", albumMapper, albumId);
    }

    /**
     * Method to query the database for a list of all  albums.
     *
     * @return list of albums
     */
    public List<Album> getAllAlbums(){
        log.debug("getAllAlbums from Database");
        return jdbc.query("SELECT * FROM \"Album\";", albumMapper);
    }

    /**
     * Method to update the database with a new album.
     *
     * @param album album to insert
     * @return the inserted album
     */
    public Album createNewAlbum(Album album) {
        log.info("Update Database with new Album. albumId: {}, title: {}, artistId: {}", album.getAlbumId(), album.getTitle(), album.getArtistId());
        jdbc.update("INSERT INTO \"Album\" (\"AlbumId\", \"Title\", \"ArtistId\") VALUES (?, ?, ?);", album.getAlbumId(), album.getTitle(), album.getArtistId());
        return album;
    }

    /**
     * Method to query the database for the maximum id of all albums
     *
     * @return maxmum id
     */
    public int getMaxId() {
        log.debug("get max Id of albums");
        return jdbc.queryForObject("SELECT COALESCE(MAX(\"AlbumId\"),0) FROM \"Album\";", maxIdMapper);
    }

    /**
     * Method to update the database with new data for a certain album.
     *
     * @param album data to update
     * @return updated album
     */
    public Album updateAlbum(Album album) {
        log.debug("update album {}", album.getAlbumId());
        jdbc.update("UPDATE \"Album\" SET \"Title\" = ?, \"ArtistId\" = ? WHERE \"AlbumId\" = ?;", album.getTitle(), album.getArtistId(), album.getAlbumId());
        return album;
    }

    /**
     * Method to delete album from the database.
     *
     * @param albumId id of the album to delete
     */
    public void deleteAlbum(int albumId) {
        log.debug("delete album {}", albumId);
        jdbc.update("DELETE FROM \"Album\" WHERE \"AlbumId\" = ?;", albumId);
    }

    /**
     * Method to delete all albums from the database.
     */
    public void deleteAlbums() {
        log.debug("delete all albums");
        jdbc.update("DELETE  * FROM \"Album\";");
    }
    
    private static final RowMapper<Album> albumMapper = new RowMapper<Album>() {
        public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
            Album album = new Album(rs.getInt("AlbumId"), rs.getString("Title"), rs.getInt("ArtistId"));
            return album;
        }
    };

    private static final RowMapper<Integer> maxIdMapper = new RowMapper<Integer>() {
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt(1);
            return id;
        }
    };
}
