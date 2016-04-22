package classes.web.model.service.exception;

/**
 * Service layer exception
 */
public class ServiceException extends Exception {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }
}
