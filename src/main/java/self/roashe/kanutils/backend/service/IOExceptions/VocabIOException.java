package self.roashe.kanutils.backend.service.IOExceptions;

public class VocabIOException extends RuntimeException {
    public VocabIOException(String message) {
        super(message);
    }
    public VocabIOException(String message, Throwable cause) {
    super(message, cause);
  }
}
