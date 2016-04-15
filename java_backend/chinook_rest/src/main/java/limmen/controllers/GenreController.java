package limmen.controllers;

import limmen.business.representations.array_representations.GenresArrayRepresentation;
import limmen.business.representations.entity_representation.GenreRepresentation;
import limmen.business.services.GenreService;
import limmen.integration.entities.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
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
     * Method to handle HTTP-requests for /resources/genres
     *
     * @return HTTP-response, JSON array of genres
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<GenresArrayRepresentation> getAllGenres() {
        log.debug("HTTP GET-request /resources/genres");
        List<GenreRepresentation> genreRepresentations = new ArrayList();
        List<Genre> genres = genreService.getAllGenres();
        genres.forEach((genre) -> {
            GenreRepresentation genreRepresentation = new GenreRepresentation(genre);
            genreRepresentation.add(linkTo(methodOn(GenreController.class).getGenre(genre.getGenreId())).withSelfRel());
            genreRepresentations.add(genreRepresentation);
        });
        GenresArrayRepresentation arrayRepresentation = new GenresArrayRepresentation(genreRepresentations);
        return new ResponseEntity<GenresArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP-requests for /resources/genres/{genreId}
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
}
