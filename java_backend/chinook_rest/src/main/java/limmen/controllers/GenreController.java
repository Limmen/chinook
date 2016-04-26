package limmen.controllers;

import limmen.business.representations.array_representations.GenresArrayRepresentation;
import limmen.business.representations.entity_representation.GenreRepresentation;
import limmen.business.services.GenreService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.GenreFilter;
import limmen.integration.entities.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * RestController for the resource pointed by the url: /resources/genres*
 *
 * @author Kim Hammar on 2016-03-22.
 */
@RestController
@CrossOrigin
@RequestMapping("/resources/genres")
public class GenreController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final GenreService genreService;

    /**
     * Method to be injected by the Spring container. Initializes the service.
     *
     * @param genreService service that handles genre-data.
     */
    @Inject
    public GenreController(final GenreService genreService) {
        this.genreService = genreService;
    }

    /**
     * Method to handle HTTP GET-requests for /resources/genres
     *
     * @return HTTP-response, JSON array of genres
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<GenresArrayRepresentation> getAllGenres(@RequestParam(name = "genreId", required = false) String genreId,
                                                              @RequestParam(name = "name", required = false) String name,
                                                              @RequestParam(name = "sort", required = false) String sort)
            throws SortException {
        log.debug("HTTP GET-request /resources/genres");
        GenreFilter genreFilter = new GenreFilter();
        genreFilter.setGenreId(genreId);
        genreFilter.setName(name);
        genreFilter.setSort(sort);
        List<GenreRepresentation> genreRepresentations = new ArrayList();
        List<Genre> genres = genreService.getAllGenres(genreFilter);
        genres.forEach((genre) -> {
            GenreRepresentation genreRepresentation = new GenreRepresentation(genre);
            genreRepresentation.add(linkTo(methodOn(GenreController.class).getGenre(genre.getGenreId())).withSelfRel());
            genreRepresentations.add(genreRepresentation);
        });
        GenresArrayRepresentation arrayRepresentation = new GenresArrayRepresentation(genreRepresentations);
        return new ResponseEntity<GenresArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP GET-requests for /resources/genres/{genreId}
     *
     * @param genreId id of the genre.
     * @return HTTP-response, JSON representation of the genre
     */
    @CrossOrigin
    @RequestMapping(value = "/{genreId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<GenreRepresentation> getGenre(@PathVariable int genreId) {
        log.debug("HTTP GET-request /resources/genres/{}", genreId);
        GenreRepresentation genreRepresentation = new GenreRepresentation(genreService.getGenre(genreId));
        genreRepresentation.add(linkTo(methodOn(GenreController.class).getGenre(genreId)).withSelfRel());
        return new ResponseEntity<GenreRepresentation>(genreRepresentation, HttpStatus.OK);
    }


    /**
     * Method to handle HTTP POST-requests for /resources/genres
     *
     * @param genre posted genre data
     * @return HTTP-response, JSON representation of the created genre
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<GenreRepresentation> createNewGenre(@RequestBody Genre genre) {
        log.debug("HTTP POST-request /resources/genres");
        GenreRepresentation genreRepresentation = new GenreRepresentation(genreService.createNewGenre(genre));
        genreRepresentation.add(linkTo(methodOn(GenreController.class).getGenre(genre.getGenreId())).withSelfRel());
        return new ResponseEntity<GenreRepresentation>(genreRepresentation, HttpStatus.CREATED);
    }

    /**
     * Method to handle HTTP PUT-requests for /resources/genres/{genreId}
     *
     * @param genreId id of the genre
     * @param genre   put genre data
     * @return HTTP-Response, JSON representation of the updated genre representation
     */
    @CrossOrigin
    @RequestMapping(value = "/{genreId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<GenreRepresentation> updateGenre(@PathVariable int genreId, @RequestBody Genre genre) {
        log.debug("HTTP PUT-request /resources/genres/{}", genreId);
        genre.setGenreId(genreId);
        GenreRepresentation genreRepresentation = new GenreRepresentation(genreService.updateGenre(genre));
        genreRepresentation.add(linkTo(methodOn(GenreController.class).getGenre(genre.getGenreId())).withSelfRel());
        return new ResponseEntity<GenreRepresentation>(genreRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle PUT-requests for /resources/genres
     *
     * @param putGenres
     * @return HTTP-Response, JSON representation of the updated genres resource
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<GenresArrayRepresentation> updateGenres(@RequestBody List<Genre> putGenres) {
        log.debug("HTTP PUT-request /resources/genres");
        List<GenreRepresentation> genreRepresentations = new ArrayList();
        List<Genre> genres = genreService.updateGenres(putGenres);
        genres.forEach((genre) -> {
            GenreRepresentation genreRepresentation = new GenreRepresentation(genre);
            genreRepresentation.add(linkTo(methodOn(GenreController.class).getGenre(genre.getGenreId())).withSelfRel());
            genreRepresentations.add(genreRepresentation);
        });
        GenresArrayRepresentation arrayRepresentation = new GenresArrayRepresentation(genreRepresentations);
        return new ResponseEntity<GenresArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP DELETE-requests for /resources/genres/{genreId}
     *
     * @param genreId id of the genre
     * @return HTTP-Response, JSON representation of the deleted genre.
     */
    @CrossOrigin
    @RequestMapping(value = "/{genreId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<GenreRepresentation> deleteGenre(@PathVariable int genreId) {
        log.debug("HTTP DELETE-request /resources/genres/{}", genreId);
        GenreRepresentation genreRepresentation = new GenreRepresentation(genreService.deleteGenre(genreId));
        genreRepresentation.add(linkTo(methodOn(GenreController.class).getGenre(genreId)).withSelfRel());
        return new ResponseEntity<GenreRepresentation>(genreRepresentation, HttpStatus.OK);
    }

    /**
     * ExceptionHandler for when a requested resource was not found.
     *
     * @param response HTTP response to send back to client
     * @throws IOException
     */
    @ExceptionHandler(org.springframework.dao.EmptyResultDataAccessException.class)
    void notFound(HttpServletResponse response) throws IOException {
        log.debug("org.springframework.dao.EmptyResultDataAccessException.class exception caught");
        response.sendError(HttpStatus.NOT_FOUND.value(), "Resource not Found");
    }

    /**
     * ExceptionHandler for when query string was invalid.
     *
     * @param response HTTP response to send back to client
     * @throws IOException
     */
    @ExceptionHandler(SortException.class)
    void invalidQueryString(HttpServletResponse response) throws IOException {
        log.debug("invalid query string exception caught");
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid Query String");
    }
}
