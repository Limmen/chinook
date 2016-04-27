package limmen.integration;

import limmen.ChinookRestApplication;
import limmen.business.representations.array_representations.ArtistsArrayRepresentation;
import limmen.business.representations.entity_representation.ArtistRepresentation;
import limmen.integration.entities.Artist;
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
 * Integration test-suite for the artist REST-resource
 *
 * @author Kim Hammar on 2016-03-24.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChinookRestApplication.class)
@WebAppConfiguration
@IntegrationTest
public class ArtistITCase {
    private final String BASE_URL = "http://localhost:7777/resources/artists";
    private JdbcTemplate jdbc;
    private RestTemplate rest;
    private List<Artist> artists;
    @Autowired
    DataSource dataSource;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setup() {
        rest = new RestTemplate();
        jdbc = new JdbcTemplate(dataSource);
        artists = jdbc.query("SELECT * FROM \"Artist\";", artistMapper);
    }

    /**
     * test of a singular artist resource
     */
    @Test
    public void getArtistTest() {
        if (artists.size() > 0) {
            ArtistRepresentation expectedArtistRepresenation = new ArtistRepresentation(artists.get(0));
            ResponseEntity<ArtistRepresentation> responseEntity = rest.getForEntity(BASE_URL + "/" +
                    expectedArtistRepresenation.getArtist().getArtistId(), ArtistRepresentation.class, Collections.EMPTY_MAP);
            assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals("Asserting entity", expectedArtistRepresenation, responseEntity.getBody());
        }
    }

    /**
     * test of artists resource
     */
    @Test
    public void getArtists() {
        ResponseEntity<ArtistsArrayRepresentation> responseEntity = rest.getForEntity(BASE_URL, ArtistsArrayRepresentation.class, Collections.EMPTY_MAP);
        assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Asserting array size", artists.size(), responseEntity.getBody().getArtists().size());
    }

    private static final RowMapper<Artist> artistMapper = new RowMapper<Artist>() {
        public Artist mapRow(ResultSet rs, int rowNum) throws SQLException {
            Artist artist = new Artist(rs.getInt("ArtistId"), rs.getString("Name"));
            return artist;
        }
    };
}
