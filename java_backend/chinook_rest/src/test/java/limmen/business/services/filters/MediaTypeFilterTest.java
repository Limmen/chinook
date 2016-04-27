package limmen.business.services.filters;

import limmen.integration.entities.MediaTypeEntity;
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
public class MediaTypeFilterTest {

    private MediaTypeFilter mediaTypeFilter;
    private List<MediaTypeEntity> mediaTypes;

    @Mock
    MediaTypeEntity mediaTypeOne;
    @Mock
    MediaTypeEntity mediaTypeTwo;
    @Mock
    MediaTypeEntity mediaTypeThree;
    @Mock
    MediaTypeEntity mediaTypeFour;
    @Mock
    MediaTypeEntity mediaTypeFive;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        mediaTypeFilter = new MediaTypeFilter();
        mediaTypes = new ArrayList<>();
        mediaTypes.add(mediaTypeOne);
        mediaTypes.add(mediaTypeTwo);
        mediaTypes.add(mediaTypeThree);
        mediaTypes.add(mediaTypeFour);
        mediaTypes.add(mediaTypeFive);
        when(mediaTypeOne.getMediaTypeId()).thenReturn(1);
        when(mediaTypeTwo.getMediaTypeId()).thenReturn(2);
        when(mediaTypeThree.getMediaTypeId()).thenReturn(3);
        when(mediaTypeFour.getMediaTypeId()).thenReturn(4);
        when(mediaTypeFive.getMediaTypeId()).thenReturn(5);
    }

    /**
     * Test of the filter method
     */
    @Test
    public void testFilter() {
        mediaTypeFilter.setMediaTypeId("1");
        assertEquals("Asserting filter mediaTypes", mediaTypeOne, mediaTypeFilter.filter(mediaTypes).get(0));
        mediaTypeFilter.setMediaTypeId("2");
        assertNotEquals("Asserting filter mediaTypes", mediaTypeOne, mediaTypeFilter.filter(mediaTypes).get(0));
        assertEquals("Asserting filter mediaTypes", mediaTypeTwo, mediaTypeFilter.filter(mediaTypes).get(0));
    }

    /**
     * Test of the sort method
     */
    @Test
    public void testSort() {
        mediaTypeFilter.setSort("+mediaTypeId");
        assertEquals("Asserting filter mediaTypes", mediaTypeOne, mediaTypeFilter.sort(mediaTypes).get(0));
        assertEquals("Asserting filter mediaTypes", mediaTypeFive, mediaTypeFilter.sort(mediaTypes).get(4));
        mediaTypeFilter.setSort("-mediaTypeId");
        assertEquals("Asserting filter mediaTypes", mediaTypeFive, mediaTypeFilter.sort(mediaTypes).get(0));
        assertEquals("Asserting filter mediaTypes", mediaTypeFour, mediaTypeFilter.sort(mediaTypes).get(1));
    }
}