package limmen.business.services.filters;

import limmen.integration.entities.Playlist;
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
public class PlaylistFilterTest {

    private PlaylistFilter playlistFilter;
    private List<Playlist> playlists;

    @Mock
    Playlist playlistOne;
    @Mock
    Playlist playlistTwo;
    @Mock
    Playlist playlistThree;
    @Mock
    Playlist playlistFour;
    @Mock
    Playlist playlistFive;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        playlistFilter = new PlaylistFilter();
        playlists = new ArrayList<>();
        playlists.add(playlistOne);
        playlists.add(playlistTwo);
        playlists.add(playlistThree);
        playlists.add(playlistFour);
        playlists.add(playlistFive);
        when(playlistOne.getPlaylistId()).thenReturn(1);
        when(playlistTwo.getPlaylistId()).thenReturn(2);
        when(playlistThree.getPlaylistId()).thenReturn(3);
        when(playlistFour.getPlaylistId()).thenReturn(4);
        when(playlistFive.getPlaylistId()).thenReturn(5);
    }

    /**
     * Test of the filter method
     */
    @Test
    public void testFilter() {
        playlistFilter.setPlaylistId("1");
        assertEquals("Asserting filter playlists", playlistOne, playlistFilter.filter(playlists).get(0));
        playlistFilter.setPlaylistId("2");
        assertNotEquals("Asserting filter playlists", playlistOne, playlistFilter.filter(playlists).get(0));
        assertEquals("Asserting filter playlists", playlistTwo, playlistFilter.filter(playlists).get(0));
    }

    /**
     * Test of the sort method
     */
    @Test
    public void testSort() {
        playlistFilter.setSort("+playlistId");
        assertEquals("Asserting filter playlists", playlistOne, playlistFilter.sort(playlists).get(0));
        assertEquals("Asserting filter playlists", playlistFive, playlistFilter.sort(playlists).get(4));
        playlistFilter.setSort("-playlistId");
        assertEquals("Asserting filter playlists", playlistFive, playlistFilter.sort(playlists).get(0));
        assertEquals("Asserting filter playlists", playlistFour, playlistFilter.sort(playlists).get(1));
    }
}