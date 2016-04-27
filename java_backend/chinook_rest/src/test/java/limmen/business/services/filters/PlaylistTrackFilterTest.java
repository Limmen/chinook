package limmen.business.services.filters;

import limmen.integration.entities.PlaylistTrack;
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
public class PlaylistTrackFilterTest {

    private PlaylistTrackFilter playlistTrackFilter;
    private List<PlaylistTrack> playlistTracks;

    @Mock
    PlaylistTrack playlistTrackOne;
    @Mock
    PlaylistTrack playlistTrackTwo;
    @Mock
    PlaylistTrack playlistTrackThree;
    @Mock
    PlaylistTrack playlistTrackFour;
    @Mock
    PlaylistTrack playlistTrackFive;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        playlistTrackFilter = new PlaylistTrackFilter();
        playlistTracks = new ArrayList<>();
        playlistTracks.add(playlistTrackOne);
        playlistTracks.add(playlistTrackTwo);
        playlistTracks.add(playlistTrackThree);
        playlistTracks.add(playlistTrackFour);
        playlistTracks.add(playlistTrackFive);
        when(playlistTrackOne.getTrackId()).thenReturn(1);
        when(playlistTrackTwo.getTrackId()).thenReturn(2);
        when(playlistTrackThree.getTrackId()).thenReturn(3);
        when(playlistTrackFour.getTrackId()).thenReturn(4);
        when(playlistTrackFive.getTrackId()).thenReturn(5);
    }

    /**
     * Test of the filter method
     */
    @Test
    public void testFilter() {
        playlistTrackFilter.setTrackId("1");
        assertEquals("Asserting filter playlistTracks", playlistTrackOne, playlistTrackFilter.filter(playlistTracks).get(0));
        playlistTrackFilter.setTrackId("2");
        assertNotEquals("Asserting filter playlistTracks", playlistTrackOne, playlistTrackFilter.filter(playlistTracks).get(0));
        assertEquals("Asserting filter playlistTracks", playlistTrackTwo, playlistTrackFilter.filter(playlistTracks).get(0));
    }

    /**
     * Test of the sort method
     */
    @Test
    public void testSort() {
        playlistTrackFilter.setSort("+trackId");
        assertEquals("Asserting filter playlistTracks", playlistTrackOne, playlistTrackFilter.sort(playlistTracks).get(0));
        assertEquals("Asserting filter playlistTracks", playlistTrackFive, playlistTrackFilter.sort(playlistTracks).get(4));
        playlistTrackFilter.setSort("-trackId");
        assertEquals("Asserting filter playlistTracks", playlistTrackFive, playlistTrackFilter.sort(playlistTracks).get(0));
        assertEquals("Asserting filter playlistTracks", playlistTrackFour, playlistTrackFilter.sort(playlistTracks).get(1));
    }
}