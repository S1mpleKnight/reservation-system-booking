package by.zelezinsky.reservationsystembooking.exception;

public class JwtAuthenticationException extends RuntimeException {
    public JwtAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
