package limmen.business.services.implementations;

import limmen.business.services.TrackService;
import limmen.integration.entities.Track;
import limmen.integration.repositories.TrackRepository;
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
 *
 * Unit test-suite for the track service implementation
 *
 * @author Kim Hammar on 2016-03-24.
 */
@RunWith(MockitoJUnitRunner.class)
public class TrackServiceImplTest {

    @Mock
    private TrackRepository trackRepository;
    private TrackService trackService;
    @Mock
    private Track trackOne;
    @Mock
    private Track trackTwo;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        trackService = new TrackServiceImpl((trackRepository));
    }

    /**
     * Test for the getAllTracks method
     */
    @Test
    public void testGetAllTracks() {
        final List<Track> databaseList = new ArrayList();
        when(trackRepository.getAllTracks()).thenReturn(databaseList);
        List<Track> returnedList = trackService.getAllTracks();
        assertEquals("Asserting getAllTracks", databaseList, returnedList);
    }

    /**
     * Test of the getTrack method
     */
    @Test
    public void testGetTrack() {
        when(trackRepository.getTrack(1)).thenReturn(trackOne);
        when(trackRepository.getTrack(2)).thenReturn(trackTwo);
        assertEquals("Asserting getTrack", trackOne, trackService.getTrack(1));
        assertEquals("Asserting getTrack", trackTwo, trackService.getTrack(2));
        assertNotEquals("Asserting getTrack", trackOne, trackService.getTrack(2));
        assertNotEquals("Asserting getTrack", trackTwo, trackService.getTrack(1));
    }
}