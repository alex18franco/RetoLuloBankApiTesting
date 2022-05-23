package co.com.lulobank.apirest.exceptions;

public class FailedOfApi extends AssertionError{

    public FailedOfApi(String message, Throwable cause) {
        super(message, cause);
    }
}
