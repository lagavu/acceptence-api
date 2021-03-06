package lagavu.acceptance.domain.appeal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RegistrationAppealException extends RuntimeException {

    public RegistrationAppealException(String errorMessage) {
        super(errorMessage);
    }
}
