package limmen.business.services.implementations;

import limmen.business.services.PlaylistTrackService;
import limmen.integration.entities.PlaylistTrack;
import limmen.integration.repositories.PlaylistTrackRepository;
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
 * Unit test-suite for the playlisttrack service implementation
 *
 * @author Kim Hammar on 2016-03-24.
 */
@RunWith(MockitoJUnitRunner.class)
public class PlaylistTrackServiceImplTest {

    @Mock
    private PlaylistTrackRepository playlistTrackRepository;
    private PlaylistTrackService playlistTrackService;
    @Mock
    private PlaylistTrack playlistTrackOne;
    @Mock
    private PlaylistTrack playlistTrackTwo;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        playlistTrackService = new PlaylistTrackServiceImpl((playlistTrackRepository));
    }

    /**
     * Test for the getAllPlaylistTracks method
     */
    @Test
    public void testGetAllPlaylistTracks() {
        final List<PlaylistTrack> databaseList = new ArrayList();
        when(playlistTrackRepository.getAllPlaylistTracks()).thenReturn(databaseList);
        List<PlaylistTrack> returnedList = playlistTrackService.getAllPlaylistTracks();
        assertEquals("Asserting getAllPlaylistTracks", databaseList, returnedList);
    }

    /**
     * Test of the getPlaylistTrack method
     */
    @Test
    public void testGetPlaylistTrack() {
        when(playlistTrackRepository.getPlaylistTrack(1, 1)).thenReturn(playlistTrackOne);
        when(playlistTrackRepository.getPlaylistTrack(2, 2)).thenReturn(playlistTrackTwo);
        assertEquals("Asserting getPlaylistTrack", playlistTrackOne, playlistTrackService.getPlaylistTrack(1, 1));
        assertEquals("Asserting getPlaylistTrack", playlistTrackTwo, playlistTrackService.getPlaylistTrack(2, 2));
        assertNotEquals("Asserting getPlaylistTrack", playlistTrackOne, playlistTrackService.getPlaylistTrack(2, 2));
        assertNotEquals("Asserting getPlaylistTrack", playlistTrackTwo, playlistTrackService.getPlaylistTrack(1, 1));
    }

    /**
     * Test of the createNewPlaylistTrack method
     */
    @Test
    public void testCreateNewPlaylistTrack() {
        when(playlistTrackRepository.createNewPlaylistTrack(playlistTrackOne)).thenReturn(playlistTrackOne);
        when(playlistTrackRepository.createNewPlaylistTrack(playlistTrackTwo)).thenReturn(playlistTrackTwo);
        assertEquals("Asserting getPlaylistTrack", playlistTrackOne, playlistTrackService.createNewPlaylistTrack(playlistTrackOne));
        assertEquals("Asserting getPlaylistTrack", playlistTrackTwo, playlistTrackService.createNewPlaylistTrack(playlistTrackTwo));
        assertNotEquals("Asserting getPlaylistTrack", playlistTrackOne, playlistTrackService.createNewPlaylistTrack(playlistTrackTwo));
        assertNotEquals("Asserting getPlaylistTrack", playlistTrackTwo, playlistTrackService.createNewPlaylistTrack(playlistTrackOne));
    }

    /**
     * Test of the updatePlaylistTrack method
     */
    @Test
    public void testUpdatePlaylistTrack() {
        when(playlistTrackRepository.updatePlaylistTrack(playlistTrackOne)).thenReturn(playlistTrackOne);
        when(playlistTrackRepository.updatePlaylistTrack(playlistTrackTwo)).thenReturn(playlistTrackTwo);
        assertEquals("Asserting getPlaylistTrack", playlistTrackOne, playlistTrackService.updatePlaylistTrack(playlistTrackOne));
        assertEquals("Asserting getPlaylistTrack", playlistTrackTwo, playlistTrackService.updatePlaylistTrack(playlistTrackTwo));
        assertNotEquals("Asserting getPlaylistTrack", playlistTrackOne, playlistTrackService.updatePlaylistTrack(playlistTrackTwo));
        assertNotEquals("Asserting getPlaylistTrack", playlistTrackTwo, playlistTrackService.updatePlaylistTrack(playlistTrackOne));
    }
}