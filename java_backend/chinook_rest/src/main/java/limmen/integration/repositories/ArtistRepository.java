package limmen.integration.repositories;

import limmen.integration.entities.Artist;
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
 * CRUD-repository for the "Artist" table in the chinook database.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Repository
public class ArtistRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbc;

    /**
     * Method to query the database for a artist with a certain id.
     *
     * @param artistId id of the artist.
     * @return Artist with the specified id.
     */
    public Artist getArtist(int artistId) {
        return jdbc.queryForObject("SELECT * FROM \"Artist\" WHERE \"ArtistId\"=?", artistMapper, artistId);
    }

    /**
     * Method to query the database for all artists.
     *
     * @return list of artists.
     */
    public List<Artist> getAllArtists(){
        log.info("getAllArtists from Database");
        return jdbc.query("SELECT * FROM \"Artist\";", artistMapper);
    }

    private static final RowMapper<Artist> artistMapper = new RowMapper<Artist>() {
        public Artist mapRow(ResultSet rs, int rowNum) throws SQLException {
            Artist artist = new Artist(rs.getInt("ArtistId"), rs.getString("Name"));
            return artist;
        }
    };
}
