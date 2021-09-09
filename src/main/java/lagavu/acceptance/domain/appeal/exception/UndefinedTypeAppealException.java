package lagavu.acceptance.domain.appeal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UndefinedTypeAppealException extends RuntimeException {

    public UndefinedTypeAppealException(String message) {
        super(message);
    }
}
