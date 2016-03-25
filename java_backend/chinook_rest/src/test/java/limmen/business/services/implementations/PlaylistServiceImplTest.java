package limmen.business.services.implementations;

import limmen.business.services.PlaylistService;
import limmen.integration.entities.Playlist;
import limmen.integration.repositories.PlaylistRepository;
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
 * Unit test-suite for the playlist service implementation
 *
 * @author Kim Hammar on 2016-03-24.
 */
@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceImplTest {

    @Mock
    private PlaylistRepository playlistRepository;
    private PlaylistService playlistService;
    @Mock
    private Playlist playlistOne;
    @Mock
    private Playlist playlistTwo;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        playlistService = new PlaylistServiceImpl((playlistRepository));
    }

    /**
     * Test for the getAllPlaylists method
     */
    @Test
    public void testGetAllPlaylists() {
        final List<Playlist> databaseList = new ArrayList();
        when(playlistRepository.getAllPlaylists()).thenReturn(databaseList);
        List<Playlist> returnedList = playlistService.getAllPlaylists();
        assertEquals("Asserting getAllPlaylists", databaseList, returnedList);
    }

    /**
     * Test of the getPlaylist method
     */
    @Test
    public void testGetPlaylist() {
        when(playlistRepository.getPlaylist(1)).thenReturn(playlistOne);
        when(playlistRepository.getPlaylist(2)).thenReturn(playlistTwo);
        assertEquals("Asserting getPlaylist", playlistOne, playlistService.getPlaylist(1));
        assertEquals("Asserting getPlaylist", playlistTwo, playlistService.getPlaylist(2));
        assertNotEquals("Asserting getPlaylist", playlistOne, playlistService.getPlaylist(2));
        assertNotEquals("Asserting getPlaylist", playlistTwo, playlistService.getPlaylist(1));
    }
}