package lagavu.acceptance.component.cbr.cbr_exchange_rate_client;

import lagavu.acceptance.component.cbr.cbr_exchange_rate_client.exception.CbrExchangeRateClientException;
import lagavu.acceptance.component.cbr.rate_parser.ICbrRateParser;
import lagavu.acceptance.component.cbr.rate_parser.identifier.RateIdentifier;
import lagavu.acceptance.domain.appeal.entity.valueObject.Currency;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CbrExchangeRateClient implements ICbrExchangeRateClient {

    private final WebClient webClient;
    private final CbrExchangeRateClientProperties properties;
    private final ICbrRateParser cbrRateParser;

    public CbrExchangeRateClient(
            WebClient webClient,
            CbrExchangeRateClientProperties properties,
            ICbrRateParser cbrRateParser
    ) {
        this.webClient = webClient;
        this.properties = properties;
        this.cbrRateParser = cbrRateParser;
    }

    @Override
    public float getTodayRateUsdRub() {
        return cbrRateParser.parse(getTodayRates(), RateIdentifier.USD.getIdentifier());
    }

    @Override
    public float getTodayRateEurRub() {
        return cbrRateParser.parse(getTodayRates(), RateIdentifier.EUR.getIdentifier());
    }

    @Override
    public float getTodayRateChfRub() {
        return cbrRateParser.parse(getTodayRates(), RateIdentifier.CHF.getIdentifier());
    }

    @Override
    public float getTodayRateGbpRub() {
        return cbrRateParser.parse(getTodayRates(), RateIdentifier.GBP.getIdentifier());
    }

    @Override
    public float getTodayRateByCurrency(String currency) {
        if (currency.equals(Currency.USD.getCurrency())) {
            return getTodayRateUsdRub();
        }

        if (currency.equals(Currency.EUR.getCurrency())) {
            return getTodayRateEurRub();
        }

        if (currency.equals(Currency.CHF.getCurrency())) {
            return getTodayRateChfRub();
        }

        if (currency.equals(Currency.GBP.getCurrency())) {
            return getTodayRateGbpRub();
        }

        throw new CbrExchangeRateClientException("Failed to define currency to get today rate.");
    }

    @Override
    public String getTodayRates() {
        return webClient.get()
                .uri(properties.getUrlForCurrentRates())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
