package limmen.integration;

import limmen.ChinookRestApplication;
import limmen.business.representations.array_representations.TracksArrayRepresentation;
import limmen.business.representations.entity_representation.TrackRepresentation;
import limmen.integration.entities.Track;
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
public class TrackITCase {
    private final String BASE_URL = "http://localhost:7777/resources/tracks";
    private JdbcTemplate jdbc;
    private RestTemplate rest;
    private List<Track> tracks;
    @Autowired
    DataSource dataSource;

    @Before
    public void setup() {
        rest = new RestTemplate();
        jdbc = new JdbcTemplate(dataSource);
        tracks = jdbc.query("SELECT * FROM \"Track\";", trackMapper);
    }

    @Test
    public void getTrackTest() {
        if (tracks.size() > 0) {
            TrackRepresentation expectedTrackRepresenation = new TrackRepresentation(tracks.get(0));
            ResponseEntity<TrackRepresentation> responseEntity = rest.getForEntity(BASE_URL + "/" +
                    expectedTrackRepresenation.getTrack().getTrackId(), TrackRepresentation.class, Collections.EMPTY_MAP);
            assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals("Asserting entity", expectedTrackRepresenation, responseEntity.getBody());
        }
    }

    @Test
    public void getTracks() {
        ResponseEntity<TracksArrayRepresentation> responseEntity = rest.getForEntity(BASE_URL, TracksArrayRepresentation.class, Collections.EMPTY_MAP);
        assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Asserting array size", tracks.size(), responseEntity.getBody().getTracks().size());
    }

    private static final RowMapper<Track> trackMapper = new RowMapper<Track>() {
        public Track mapRow(ResultSet rs, int rowNum) throws SQLException {
            Track track = new Track(rs.getInt("TrackId"), rs.getString("Name"), rs.getInt("AlbumId"),
                    rs.getInt("MediaTypeId"), rs.getInt("GenreId"), rs.getString("Composer"), rs.getInt("Milliseconds"),
                    rs.getInt("Bytes"), rs.getFloat("UnitPrice"));
            return track;
        }
    };
}
