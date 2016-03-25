package limmen.integration;

import limmen.ChinookRestApplication;
import limmen.business.representations.array_representations.AlbumsArrayRepresentation;
import limmen.business.representations.entity_representation.AlbumRepresentation;
import limmen.integration.entities.Album;
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
public class AlbumITCase {
    final String BASE_URL = "http://localhost:7777/resources/albums";
    private JdbcTemplate jdbc;
    private RestTemplate rest;
    @Autowired
    DataSource dataSource;

    @Before
    public void setup() {
        rest = new RestTemplate();
        jdbc = new JdbcTemplate(dataSource);
    }

    @Test
    public void getAlbumTest() {
        AlbumRepresentation expectedAlbumRepresenation =
                new AlbumRepresentation(jdbc.queryForObject("SELECT * FROM \"Album\" WHERE \"AlbumId\"=?", albumMapper, 1));
        ResponseEntity<AlbumRepresentation> responseEntity = rest.getForEntity(BASE_URL + "/" +
                expectedAlbumRepresenation.getAlbum().getAlbumId(), AlbumRepresentation.class, Collections.EMPTY_MAP);
        assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Asserting entity", expectedAlbumRepresenation, responseEntity.getBody());
    }

    @Test
    public void getAlbums(){
        List<Album> albums = jdbc.query("SELECT * FROM \"Album\";", albumMapper);
        ResponseEntity<AlbumsArrayRepresentation> responseEntity = rest.getForEntity(BASE_URL, AlbumsArrayRepresentation.class, Collections.EMPTY_MAP);
        assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Asserting array size", albums.size(), responseEntity.getBody().getAlbums().size());
    }

    private static final RowMapper<Album> albumMapper = new RowMapper<Album>() {
        public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
            Album album = new Album(rs.getInt("AlbumId"), rs.getString("Title"), rs.getInt("ArtistId"));
            return album;
        }
    };
}
