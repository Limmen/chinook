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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Kim Hammar on 2016-03-22.
 */
@RestController
@RequestMapping("/resources/genres")
public class GenreController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final GenreService genreService;

    @Inject
    public GenreController(final GenreService genreService) {
        this.genreService = genreService;
    }

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

    @RequestMapping(value = "/{genreId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<GenreRepresentation> getGenre(@PathVariable int genreId) {
        log.debug("HTTP GET-request /resources/genres/{}", genreId);
        GenreRepresentation genreRepresentation = new GenreRepresentation(genreService.getGenre(genreId));
        genreRepresentation.add(linkTo(methodOn(GenreController.class).getGenre(genreId)).withSelfRel());
        return new ResponseEntity<GenreRepresentation>(genreRepresentation, HttpStatus.OK);
    }
}
