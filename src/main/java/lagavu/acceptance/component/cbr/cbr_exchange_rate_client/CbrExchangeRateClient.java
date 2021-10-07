package lagavu.acceptance.component.cbr.cbr_exchange_rate_client;

import lagavu.acceptance.component.cbr.cbr_exchange_rate_client.exception.CbrExchangeRateClientException;
import lagavu.acceptance.component.cbr.rate_parser.ICbrRateParser;
import lagavu.acceptance.component.cbr.rate_parser.identifier.RateIdentifier;
import lagavu.acceptance.domain.appeal.entity.value_object.Currency;
import org.springframework.cache.annotation.Cacheable;
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
    public float getRateUsdRub(String date) {
        return cbrRateParser.parse(getRates(date), RateIdentifier.USD.getIdentifier());
    }

    @Override
    public float getRateEurRub(String date) {
        return cbrRateParser.parse(getRates(date), RateIdentifier.EUR.getIdentifier());
    }

    @Override
    public float getRateChfRub(String date) {
        return cbrRateParser.parse(getRates(date), RateIdentifier.CHF.getIdentifier());
    }

    @Override
    public float getRateGbpRub(String date) {
        return cbrRateParser.parse(getRates(date), RateIdentifier.GBP.getIdentifier());
    }

    @Override
    public float getRateByCurrency(String currency, String date) {
        if (currency.equals(Currency.USD.getCurrency())) {
            return getRateUsdRub(date);
        }

        if (currency.equals(Currency.EUR.getCurrency())) {
            return getRateEurRub(date);
        }

        if (currency.equals(Currency.CHF.getCurrency())) {
            return getRateChfRub(date);
        }

        if (currency.equals(Currency.GBP.getCurrency())) {
            return getRateGbpRub(date);
        }

        throw new CbrExchangeRateClientException("Failed to define currency to get today rate.");
    }

    @Override
    @Cacheable("cbr_rates")
    public String getRates(String date) {
        return webClient.get()
                .uri(properties.getUrlOfRates(date))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
