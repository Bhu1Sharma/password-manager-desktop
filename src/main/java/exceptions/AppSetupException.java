package exceptions;

public class AppSetupException extends IllegalStateException {
    public AppSetupException() {}
    public AppSetupException(String message) {
        super(message);
    }
}
