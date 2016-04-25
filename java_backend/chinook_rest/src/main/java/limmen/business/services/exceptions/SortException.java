package limmen.business.services.exceptions;

/**
 * Exception for when sorting can't be applied. Usually because of invalid query string.
 *
 * @author Kim Hammar on 2016-04-25.
 */
public class SortException extends Exception {

    public SortException() {
    }

    public SortException(String message) {
        super(message);
    }

    public SortException(Throwable cause){
        super(cause);
    }

    public SortException(String message, Throwable cause){
        super(message, cause);
    }
}
