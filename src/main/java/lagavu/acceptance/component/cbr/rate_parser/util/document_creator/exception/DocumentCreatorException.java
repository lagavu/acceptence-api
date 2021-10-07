package lagavu.acceptance.component.cbr.rate_parser.util.document_creator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DocumentCreatorException extends RuntimeException {

    public DocumentCreatorException(String errorMessage) {
        super(errorMessage);
    }
}
