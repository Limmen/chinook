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

    @Before
    public void setUp() throws Exception {
        mediaTypeService = new MediaTypeServiceImpl((mediaTypeRepository));
    }

    @Test
    public void testGetAllMediaTypes() throws Exception {
        final List<MediaTypeEntity> databaseList = new ArrayList();
        when(mediaTypeRepository.getAllMediaTypes()).thenReturn(databaseList);
        List<MediaTypeEntity> returnedList = mediaTypeService.getAllMediaTypes();
        assertEquals("Asserting getAllMediaTypes", databaseList, returnedList);
    }

    @Test
    public void testGetMediaType() throws Exception {
        when(mediaTypeRepository.getMediaType(1)).thenReturn(mediaTypeOne);
        when(mediaTypeRepository.getMediaType(2)).thenReturn(mediaTypeTwo);
        assertEquals("Asserting getMediaType", mediaTypeOne, mediaTypeService.getMediaType(1));
        assertEquals("Asserting getMediaType", mediaTypeTwo, mediaTypeService.getMediaType(2));
        assertNotEquals("Asserting getMediaType", mediaTypeOne, mediaTypeService.getMediaType(2));
        assertNotEquals("Asserting getMediaType", mediaTypeTwo, mediaTypeService.getMediaType(1));
    }
}