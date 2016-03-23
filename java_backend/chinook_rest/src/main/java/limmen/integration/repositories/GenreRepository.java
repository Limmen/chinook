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
 * @author Kim Hammar on 2016-03-22.
 */
@Repository
public class GenreRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbc;

    public Genre getGenre(int genreId) {
        return jdbc.queryForObject("SELECT * FROM \"Genre\" WHERE \"GenreId\"=?", genreMapper, genreId);
    }

    public List<Genre> getAllGenres(){
        log.info("getAllGenres from Database");
        return jdbc.query("SELECT * FROM \"Genre\";", genreMapper);
    }

    private static final RowMapper<Genre> genreMapper = new RowMapper<Genre>() {
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            Genre genre = new Genre(rs.getInt("GenreId"), rs.getString("Name"));
            return genre;
        }
    };
}
