package app.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PatchException extends RuntimeException {
    public PatchException(String key) {
        super("Field " + key + " update is not allowed.");
    }
}
