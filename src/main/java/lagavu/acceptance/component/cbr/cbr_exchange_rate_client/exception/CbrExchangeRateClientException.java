package lagavu.acceptance.component.cbr.cbr_exchange_rate_client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CbrExchangeRateClientException extends RuntimeException {

    public CbrExchangeRateClientException(String errorMessage) {
        super(errorMessage);
    }
}
