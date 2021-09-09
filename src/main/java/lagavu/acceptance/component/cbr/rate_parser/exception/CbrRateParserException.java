package lagavu.acceptance.component.cbr.rate_parser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CbrRateParserException extends RuntimeException {

    public CbrRateParserException(String errorMessage) {
        super(errorMessage);
    }
}
