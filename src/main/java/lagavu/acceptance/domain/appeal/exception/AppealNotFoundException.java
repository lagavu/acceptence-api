package lagavu.acceptance.domain.appeal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AppealNotFoundException extends RuntimeException {

    public AppealNotFoundException(String message) {
        super(message);
    }
}
