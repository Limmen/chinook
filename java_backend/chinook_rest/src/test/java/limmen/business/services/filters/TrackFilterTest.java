package limmen.business.services.filters;

import limmen.integration.entities.Track;
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
public class TrackFilterTest {

    private TrackFilter trackFilter;
    private List<Track> tracks;

    @Mock
    Track trackOne;
    @Mock
    Track trackTwo;
    @Mock
    Track trackThree;
    @Mock
    Track trackFour;
    @Mock
    Track trackFive;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        trackFilter = new TrackFilter();
        tracks = new ArrayList<>();
        tracks.add(trackOne);
        tracks.add(trackTwo);
        tracks.add(trackThree);
        tracks.add(trackFour);
        tracks.add(trackFive);
        when(trackOne.getTrackId()).thenReturn(1);
        when(trackTwo.getTrackId()).thenReturn(2);
        when(trackThree.getTrackId()).thenReturn(3);
        when(trackFour.getTrackId()).thenReturn(4);
        when(trackFive.getTrackId()).thenReturn(5);
    }

    /**
     * Test of the filter method
     */
    @Test
    public void testFilter() {
        trackFilter.setTrackId("1");
        assertEquals("Asserting filter tracks", trackOne, trackFilter.filter(tracks).get(0));
        trackFilter.setTrackId("2");
        assertNotEquals("Asserting filter tracks", trackOne, trackFilter.filter(tracks).get(0));
        assertEquals("Asserting filter tracks", trackTwo, trackFilter.filter(tracks).get(0));
    }

    /**
     * Test of the sort method
     */
    @Test
    public void testSort() {
        trackFilter.setSort("+trackId");
        assertEquals("Asserting filter tracks", trackOne, trackFilter.sort(tracks).get(0));
        assertEquals("Asserting filter tracks", trackFive, trackFilter.sort(tracks).get(4));
        trackFilter.setSort("-trackId");
        assertEquals("Asserting filter tracks", trackFive, trackFilter.sort(tracks).get(0));
        assertEquals("Asserting filter tracks", trackFour, trackFilter.sort(tracks).get(1));
    }
}