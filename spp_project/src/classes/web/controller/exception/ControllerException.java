package classes.web.controller.exception;

/**
 * Created by Олег on 07.04.2016.
 */
public class ControllerException extends Exception {
    public ControllerException() {
    }

    public ControllerException(String message) {
        super(message);
    }
}
