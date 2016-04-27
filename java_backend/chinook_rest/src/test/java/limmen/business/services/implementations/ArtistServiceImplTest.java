package limmen.business.services.implementations;

import limmen.business.services.ArtistService;
import limmen.integration.entities.Artist;
import limmen.integration.repositories.ArtistRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

/**
 * Unit test-suite for the artist service implementation
 *
 * @author Kim Hammar on 2016-03-24.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArtistServiceImplTest {

    @Mock
    private ArtistRepository artistRepository;
    private ArtistService artistService;
    @Mock
    private Artist artistOne;
    @Mock
    private Artist artistTwo;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        artistService = new ArtistServiceImpl((artistRepository));
    }

    /**
     * Test for the getAllArtists method
     */
    @Test
    public void testGetAllArtists(){
        final List<Artist> databaseList = new ArrayList();
        when(artistRepository.getAllArtists()).thenReturn(databaseList);
        List<Artist> returnedList = artistService.getAllArtists();
        assertEquals("Asserting getAllArtists", databaseList, returnedList);
    }

    /**
     * Test of the getArtist method
     */
    @Test
    public void testGetArtist() {
        when(artistRepository.getArtist(1)).thenReturn(artistOne);
        when(artistRepository.getArtist(2)).thenReturn(artistTwo);
        assertEquals("Asserting getArtist", artistOne, artistService.getArtist(1));
        assertEquals("Asserting getArtist", artistTwo, artistService.getArtist(2));
        assertNotEquals("Asserting getArtist", artistOne, artistService.getArtist(2));
        assertNotEquals("Asserting getArtist", artistTwo, artistService.getArtist(1));
    }

    /**
     * Test of the createNewArtist method
     */
    @Test
    public void testCreateNewArtist() {
        when(artistRepository.createNewArtist(artistOne)).thenReturn(artistOne);
        when(artistRepository.createNewArtist(artistTwo)).thenReturn(artistTwo);
        assertEquals("Asserting getArtist", artistOne, artistService.createNewArtist(artistOne));
        assertEquals("Asserting getArtist", artistTwo, artistService.createNewArtist(artistTwo));
        assertNotEquals("Asserting getArtist", artistOne, artistService.createNewArtist(artistTwo));
        assertNotEquals("Asserting getArtist", artistTwo, artistService.createNewArtist(artistOne));
    }

    /**
     * Test of the updateArtist method
     */
    @Test
    public void testUpdateArtist() {
        when(artistRepository.updateArtist(artistOne)).thenReturn(artistOne);
        when(artistRepository.updateArtist(artistTwo)).thenReturn(artistTwo);
        assertEquals("Asserting getArtist", artistOne, artistService.updateArtist(artistOne));
        assertEquals("Asserting getArtist", artistTwo, artistService.updateArtist(artistTwo));
        assertNotEquals("Asserting getArtist", artistOne, artistService.updateArtist(artistTwo));
        assertNotEquals("Asserting getArtist", artistTwo, artistService.updateArtist(artistOne));
    }

}