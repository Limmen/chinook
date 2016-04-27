package limmen.business.services.filters;

import limmen.integration.entities.Album;
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
public class AlbumFilterTest {

    private AlbumFilter albumFilter;
    private List<Album> albums;

    @Mock
    Album albumOne;
    @Mock
    Album albumTwo;
    @Mock
    Album albumThree;
    @Mock
    Album albumFour;
    @Mock
    Album albumFive;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        albumFilter = new AlbumFilter();
        albums = new ArrayList<>();
        albums.add(albumOne);
        albums.add(albumTwo);
        albums.add(albumThree);
        albums.add(albumFour);
        albums.add(albumFive);
        when(albumOne.getAlbumId()).thenReturn(1);
        when(albumTwo.getAlbumId()).thenReturn(2);
        when(albumThree.getAlbumId()).thenReturn(3);
        when(albumFour.getAlbumId()).thenReturn(4);
        when(albumFive.getAlbumId()).thenReturn(5);
    }

    /**
     * Test of the filter method
     */
    @Test
    public void testFilter() {
        albumFilter.setAlbumId("1");
        assertEquals("Asserting filter albums", albumOne, albumFilter.filter(albums).get(0));
        albumFilter.setAlbumId("2");
        assertNotEquals("Asserting filter albums", albumOne, albumFilter.filter(albums).get(0));
        assertEquals("Asserting filter albums", albumTwo, albumFilter.filter(albums).get(0));
    }

    /**
     * Test of the sort method
     */
    @Test
    public void testSort() {
        albumFilter.setSort("+albumId");
        assertEquals("Asserting filter albums", albumOne, albumFilter.sort(albums).get(0));
        assertEquals("Asserting filter albums", albumFive, albumFilter.sort(albums).get(4));
        albumFilter.setSort("-albumId");
        assertEquals("Asserting filter albums", albumFive, albumFilter.sort(albums).get(0));
        assertEquals("Asserting filter albums", albumFour, albumFilter.sort(albums).get(1));
    }
}