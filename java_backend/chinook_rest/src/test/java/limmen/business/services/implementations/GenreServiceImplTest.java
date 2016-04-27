package limmen.business.services.implementations;

import limmen.business.services.GenreService;
import limmen.integration.entities.Genre;
import limmen.integration.repositories.GenreRepository;
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
 * Unit test-suite for the genre service implementation
 *
 * @author Kim Hammar on 2016-03-24.
 */
@RunWith(MockitoJUnitRunner.class)
public class GenreServiceImplTest {

    @Mock
    private GenreRepository genreRepository;
    private GenreService genreService;
    @Mock
    private Genre genreOne;
    @Mock
    private Genre genreTwo;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        genreService = new GenreServiceImpl((genreRepository));
    }

    /**
     * Test for the getAllGenres method
     */
    @Test
    public void testGetAllGenres() {
        final List<Genre> databaseList = new ArrayList();
        when(genreRepository.getAllGenres()).thenReturn(databaseList);
        List<Genre> returnedList = genreService.getAllGenres();
        assertEquals("Asserting getAllGenres", databaseList, returnedList);
    }

    /**
     * Test of the getGenre method
     */
    @Test
    public void testGetGenre() {
        when(genreRepository.getGenre(1)).thenReturn(genreOne);
        when(genreRepository.getGenre(2)).thenReturn(genreTwo);
        assertEquals("Asserting getGenre", genreOne, genreService.getGenre(1));
        assertEquals("Asserting getGenre", genreTwo, genreService.getGenre(2));
        assertNotEquals("Asserting getGenre", genreOne, genreService.getGenre(2));
        assertNotEquals("Asserting getGenre", genreTwo, genreService.getGenre(1));
    }

    /**
     * Test of the createNewGenre method
     */
    @Test
    public void testCreateNewGenre() {
        when(genreRepository.createNewGenre(genreOne)).thenReturn(genreOne);
        when(genreRepository.createNewGenre(genreTwo)).thenReturn(genreTwo);
        assertEquals("Asserting getGenre", genreOne, genreService.createNewGenre(genreOne));
        assertEquals("Asserting getGenre", genreTwo, genreService.createNewGenre(genreTwo));
        assertNotEquals("Asserting getGenre", genreOne, genreService.createNewGenre(genreTwo));
        assertNotEquals("Asserting getGenre", genreTwo, genreService.createNewGenre(genreOne));
    }

    /**
     * Test of the updateGenre method
     */
    @Test
    public void testUpdateGenre() {
        when(genreRepository.updateGenre(genreOne)).thenReturn(genreOne);
        when(genreRepository.updateGenre(genreTwo)).thenReturn(genreTwo);
        assertEquals("Asserting getGenre", genreOne, genreService.updateGenre(genreOne));
        assertEquals("Asserting getGenre", genreTwo, genreService.updateGenre(genreTwo));
        assertNotEquals("Asserting getGenre", genreOne, genreService.updateGenre(genreTwo));
        assertNotEquals("Asserting getGenre", genreTwo, genreService.updateGenre(genreOne));
    }
}