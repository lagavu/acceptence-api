package lagavu.acceptance.component.cbr.cbr_exchange_rate_client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CbrExchangeRateClientCorrectResponseTest {

    @Autowired
    private CbrExchangeRateClient cbrExchangeRateClient;

    @Test
    public void getRateUsdRub() {
        Float rateUsdRub = cbrExchangeRateClient.getRateUsdRub("06-05-2021");
        assertEquals((float) 74.8617, rateUsdRub);
    }

    @Test
    public void getRateChfRub() {
        Float rateChfRub = cbrExchangeRateClient.getRateChfRub("06-05-2021");
        System.out.println(rateChfRub);
        assertEquals((float) 81.7089, rateChfRub);
    }

    @Test
    public void getRateGbpRub() {
        Float rateGbpRub = cbrExchangeRateClient.getRateGbpRub("06-05-2021");
        System.out.println(rateGbpRub);
        assertEquals((float) 103.9979, rateGbpRub);
    }

    @Test
    public void getRateEurRub() {
        Float rateEurRub = cbrExchangeRateClient.getRateEurRub("06-05-2021");
        assertEquals((float) 89.7742, rateEurRub);
    }
}
