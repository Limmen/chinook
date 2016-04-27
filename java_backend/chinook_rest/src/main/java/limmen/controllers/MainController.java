package limmen.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController for the resource pointed by the url: /*
 *
 * @author Kim Hammar on 2016-03-22.
 */
@RestController
@CrossOrigin
public class MainController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Method to handle HTTP GET-requests for /
     *
     * @return HTTP-response
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String index(){
        log.debug("HTTP GET-request /");
        return "<h1> INDEX </h1>";
    }

    /**
     * Method to handle HTTP GET-requests for /error
     *
     * @return HTTP-response
     */
    @CrossOrigin
    @RequestMapping(value = "/error", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String error(){
        //JSON-response with exception is sent by default by Spring.
        log.debug("HTTP GET-request /error");
        return "<h1> ERROR </h1>";
    }
}

