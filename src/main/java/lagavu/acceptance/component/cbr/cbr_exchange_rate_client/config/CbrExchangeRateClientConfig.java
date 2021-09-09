package lagavu.acceptance.component.cbr.cbr_exchange_rate_client.config;

import lagavu.acceptance.component.cbr.cbr_exchange_rate_client.CbrExchangeRateClient;
import lagavu.acceptance.component.cbr.cbr_exchange_rate_client.CbrExchangeRateClientProperties;
import lagavu.acceptance.component.cbr.cbr_exchange_rate_client.ICbrExchangeRateClient;
import lagavu.acceptance.component.cbr.rate_parser.ICbrRateParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CbrExchangeRateClientConfig {

    private final ICbrRateParser cbrRateParser;

    public CbrExchangeRateClientConfig(ICbrRateParser cbrRateParser) {
        this.cbrRateParser = cbrRateParser;
    }

    @Bean
    public ICbrExchangeRateClient cbrRateParser() {
        WebClient webClient = WebClient.create();
        CbrExchangeRateClientProperties properties = new CbrExchangeRateClientProperties();

        return new CbrExchangeRateClient(webClient, properties, cbrRateParser);
    }
}
