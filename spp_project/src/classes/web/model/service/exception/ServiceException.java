package classes.web.model.service.exception;

/**
 * Created by Олег on 07.04.2016.
 */
public class ServiceException extends Exception {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }
}
