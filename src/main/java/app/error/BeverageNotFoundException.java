package app.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BeverageNotFoundException extends RuntimeException{
    public BeverageNotFoundException(Long id) {
        super("Beverage id not found : " + id);
    }
}
