package limmen.controllers;

import limmen.business.representations.array_representations.AlbumsArrayRepresentation;
import limmen.business.representations.entity_representation.AlbumRepresentation;
import limmen.business.services.AlbumService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.AlbumFilter;
import limmen.integration.entities.Album;
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
 * RestController for the resource pointed by the url: /resources/albums*
 *
 * @author Kim Hammar on 2016-03-22.
 */
@RestController
@CrossOrigin
@RequestMapping("/resources/albums")
public class AlbumController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final AlbumService albumService;

    /**
     * Method to be injected by the Spring container. Initializes the service.
     *
     * @param albumService service for album-data
     */
    @Inject
    public AlbumController(final AlbumService albumService) {
        this.albumService = albumService;
    }

    /**
     * Method to handle HTTP GET-requests for /resources/albums
     *
     * @return HTTP-response, JSON array of albums
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<AlbumsArrayRepresentation> getAllAlbums(@RequestParam(name = "albumId", required = false) String albumId,
                                                              @RequestParam(name = "name", required = false) String title,
                                                              @RequestParam(name = "artistId", required = false) String artistId,
                                                              @RequestParam(name = "sort", required = false) String sort)
            throws SortException {
        log.debug("HTTP GET-request /resources/albums");
        AlbumFilter albumFilter = new AlbumFilter();
        albumFilter.setAlbumId(albumId);
        albumFilter.setTitle(title);
        albumFilter.setArtistId(artistId);
        albumFilter.setSort(sort);
        List<AlbumRepresentation> albumRepresentations = new ArrayList();
        List<Album> albums = albumService.getAllAlbums(albumFilter);
        albums.forEach((album) -> {
            AlbumRepresentation albumRepresentation = new AlbumRepresentation(album);
            albumRepresentation.add(linkTo(methodOn(AlbumController.class).getAlbum(album.getAlbumId())).withSelfRel());
            albumRepresentation.add(linkTo(methodOn(ArtistController.class).getArtist(albumRepresentation.getAlbum()
                    .getArtistId())).withRel("artist"));
            albumRepresentations.add(albumRepresentation);
        });
        AlbumsArrayRepresentation arrayRepresentation = new AlbumsArrayRepresentation(albumRepresentations);
        return new ResponseEntity<AlbumsArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP GET-requests for /resources/albums/{albumId}
     *
     * @param albumId id of the album
     * @return HTTP-response, JSON representation of the album
     */
    @CrossOrigin
    @RequestMapping(value = "/{albumId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<AlbumRepresentation> getAlbum(@PathVariable int albumId) {
        log.debug("HTTP GET-request /resources/albums/{}", albumId);
        AlbumRepresentation albumRepresentation = new AlbumRepresentation(albumService.getAlbum(albumId));
        albumRepresentation.add(linkTo(methodOn(AlbumController.class).getAlbum(albumId)).withSelfRel());
        albumRepresentation.add(linkTo(methodOn(ArtistController.class).getArtist(albumRepresentation.getAlbum()
                .getArtistId())).withRel("artist"));
        return new ResponseEntity<AlbumRepresentation>(albumRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP POST-requests for /resources/artists
     *
     * @param album posted album data
     * @return HTTP-response, JSON representation of the created album
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<AlbumRepresentation> createNewAlbum(@RequestBody Album album) {
        log.debug("HTTP POST-request /resources/albums");
        AlbumRepresentation albumRepresentation = new AlbumRepresentation(albumService.createNewAlbum(album));
        albumRepresentation.add(linkTo(methodOn(AlbumController.class).getAlbum(album.getAlbumId())).withSelfRel());
        albumRepresentation.add(linkTo(methodOn(ArtistController.class).getArtist(albumRepresentation.getAlbum()
                .getArtistId())).withRel("artist"));
        return new ResponseEntity<AlbumRepresentation>(albumRepresentation, HttpStatus.CREATED);
    }

    /**
     * Method to handle HTTP PUT-requests for /resources/albums/{albumId}
     *
     * @param albumId id of the album
     * @param album   put album data
     * @return HTTP-Response, JSON representation of the updated album representation
     */
    @CrossOrigin
    @RequestMapping(value = "/{albumId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<AlbumRepresentation> updateAlbum(@PathVariable int albumId, @RequestBody Album album) {
        log.debug("HTTP PUT-request /resources/albums/{}", albumId);
        album.setAlbumId(albumId);
        AlbumRepresentation albumRepresentation = new AlbumRepresentation(albumService.updateAlbum(album));
        albumRepresentation.add(linkTo(methodOn(AlbumController.class).getAlbum(album.getAlbumId())).withSelfRel());
        albumRepresentation.add(linkTo(methodOn(ArtistController.class).getArtist(albumRepresentation.getAlbum()
                .getArtistId())).withRel("artist"));
        return new ResponseEntity<AlbumRepresentation>(albumRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle PUT-requests for /resources/albums
     *
     * @param putAlbums
     * @return HTTP-Response, JSON representation of the updated albums resource
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<AlbumsArrayRepresentation> updateAlbums(@RequestBody List<Album> putAlbums) {
        log.debug("HTTP PUT-request /resources/albums");
        List<AlbumRepresentation> albumRepresentations = new ArrayList();
        List<Album> albums = albumService.updateAlbums(putAlbums);
        albums.forEach((album) -> {
            AlbumRepresentation albumRepresentation = new AlbumRepresentation(album);
            albumRepresentation.add(linkTo(methodOn(AlbumController.class).getAlbum(album.getAlbumId())).withSelfRel());
            albumRepresentation.add(linkTo(methodOn(ArtistController.class).getArtist(albumRepresentation.getAlbum()
                    .getArtistId())).withRel("artist"));
            albumRepresentations.add(albumRepresentation);
        });
        AlbumsArrayRepresentation arrayRepresentation = new AlbumsArrayRepresentation(albumRepresentations);
        return new ResponseEntity<AlbumsArrayRepresentation>(arrayRepresentation, HttpStatus.OK);
    }

    /**
     * Method to handle HTTP DELETE-requests for /resources/albums/{albumId}
     *
     * @param albumId id of the album
     * @return HTTP-Response, JSON representation of the deleted album.
     */
    @CrossOrigin
    @RequestMapping(value = "/{albumId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<AlbumRepresentation> deleteAlbum(@PathVariable int albumId) {
        log.debug("HTTP DELETE-request /resources/albums/{}", albumId);
        AlbumRepresentation albumRepresentation = new AlbumRepresentation(albumService.deleteAlbum(albumId));
        albumRepresentation.add(linkTo(methodOn(AlbumController.class).getAlbum(albumId)).withSelfRel());
        albumRepresentation.add(linkTo(methodOn(ArtistController.class).getArtist(albumRepresentation.getAlbum()
                .getArtistId())).withRel("artist"));
        return new ResponseEntity<AlbumRepresentation>(albumRepresentation, HttpStatus.OK);
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
