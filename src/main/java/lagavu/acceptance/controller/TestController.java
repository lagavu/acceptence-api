package lagavu.acceptance.controller;

import lagavu.acceptance.component.cbr.cbr_exchange_rate_client.CbrExchangeRateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

//    private final CbrExchangeRateRepository cbrExchangeRateRepository;
//
//    @Autowired
//    public TestController(CbrExchangeRateRepository cbrExchangeRateRepository) {
//        this.cbrExchangeRateRepository = cbrExchangeRateRepository;
//    }

    private final CbrExchangeRateClient cbrExchangeRateClient;

    @Autowired
    public TestController(CbrExchangeRateClient cbrExchangeRateClient) {
        this.cbrExchangeRateClient = cbrExchangeRateClient;
    }

//    @GetMapping(value = "/hello")
//    public Float getHello() {
//        return cbrExchangeRateRepository.getCurrentRateUsdRub();
//    }

    @GetMapping(value = "/hello1")
    public String getHello() {
        return cbrExchangeRateClient.getTodayRates();
    }
}
