package limmen.integration;

import limmen.ChinookRestApplication;
import limmen.business.representations.array_representations.MediaTypesArrayRepresentation;
import limmen.business.representations.entity_representation.MediaTypeRepresentation;
import limmen.integration.entities.MediaTypeEntity;
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
 * Integration test-suite for the mediatype REST-resource
 *
 * @author Kim Hammar on 2016-03-24.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChinookRestApplication.class)
@WebAppConfiguration
@IntegrationTest
public class MediaTypeITCase {
    private final String BASE_URL = "http://localhost:7777/resources/mediatypes";
    private JdbcTemplate jdbc;
    private RestTemplate rest;
    private List<MediaTypeEntity> mediaTypes;
    @Autowired
    DataSource dataSource;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setup() {
        rest = new RestTemplate();
        jdbc = new JdbcTemplate(dataSource);
        mediaTypes = jdbc.query("SELECT * FROM \"MediaType\";", mediaTypeMapper);
    }

    /**
     * test of a singular mediatype resource
     */
    @Test
    public void getMediaTypeTest() {
        if (mediaTypes.size() > 0) {
            MediaTypeRepresentation expectedMediaTypeRepresenation = new MediaTypeRepresentation(mediaTypes.get(0));
            ResponseEntity<MediaTypeRepresentation> responseEntity = rest.getForEntity(BASE_URL + "/" +
                    expectedMediaTypeRepresenation.getMediaTypeEntity().getMediaTypeId(), MediaTypeRepresentation.class, Collections.EMPTY_MAP);
            assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals("Asserting entity", expectedMediaTypeRepresenation, responseEntity.getBody());
        }
    }

    /**
     * test of mediatypes resource
     */
    @Test
    public void getMediaTypes() {
        ResponseEntity<MediaTypesArrayRepresentation> responseEntity = rest.getForEntity(BASE_URL, MediaTypesArrayRepresentation.class, Collections.EMPTY_MAP);
        assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Asserting array size", mediaTypes.size(), responseEntity.getBody().getMediaTypes().size());
    }

    private static final RowMapper<MediaTypeEntity> mediaTypeMapper = new RowMapper<MediaTypeEntity>() {
        public MediaTypeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            MediaTypeEntity mediaTypeEntity = new MediaTypeEntity(rs.getInt("MediaTypeId"), rs.getString("Name"));
            return mediaTypeEntity;
        }
    };
}
