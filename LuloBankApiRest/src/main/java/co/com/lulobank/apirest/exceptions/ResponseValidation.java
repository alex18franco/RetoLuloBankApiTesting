package co.com.lulobank.apirest.exceptions;

public class ResponseValidation extends AssertionError{

    public ResponseValidation(String message, Throwable cause) {
        super(message, cause);
    }

}
