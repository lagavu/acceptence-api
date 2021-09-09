package lagavu.acceptance.component.cbr.cbr_exchange_rate_client;

public interface ICbrExchangeRateClient {

    float getTodayRateUsdRub();

    float getTodayRateEurRub();

    float getTodayRateChfRub();

    float getTodayRateGbpRub();

    float getTodayRateByCurrency(String currency);

    String getTodayRates();
}
