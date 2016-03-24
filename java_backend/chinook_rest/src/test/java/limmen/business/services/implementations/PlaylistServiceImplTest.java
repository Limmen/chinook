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

    @Before
    public void setUp() throws Exception {
        playlistService = new PlaylistServiceImpl((playlistRepository));
    }

    @Test
    public void testGetAllPlaylists() throws Exception {
        final List<Playlist> databaseList = new ArrayList();
        when(playlistRepository.getAllPlaylists()).thenReturn(databaseList);
        List<Playlist> returnedList = playlistService.getAllPlaylists();
        assertEquals("Asserting getAllPlaylists", databaseList, returnedList);
    }

    @Test
    public void testGetPlaylist() throws Exception {
        when(playlistRepository.getPlaylist(1)).thenReturn(playlistOne);
        when(playlistRepository.getPlaylist(2)).thenReturn(playlistTwo);
        assertEquals("Asserting getPlaylist", playlistOne, playlistService.getPlaylist(1));
        assertEquals("Asserting getPlaylist", playlistTwo, playlistService.getPlaylist(2));
        assertNotEquals("Asserting getPlaylist", playlistOne, playlistService.getPlaylist(2));
        assertNotEquals("Asserting getPlaylist", playlistTwo, playlistService.getPlaylist(1));
    }
}