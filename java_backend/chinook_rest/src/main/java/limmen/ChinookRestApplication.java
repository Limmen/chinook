package limmen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * This class represents the application as a whole.
 * Bootstraps and launches the Spring application from a regular main method.
 * SpringBootApplication annotation adds general configuration for a SpringBoot application.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@SpringBootApplication
public class ChinookRestApplication {

    /**
     * Main method, entry point of the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ChinookRestApplication.class, args);
    }
}
