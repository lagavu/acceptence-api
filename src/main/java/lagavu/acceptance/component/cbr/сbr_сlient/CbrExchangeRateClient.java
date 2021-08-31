package lagavu.acceptance.component.cbr.сbr_сlient;

import org.springframework.web.reactive.function.client.WebClient;

public class CbrExchangeRateClient {

    public static final String DATE_FORMAT = "dd-MM-yyyy";

    private static final String BASE_URL = "http://www.cbr.ru/scripts/";
    private static final String FORMAT = "XML_daily.asp";
    private static final String PARAMETER_DATE = "?date_req=";

    public static WebClient getCbrClient() {
        return WebClient.create(getBaseUrl());
    }

    private static String getBaseUrl() {
        return CbrExchangeRateClient.BASE_URL + CbrExchangeRateClient.FORMAT + CbrExchangeRateClient.PARAMETER_DATE;
    }
}
