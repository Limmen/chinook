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
        log.debug("getArtist from Database");
        return jdbc.queryForObject("SELECT * FROM \"Artist\" WHERE \"ArtistId\"=?", artistMapper, artistId);
    }

    /**
     * Method to query the database for all artists.
     *
     * @return list of artists.
     */
    public List<Artist> getAllArtists(){
        log.debug("getAllArtists from Database");
        return jdbc.query("SELECT * FROM \"Artist\";", artistMapper);
    }

    /**
     * Method to update the database with a new artist.
     *
     * @param artist artist to insert
     * @return the inserted artist
     */
    public Artist createNewArtist(Artist artist){
        log.info("Update Database with new Artist");
        jdbc.update("INSERT INTO \"Artist\" (\"ArtistId\", \"Name\") VALUES (?, ?);", artist.getArtistId(), artist.getName());
        return artist;
    }

    /**
     * Method to query the database for the maximum id of all artists
     *
     * @return maxmum id
     */
    public int getMaxId(){
        log.debug("get max Id of artists");
        return jdbc.queryForObject("SELECT max(\"ArtistId\") FROM \"Artist\";", maxIdMapper);
    }

    private static final RowMapper<Artist> artistMapper = new RowMapper<Artist>() {
        public Artist mapRow(ResultSet rs, int rowNum) throws SQLException {
            Artist artist = new Artist(rs.getInt("ArtistId"), rs.getString("Name"));
            return artist;
        }
    };

    private static final RowMapper<Integer> maxIdMapper = new RowMapper<Integer>() {
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id =  rs.getInt(1);
            return id;
        }
    };

}
