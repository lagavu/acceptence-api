package lagavu.acceptance.component.cbr.cbr_exchange_rate_client;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="cbr-api")
public class CbrExchangeRateClientProperties {

    private String baseUrl;

    private String format;

    private String parameterDate;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getParameterDate() {
        return parameterDate;
    }

    public void setParameterDate(String parameterDate) {
        this.parameterDate = parameterDate;
    }

    public String getUrlOfRates(String date) {
        return getBaseUrl() + getFormat() + getParameterDate() + date;
    }
}
