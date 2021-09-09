package lagavu.acceptance.component.cbr.cbr_exchange_rate_client;

import lagavu.acceptance.component.cbr.rate_parser.config.CbrRateParserConfig;
import lagavu.acceptance.component.cbr.rate_parser.ICbrRateParser;
import lagavu.acceptance.data_fixture.cbr.CbrRateApiMock;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CbrRateParserConfig.class, loader = AnnotationConfigContextLoader.class)
class CbrExchangeRateClientTest {

    @Autowired
    private ICbrRateParser cbrRateParser;

    private MockWebServer mockWebServer;
    private CbrExchangeRateClient cbrExchangeRateClient;

    @BeforeEach
    void setupMockWebServer() {
        mockWebServer = new MockWebServer();

        CbrExchangeRateClientProperties properties = new CbrExchangeRateClientProperties();
        properties.setBaseUrl(mockWebServer.url("/").url().toString());
        properties.setFormat("XML_daily.asp");
        properties.setParameterDate("?date_req=");

        cbrExchangeRateClient = new CbrExchangeRateClient(WebClient.create(), properties, cbrRateParser);
    }

    @Test
    void getTodayRateUsdRub() {
        String xml = CbrRateApiMock.getXmlRates();

        mockWebServer.enqueue(
                new MockResponse().setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE)
                        .setBody(xml)
        );

        Float rateUsdRub = cbrExchangeRateClient.getTodayRateUsdRub();
        assertEquals((float) 73.9866, rateUsdRub);
    }
}