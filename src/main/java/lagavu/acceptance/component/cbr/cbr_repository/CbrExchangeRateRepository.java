package lagavu.acceptance.component.cbr.cbr_repository;

import lagavu.acceptance.component.cbr.rate_parser.CbrRateParserInterface;
import lagavu.acceptance.component.cbr.rate_parser.identifier.RateIdentifier;
import lagavu.acceptance.component.cbr.сbr_сlient.CbrExchangeRateClient;
import lagavu.acceptance.util.date_formatter.DateFormatter;
import org.springframework.stereotype.Repository;

@Repository
public class CbrExchangeRateRepository {

    private final CbrRateParserInterface cbrRateParser;

    public CbrExchangeRateRepository(CbrRateParserInterface cbrRateParser) {
        this.cbrRateParser = cbrRateParser;
    }

    public static String getCurrentRates() {
        return CbrExchangeRateClient
                .getCbrClient()
                .get()
                .uri(DateFormatter.getCurrentDate(CbrExchangeRateClient.DATE_FORMAT))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public float getCurrentRateUsdRub() {
        return cbrRateParser.parse(getCurrentRates(), RateIdentifier.USD.getIdentifier());
    }
}
