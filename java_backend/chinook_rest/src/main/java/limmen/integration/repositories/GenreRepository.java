package limmen.integration.repositories;

import limmen.integration.entities.Genre;
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
 * CRUD-repository for the "Genre" table in the chinook database.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Repository
public class GenreRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbc;

    /**
     * Method to query the database for a genre with a certain id.
     *
     * @param genreId id of the genre
     * @return Genre with the specified id.
     */
    public Genre getGenre(int genreId) {
        log.debug("getGenre {} from Database", genreId);
        return jdbc.queryForObject("SELECT * FROM \"Genre\" WHERE \"GenreId\"=?", genreMapper, genreId);
    }

    /**
     * Method to query the database for all genres.
     *
     * @return list of genres
     */
    public List<Genre> getAllGenres(){
        log.debug("getAllGenres from Database");
        return jdbc.query("SELECT * FROM \"Genre\";", genreMapper);
    }

    /**
     * Method to update the database with a new genre.
     *
     * @param genre genre to insert
     * @return the inserted genre
     */
    public Genre createNewGenre(Genre genre) {
        log.info("Update Database with new Genre. genreId: {}, name: {}", genre.getGenreId(), genre.getName());
        jdbc.update("INSERT INTO \"Genre\" (\"GenreId\", \"Name\") VALUES (?, ?);", genre.getGenreId(), genre.getName());
        return genre;
    }

    /**
     * Method to query the database for the maximum id of all genres
     *
     * @return maxmum id
     */
    public int getMaxId() {
        log.debug("get max Id of genres");
        return jdbc.queryForObject("SELECT COALESCE(MAX(\"GenreId\"),0) FROM \"Genre\";", maxIdMapper);
    }

    /**
     * Method to update the database with new data for a certain genre.
     *
     * @param genre data to update
     * @return updated genre
     */
    public Genre updateGenre(Genre genre) {
        log.debug("update genre {}", genre.getGenreId());
        jdbc.update("UPDATE \"Genre\" SET \"Name\" = ? WHERE \"GenreId\" = ?;", genre.getName(), genre.getGenreId());
        return genre;
    }

    /**
     * Method to delete genre from the database.
     *
     * @param genreId id of the genre to delete
     */
    public void deleteGenre(int genreId) {
        log.debug("delete genre {}", genreId);
        jdbc.update("DELETE FROM \"Genre\" WHERE \"GenreId\" = ?;", genreId);
    }

    /**
     * Method to delete all genres from the database.
     */
    public void deleteGenres() {
        log.debug("delete all genres");
        jdbc.update("DELETE  * FROM \"Genre\";");
    }


    private static final RowMapper<Genre> genreMapper = new RowMapper<Genre>() {
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            Genre genre = new Genre(rs.getInt("GenreId"), rs.getString("Name"));
            return genre;
        }
    };

    private static final RowMapper<Integer> maxIdMapper = new RowMapper<Integer>() {
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt(1);
            return id;
        }
    };
}
