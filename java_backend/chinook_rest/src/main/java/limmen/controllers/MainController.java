package limmen.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kim Hammar on 2016-03-22.
 */
@RestController
public class MainController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String index(){
        log.debug("HTTP GET-request /");
        return "<h1> INDEX </h1>";
    }
    @RequestMapping(value = "/error", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String error(){
        //JSON-response with exception is sent by default by Spring.
        log.debug("HTTP GET-request /error");
        return "<h1> ERROR </h1>";
    }
}

