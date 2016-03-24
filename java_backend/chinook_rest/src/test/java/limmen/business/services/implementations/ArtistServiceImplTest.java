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

    @Before
    public void setUp() throws Exception {
        artistService = new ArtistServiceImpl((artistRepository));
    }

    @Test
    public void testGetAllArtists() throws Exception {
        final List<Artist> databaseList = new ArrayList();
        when(artistRepository.getAllArtists()).thenReturn(databaseList);
        List<Artist> returnedList = artistService.getAllArtists();
        assertEquals("Asserting getAllArtists", databaseList, returnedList);
    }

    @Test
    public void testGetArtist() throws Exception {
        when(artistRepository.getArtist(1)).thenReturn(artistOne);
        when(artistRepository.getArtist(2)).thenReturn(artistTwo);
        assertEquals("Asserting getArtist", artistOne, artistService.getArtist(1));
        assertEquals("Asserting getArtist", artistTwo, artistService.getArtist(2));
        assertNotEquals("Asserting getArtist", artistOne, artistService.getArtist(2));
        assertNotEquals("Asserting getArtist", artistTwo, artistService.getArtist(1));
    }
}