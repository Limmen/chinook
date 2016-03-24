package limmen.integration;

import limmen.ChinookRestApplication;
import limmen.builders.ArtistBuilder;
import limmen.business.representations.entity_representation.ArtistRepresentation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * @author Kim Hammar on 2016-03-24.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChinookRestApplication.class)
@WebAppConfiguration
@IntegrationTest
public class ArtistITCase {
    final String BASE_URL = "http://localhost:7777/resources/artists";

    @Test
    public void getArtistTest() {
        ArtistRepresentation expectedArtistRepresenation = new ArtistRepresentation(ArtistBuilder.aArtist().withId(1).withName("AC/DC").build());
        RestTemplate rest = new TestRestTemplate();
        ResponseEntity<ArtistRepresentation> responseEntity = rest.getForEntity(BASE_URL + "/" +
                expectedArtistRepresenation.getArtist().getArtistId(), ArtistRepresentation.class, Collections.EMPTY_MAP);
        assertEquals("Asserting status code", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Asserting entity", expectedArtistRepresenation, responseEntity.getBody());
    }

}
