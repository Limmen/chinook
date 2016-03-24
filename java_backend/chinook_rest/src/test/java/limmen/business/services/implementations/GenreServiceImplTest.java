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

    @Before
    public void setUp() throws Exception {
        genreService = new GenreServiceImpl((genreRepository));
    }

    @Test
    public void testGetAllGenres() throws Exception {
        final List<Genre> databaseList = new ArrayList();
        when(genreRepository.getAllGenres()).thenReturn(databaseList);
        List<Genre> returnedList = genreService.getAllGenres();
        assertEquals("Asserting getAllGenres", databaseList, returnedList);
    }

    @Test
    public void testGetGenre() throws Exception {
        when(genreRepository.getGenre(1)).thenReturn(genreOne);
        when(genreRepository.getGenre(2)).thenReturn(genreTwo);
        assertEquals("Asserting getGenre", genreOne, genreService.getGenre(1));
        assertEquals("Asserting getGenre", genreTwo, genreService.getGenre(2));
        assertNotEquals("Asserting getGenre", genreOne, genreService.getGenre(2));
        assertNotEquals("Asserting getGenre", genreTwo, genreService.getGenre(1));
    }
}