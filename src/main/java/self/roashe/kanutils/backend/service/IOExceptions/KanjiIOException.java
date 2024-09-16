package self.roashe.kanutils.backend.service.IOExceptions;

public class KanjiIOException extends Exception{
    public KanjiIOException(String message) {
        super(message);

    }

    public KanjiIOException(String message, Throwable cause) {
        super(message, cause);
    }
}
