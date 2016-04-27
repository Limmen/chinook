package limmen.business.services.filters;

import limmen.integration.entities.Genre;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for implementing filtering and sorting functionality for the Genre-Resource.
 *
 * @author Kim Hammar on 2016-04-25.
 */
public class GenreFilter {

    private String sort;
    private String genreId;
    private String name;

    /**
     * Default constructor
     */
    public GenreFilter() {
    }

    /**
     * Method to filter a list of genres.
     *
     * @param genres list to filter
     * @return filtered list
     */
    public List<Genre> filter(List<Genre> genres) {
        if (name != null)
            genres = genres.stream().filter(genre -> {if(genre.getName() != null)
                return genre.getName().equals(name); else return false;}).collect(Collectors.toList());
        if (genreId != null)
            genres = genres.stream().filter(genre -> genre.getGenreId() == Integer.parseInt(genreId)).collect(Collectors.toList());
        return genres;
    }

    /**
     * Method to sort a list of genres.
     *
     * @param genres list to sort
     * @return sorted list
     */
    public List<Genre> sort(List<Genre> genres) {
        if(sort == null)
            return genres;
        String order = sort.substring(0, 1);
        String property = sort.substring(1, sort.length());
        Comparator<Genre> comparator = null;
        if (property.equals("genreId")) {
            comparator = (genre1, genre2) ->
            {
                if (genre1.getGenreId() > genre2.getGenreId())
                    return 1;
                else if (genre1.getGenreId() < genre2.getGenreId())
                    return -1;
                return 0;
            };
        }
        if (property.equals("name")) {
            comparator = (genre1, genre2) ->
            {
                if(genre1.getName() == null && genre2.getName() == null)
                    return 0;
                if(genre1.getName() == null)
                    return -1;
                if(genre2.getName() == null)
                    return 1;
                if (genre1.getName().compareTo(genre2.getName()) > 0)
                    return 1;
                else if (genre1.getName().compareTo(genre2.getName()) < 0)
                    return -1;
                return 0;
            };
        }
        Collections.sort(genres, comparator);
        if (order.equals("-")) {
            Collections.reverse(genres);
        }
        return genres;
    }


    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
