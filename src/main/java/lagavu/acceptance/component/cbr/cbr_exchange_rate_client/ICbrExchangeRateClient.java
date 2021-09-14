package lagavu.acceptance.component.cbr.cbr_exchange_rate_client;

public interface ICbrExchangeRateClient {

    float getRateUsdRub(String date);

    float getRateEurRub(String date);

    float getRateChfRub(String date);

    float getRateGbpRub(String date);

    float getRateByCurrency(String currency, String date);

    String getRates(String date);
}
