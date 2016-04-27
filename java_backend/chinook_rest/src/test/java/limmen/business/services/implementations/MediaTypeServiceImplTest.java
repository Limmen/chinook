package limmen.business.services.implementations;

import limmen.business.services.MediaTypeService;
import limmen.integration.entities.MediaTypeEntity;
import limmen.integration.repositories.MediaTypeRepository;
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
 * Unit test-suite for the mediatype service implementation
 *
 * @author Kim Hammar on 2016-03-24.
 */
@RunWith(MockitoJUnitRunner.class)
public class MediaTypeServiceImplTest {

    @Mock
    private MediaTypeRepository mediaTypeRepository;
    private MediaTypeService mediaTypeService;
    @Mock
    private MediaTypeEntity mediaTypeOne;
    @Mock
    private MediaTypeEntity mediaTypeTwo;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        mediaTypeService = new MediaTypeServiceImpl((mediaTypeRepository));
    }

    /**
     * Test for the getAllMediaTypes method
     */
    @Test
    public void testGetAllMediaTypes() {
        final List<MediaTypeEntity> databaseList = new ArrayList();
        when(mediaTypeRepository.getAllMediaTypes()).thenReturn(databaseList);
        List<MediaTypeEntity> returnedList = mediaTypeService.getAllMediaTypes();
        assertEquals("Asserting getAllMediaTypes", databaseList, returnedList);
    }

    /**
     * Test of the getMediaType method
     */
    @Test
    public void testGetMediaType() {
        when(mediaTypeRepository.getMediaType(1)).thenReturn(mediaTypeOne);
        when(mediaTypeRepository.getMediaType(2)).thenReturn(mediaTypeTwo);
        assertEquals("Asserting getMediaType", mediaTypeOne, mediaTypeService.getMediaType(1));
        assertEquals("Asserting getMediaType", mediaTypeTwo, mediaTypeService.getMediaType(2));
        assertNotEquals("Asserting getMediaType", mediaTypeOne, mediaTypeService.getMediaType(2));
        assertNotEquals("Asserting getMediaType", mediaTypeTwo, mediaTypeService.getMediaType(1));
    }

    /**
     * Test of the createNewMediaType method
     */
    @Test
    public void testCreateNewMediaType() {
        when(mediaTypeRepository.createNewMediaType(mediaTypeOne)).thenReturn(mediaTypeOne);
        when(mediaTypeRepository.createNewMediaType(mediaTypeTwo)).thenReturn(mediaTypeTwo);
        assertEquals("Asserting createNewMediaType", mediaTypeOne, mediaTypeService.createNewMediaType(mediaTypeOne));
        assertEquals("Asserting createNewMediaType", mediaTypeTwo, mediaTypeService.createNewMediaType(mediaTypeTwo));
        assertNotEquals("Asserting createNewMediaType", mediaTypeOne, mediaTypeService.createNewMediaType(mediaTypeTwo));
        assertNotEquals("Asserting createNewMediaType", mediaTypeTwo, mediaTypeService.createNewMediaType(mediaTypeOne));
    }

    /**
     * Test of the updateMediaType method
     */
    @Test
    public void testUpdateMediaType() {
        when(mediaTypeRepository.updateMediaType(mediaTypeOne)).thenReturn(mediaTypeOne);
        when(mediaTypeRepository.updateMediaType(mediaTypeTwo)).thenReturn(mediaTypeTwo);
        assertEquals("Asserting updateMediaType", mediaTypeOne, mediaTypeService.updateMediaType(mediaTypeOne));
        assertEquals("Asserting updateMediaType", mediaTypeTwo, mediaTypeService.updateMediaType(mediaTypeTwo));
        assertNotEquals("Asserting updateMediaType", mediaTypeOne, mediaTypeService.updateMediaType(mediaTypeTwo));
        assertNotEquals("Asserting updateMediaType", mediaTypeTwo, mediaTypeService.updateMediaType(mediaTypeOne));
    }
}