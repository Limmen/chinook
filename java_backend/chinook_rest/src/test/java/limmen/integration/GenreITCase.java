package limmen.integration;

import limmen.ChinookRestApplication;
import limmen.business.representations.array_representations.GenresArrayRepresentation;
import limmen.business.representations.entity_representation.GenreRepresentation;
import limmen.integration.entities.Genre;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Kim Hammar on 2016-03-24.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChinookRestApplication.class)
@WebAppConfiguration
@IntegrationTest
public class GenreITCase {
    private final String BASE_URL = "http://localhost:7777/resources/genres";
    private JdbcTemplate jdbc;
    private RestTemplate rest;
    private List<Genre> genres;
    @Autowired
    DataSource dataSource;

    @Before
    public void setup() {
        rest = new RestTemplate();
        jdbc = new JdbcTemplate(dataSource);
        genres = jdbc.query("SELECT * FROM \"Genre\";", genreMapper);
    }

    @Test
    public void getGenreTest() {
        if (genres.size() > 0) {
            GenreRepresentation expectedGenreRepresenation =
                    new GenreRepresentation(genres.get(0));
            ResponseEntity<GenreRepresentation> responseEntity = rest.getForEntity(BASE_URL + "/" +
                    expectedGenreRepresenation.getGenre().getGenreId(), GenreRepresentation.class, Collections.EMPTY_MAP);
            assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals("Asserting entity", expectedGenreRepresenation, responseEntity.getBody());
        }
    }

    @Test
    public void getGenres() {
        ResponseEntity<GenresArrayRepresentation> responseEntity = rest.getForEntity(BASE_URL, GenresArrayRepresentation.class, Collections.EMPTY_MAP);
        assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Asserting array size", genres.size(), responseEntity.getBody().getGenres().size());
    }

    private static final RowMapper<Genre> genreMapper = new RowMapper<Genre>() {
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            Genre genre = new Genre(rs.getInt("GenreId"), rs.getString("Name"));
            return genre;
        }
    };
}
