package limmen.integration;

import limmen.ChinookRestApplication;
import limmen.business.representations.array_representations.PlaylistsArrayRepresentation;
import limmen.business.representations.entity_representation.PlaylistRepresentation;
import limmen.integration.entities.Playlist;
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
public class PlaylistITCase {
    private final String BASE_URL = "http://localhost:7777/resources/playlists";
    private JdbcTemplate jdbc;
    private RestTemplate rest;
    private List<Playlist> playlists;
    @Autowired
    DataSource dataSource;

    @Before
    public void setup() {
        rest = new RestTemplate();
        jdbc = new JdbcTemplate(dataSource);
        playlists = jdbc.query("SELECT * FROM \"Playlist\";", playlistMapper);
    }

    @Test
    public void getPlaylistTest() {
        if (playlists.size() > 0) {
            PlaylistRepresentation expectedPlaylistRepresenation = new PlaylistRepresentation(playlists.get(0));
            ResponseEntity<PlaylistRepresentation> responseEntity = rest.getForEntity(BASE_URL + "/" +
                    expectedPlaylistRepresenation.getPlaylist().getPlaylistId(), PlaylistRepresentation.class, Collections.EMPTY_MAP);
            assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals("Asserting entity", expectedPlaylistRepresenation, responseEntity.getBody());
        }
    }

    @Test
    public void getPlaylists() {
        ResponseEntity<PlaylistsArrayRepresentation> responseEntity = rest.getForEntity(BASE_URL, PlaylistsArrayRepresentation.class, Collections.EMPTY_MAP);
        assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Asserting array size", playlists.size(), responseEntity.getBody().getPlaylists().size());
    }

    private static final RowMapper<Playlist> playlistMapper = new RowMapper<Playlist>() {
        public Playlist mapRow(ResultSet rs, int rowNum) throws SQLException {
            Playlist playlist = new Playlist(rs.getInt("PlaylistId"), rs.getString("Name"));
            return playlist;
        }
    };
}
