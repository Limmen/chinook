package limmen.business.services.filters;

import limmen.integration.entities.Artist;
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
 * @author Kim Hammar on 2016-04-27.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArtistFilterTest {

    private ArtistFilter artistFilter;
    private List<Artist> artists;

    @Mock
    Artist artistOne;
    @Mock
    Artist artistTwo;
    @Mock
    Artist artistThree;
    @Mock
    Artist artistFour;
    @Mock
    Artist artistFive;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        artistFilter = new ArtistFilter();
        artists = new ArrayList<>();
        artists.add(artistOne);
        artists.add(artistTwo);
        artists.add(artistThree);
        artists.add(artistFour);
        artists.add(artistFive);
        when(artistOne.getArtistId()).thenReturn(1);
        when(artistTwo.getArtistId()).thenReturn(2);
        when(artistThree.getArtistId()).thenReturn(3);
        when(artistFour.getArtistId()).thenReturn(4);
        when(artistFive.getArtistId()).thenReturn(5);
    }

    /**
     * Test of the filter method
     */
    @Test
    public void testFilter() {
        artistFilter.setArtistId("1");
        assertEquals("Asserting filter artists", artistOne, artistFilter.filter(artists).get(0));
        artistFilter.setArtistId("2");
        assertNotEquals("Asserting filter artists", artistOne, artistFilter.filter(artists).get(0));
        assertEquals("Asserting filter artists", artistTwo, artistFilter.filter(artists).get(0));
    }

    /**
     * Test of the sort method
     */
    @Test
    public void testSort() {
        artistFilter.setSort("+artistId");
        assertEquals("Asserting filter artists", artistOne, artistFilter.sort(artists).get(0));
        assertEquals("Asserting filter artists", artistFive, artistFilter.sort(artists).get(4));
        artistFilter.setSort("-artistId");
        assertEquals("Asserting filter artists", artistFive, artistFilter.sort(artists).get(0));
        assertEquals("Asserting filter artists", artistFour, artistFilter.sort(artists).get(1));
    }
}