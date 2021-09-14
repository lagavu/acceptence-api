package lagavu.acceptance.component.cbr.cbr_exchange_rate_client;

import lagavu.acceptance.component.cbr.rate_parser.parser.CbrRateXmlParser;
import lagavu.acceptance.data_fixture.cbr.CbrRateApiMock;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CbrExchangeRateClientTest {

    @Autowired
    private CbrExchangeRateClient cbrExchangeRateClient;

    @MockBean
    private WebClient webClient;

    @MockBean
    private CbrExchangeRateClientProperties properties;

    @MockBean
    private CbrRateXmlParser cbrRateParser;

    @Test
    public void getRateUsdRub() {
        String date = "26-05-2021";
        String xmlRates = CbrRateApiMock.getXmlRates();

        doReturn("url")
                .when(properties)
                .getUrlOfRates(date);

        setMockDataForWebClient(xmlRates);

        Float rateUsdRub = cbrExchangeRateClient.getRateUsdRub(date);
        assertEquals((float) 73.9866, rateUsdRub);
    }

    @Test
    public void getRateUsdRubUsingCache() {
        String date = "26-05-2021";

        doReturn("url")
                .when(properties)
                .getUrlOfRates(date);

        setMockDataForWebClient("data about rates");

        cbrExchangeRateClient.getRates(date);
        cbrExchangeRateClient.getRates(date);

        verify(webClient, times(1)).get();
    }

    private void setMockDataForWebClient(String xmlRates) {
        final var uriSpecMock = mock(WebClient.RequestHeadersUriSpec.class);
        final var headersSpecMock = mock(WebClient.RequestHeadersSpec.class);
        final var responseSpecMock = mock(WebClient.ResponseSpec.class);

        when(webClient.get()).thenReturn(uriSpecMock);
        when(uriSpecMock.uri(ArgumentMatchers.<String>notNull())).thenReturn(headersSpecMock);
        when(headersSpecMock.header(notNull(), notNull())).thenReturn(headersSpecMock);
        when(headersSpecMock.headers(notNull())).thenReturn(headersSpecMock);
        when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);

        when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<String>>notNull()))
                .thenReturn(Mono.just(xmlRates));
    }
}
