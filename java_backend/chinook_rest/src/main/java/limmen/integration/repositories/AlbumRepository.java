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
 * @author Kim Hammar on 2016-03-22.
 */
@Repository
public class AlbumRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbc;

    public Album getAlbum(int albumId) {
        return jdbc.queryForObject("SELECT * FROM \"Album\" WHERE \"AlbumId\"=?", albumMapper, albumId);
    }

    public List<Album> getAllAlbums(){
        log.info("getAllAlbums from Database");
        return jdbc.query("SELECT * FROM \"Album\";", albumMapper);
    }

    private static final RowMapper<Album> albumMapper = new RowMapper<Album>() {
        public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
            Album album = new Album(rs.getInt("AlbumId"), rs.getString("Title"), rs.getInt("ArtistId"));
            return album;
        }
    };
}
