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

    @Before
    public void setUp() throws Exception {
        playlistTrackService = new PlaylistTrackServiceImpl((playlistTrackRepository));
    }

    @Test
    public void testGetAllPlaylistTracks() throws Exception {
        final List<PlaylistTrack> databaseList = new ArrayList();
        when(playlistTrackRepository.getAllPlaylistTracks()).thenReturn(databaseList);
        List<PlaylistTrack> returnedList = playlistTrackService.getAllPlaylistTracks();
        assertEquals("Asserting getAllPlaylistTracks", databaseList, returnedList);
    }

    @Test
    public void testGetPlaylistTrack() throws Exception {
        when(playlistTrackRepository.getPlaylistTrack(1, 1)).thenReturn(playlistTrackOne);
        when(playlistTrackRepository.getPlaylistTrack(2, 2)).thenReturn(playlistTrackTwo);
        assertEquals("Asserting getPlaylistTrack", playlistTrackOne, playlistTrackService.getPlaylistTrack(1, 1));
        assertEquals("Asserting getPlaylistTrack", playlistTrackTwo, playlistTrackService.getPlaylistTrack(2, 2));
        assertNotEquals("Asserting getPlaylistTrack", playlistTrackOne, playlistTrackService.getPlaylistTrack(2, 2));
        assertNotEquals("Asserting getPlaylistTrack", playlistTrackTwo, playlistTrackService.getPlaylistTrack(1, 1));
    }
}