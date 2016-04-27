package limmen.integration;

import limmen.ChinookRestApplication;
import limmen.business.representations.array_representations.PlaylistTracksArrayRepresentation;
import limmen.business.representations.entity_representation.PlaylistTrackRepresentation;
import limmen.integration.entities.PlaylistTrack;
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
 * Integration test-suite for the playlisttrack REST-resource
 *
 * @author Kim Hammar on 2016-03-24.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChinookRestApplication.class)
@WebAppConfiguration
@IntegrationTest
public class PlaylistTrackITCase {
    private final String BASE_URL = "http://localhost:7777/resources/playlisttracks";
    private JdbcTemplate jdbc;
    private RestTemplate rest;
    private List<PlaylistTrack> playlistTracks;
    @Autowired
    DataSource dataSource;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setup() {
        rest = new RestTemplate();
        jdbc = new JdbcTemplate(dataSource);
        playlistTracks = jdbc.query("SELECT * FROM \"PlaylistTrack\";", playlistTrackMapper);
    }

    /**
     * test of a singular playlisttrack resource
     */
    @Test
    public void getPlaylistTrackTest() {
        if (playlistTracks.size() > 0) {
            PlaylistTrackRepresentation expectedPlaylistTrackRepresenation = new PlaylistTrackRepresentation(playlistTracks.get(0));
            ResponseEntity<PlaylistTrackRepresentation> responseEntity = rest.getForEntity(BASE_URL + "/" +
                    expectedPlaylistTrackRepresenation.getPlaylistTrack().getTrackId() + "/" +
                    expectedPlaylistTrackRepresenation.getPlaylistTrack().getPlaylistId(), PlaylistTrackRepresentation.class, Collections.EMPTY_MAP);
            assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals("Asserting entity", expectedPlaylistTrackRepresenation, responseEntity.getBody());
        }
    }

    /**
     * test of playlisttracks resource
     */
    @Test
    public void getPlaylistTracks() {
        ResponseEntity<PlaylistTracksArrayRepresentation> responseEntity = rest.getForEntity(BASE_URL, PlaylistTracksArrayRepresentation.class, Collections.EMPTY_MAP);
        assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Asserting array size", playlistTracks.size(), responseEntity.getBody().getPlaylistTracks().size());
    }

    private static final RowMapper<PlaylistTrack> playlistTrackMapper = new RowMapper<PlaylistTrack>() {
        public PlaylistTrack mapRow(ResultSet rs, int rowNum) throws SQLException {
            PlaylistTrack playlistTrack = new PlaylistTrack(rs.getInt("TrackId"), rs.getInt("PlaylistId"));
            return playlistTrack;
        }
    };
}
