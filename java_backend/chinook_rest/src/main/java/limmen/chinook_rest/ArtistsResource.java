/*
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar
 */
package limmen.chinook_rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import limmen.chinook_rest.integration.DBhandler;
import limmen.chinook_rest.integration.QueryManager;

/**
 * REST Web Service
 *
 * @author Kim Hammar
 */
@Path("{artists}")
public class ArtistsResource {

    @Context
    private UriInfo context;
    DBhandler dbHandler;
    QueryManager queryManager;

    /**
     * Creates a new instance of ServiceResource
     */
    public ArtistsResource() {
        dbHandler = new DBhandler();
        queryManager = new QueryManager(dbHandler);
    }

    /**
     * Retrieves representation of an instance of limmen.chinook_rest.ArtistsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getHtml() {
        return queryManager.getAllArtists().toString();
    }

    /**
     * PUT method for updating or creating an instance of ArtistsResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/html")
    public void putHtml(String content) {
    }
    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public String postHandler(String content) {
        return content;
    }
}
