package limmen.business.services.implementations;

import limmen.business.services.AlbumService;
import limmen.integration.entities.Album;
import limmen.integration.repositories.AlbumRepository;
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
 * Unit test-suite for the album service implementation
 *
 * @author Kim Hammar on 2016-03-24.
 */
@RunWith(MockitoJUnitRunner.class)
public class AlbumServiceImplTest {

    @Mock
    private AlbumRepository albumRepository;
    private AlbumService albumService;
    @Mock
    private Album albumOne;
    @Mock
    private Album albumTwo;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        albumService = new AlbumServiceImpl((albumRepository));
    }

    /**
     * Test for the getAllAlbums method
     */
    @Test
    public void testGetAllAlbums(){
        final List<Album> databaseList = new ArrayList();
        when(albumRepository.getAllAlbums()).thenReturn(databaseList);
        List<Album> returnedList = albumService.getAllAlbums();
        assertEquals("Asserting getAllAlbums", databaseList, returnedList);
    }

    /**
     * Test of the getAlbum method
     */
    @Test
    public void testGetAlbum() {
        when(albumRepository.getAlbum(1)).thenReturn(albumOne);
        when(albumRepository.getAlbum(2)).thenReturn(albumTwo);
        assertEquals("Asserting getAlbum", albumOne, albumService.getAlbum(1));
        assertEquals("Asserting getAlbum", albumTwo, albumService.getAlbum(2));
        assertNotEquals("Asserting getAlbum", albumOne, albumService.getAlbum(2));
        assertNotEquals("Asserting getAlbum", albumTwo, albumService.getAlbum(1));
    }
}