package limmen.business.services.filters;

import limmen.integration.entities.Genre;
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
public class GenreFilterTest {

    private GenreFilter genreFilter;
    private List<Genre> genres;

    @Mock
    Genre genreOne;
    @Mock
    Genre genreTwo;
    @Mock
    Genre genreThree;
    @Mock
    Genre genreFour;
    @Mock
    Genre genreFive;

    /**
     * This method is used for initializing the test, and called before tests are executed.
     */
    @Before
    public void setUp() {
        genreFilter = new GenreFilter();
        genres = new ArrayList<>();
        genres.add(genreOne);
        genres.add(genreTwo);
        genres.add(genreThree);
        genres.add(genreFour);
        genres.add(genreFive);
        when(genreOne.getGenreId()).thenReturn(1);
        when(genreTwo.getGenreId()).thenReturn(2);
        when(genreThree.getGenreId()).thenReturn(3);
        when(genreFour.getGenreId()).thenReturn(4);
        when(genreFive.getGenreId()).thenReturn(5);
    }

    /**
     * Test of the filter method
     */
    @Test
    public void testFilter() {
        genreFilter.setGenreId("1");
        assertEquals("Asserting filter genres", genreOne, genreFilter.filter(genres).get(0));
        genreFilter.setGenreId("2");
        assertNotEquals("Asserting filter genres", genreOne, genreFilter.filter(genres).get(0));
        assertEquals("Asserting filter genres", genreTwo, genreFilter.filter(genres).get(0));
    }

    /**
     * Test of the sort method
     */
    @Test
    public void testSort() {
        genreFilter.setSort("+genreId");
        assertEquals("Asserting filter genres", genreOne, genreFilter.sort(genres).get(0));
        assertEquals("Asserting filter genres", genreFive, genreFilter.sort(genres).get(4));
        genreFilter.setSort("-genreId");
        assertEquals("Asserting filter genres", genreFive, genreFilter.sort(genres).get(0));
        assertEquals("Asserting filter genres", genreFour, genreFilter.sort(genres).get(1));
    }
}