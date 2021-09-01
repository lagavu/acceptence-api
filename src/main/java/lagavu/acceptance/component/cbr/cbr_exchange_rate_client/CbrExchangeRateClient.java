package lagavu.acceptance.component.cbr.cbr_exchange_rate_client;

import lagavu.acceptance.component.cbr.rate_parser.CbrRateParserInterface;
import lagavu.acceptance.component.cbr.rate_parser.identifier.RateIdentifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CbrExchangeRateClient {

    private final WebClient webClient;
    private final CbrExchangeRateClientProperties properties;
    private final CbrRateParserInterface cbrRateParser;

    public float getTodayRateUsdRub() {
        return cbrRateParser.parse(getTodayRates(), RateIdentifier.USD.getIdentifier());
    }

    public String getTodayRates() {
        return webClient.get()
                .uri(properties.getUrlForCurrentRates())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
