package lagavu.acceptance.domain.appeal.service;

import lagavu.acceptance.component.cbr.cbr_exchange_rate_client.ICbrExchangeRateClient;
import lagavu.acceptance.data_fixture.customer.CustomerFixture;
import lagavu.acceptance.domain.appeal.dto.request.AppealRequestDto;
import lagavu.acceptance.domain.appeal.dto.request.builder.AppealRequestDtoBuilder;
import lagavu.acceptance.domain.appeal.entity.Appeal;
import lagavu.acceptance.domain.appeal.entity.CallAppeal;
import lagavu.acceptance.domain.appeal.entity.FeedbackAppeal;
import lagavu.acceptance.domain.appeal.entity.OfflineAppeal;
import lagavu.acceptance.domain.appeal.entity.value_object.AppealType;
import lagavu.acceptance.domain.appeal.entity.value_object.Currency;
import lagavu.acceptance.domain.appeal.exception.RegistrationAppealException;
import lagavu.acceptance.domain.appeal.exception.UndefinedTypeAppealException;
import lagavu.acceptance.domain.appeal.repository.IAppealRepository;
import lagavu.acceptance.domain.customer.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class AppealServiceTest {

    private final Customer customer = CustomerFixture.create();

    @Autowired
    private AppealService appealService;

    @MockBean
    private IAppealRepository appealRepository;

    @MockBean
    private ICbrExchangeRateClient cbrExchangeRateClient;

    @Test
    void phoneNumberFromCallOfAppealDoesNotMatchCustomerPhoneNumber() {
        Customer customer = CustomerFixture.create();
        AppealRequestDto appealRequestDto = new AppealRequestDtoBuilder(AppealType.Values.CALL, 500, Currency.USD)
                .callAppealRequestDto(true, "+796464464")
                .build();

        RegistrationAppealException exception = assertThrows(RegistrationAppealException.class, () -> {
            appealService.register(appealRequestDto, customer);
        });

        assertTrue(exception.getMessage().contains("Failed to verify appeal."));
    }

    @Test
    void notVerifiedSmsConfirmationForCall() {
        Customer customer = CustomerFixture.create();
        AppealRequestDto appealRequestDto = new AppealRequestDtoBuilder(AppealType.Values.CALL, 100, Currency.USD)
                .callAppealRequestDto(false, "+796464464")
                .build();

        RegistrationAppealException exception = assertThrows(RegistrationAppealException.class, () -> {
            appealService.register(appealRequestDto, customer);
        });

        assertTrue(exception.getMessage().contains("Failed to verify appeal."));
    }

    @Test
    void notEnoughMoneyInAccount() {
        AppealRequestDto appealRequestDto = new AppealRequestDtoBuilder(
                AppealType.Values.CALL, 500000, Currency.USD).build();

        RegistrationAppealException exception = assertThrows(RegistrationAppealException.class, () -> {
            appealService.register(appealRequestDto, customer);
        });

        assertTrue(exception.getMessage().contains("Not enough money in the account."));
    }

    @Test
    void registerCallAppeal() {
        AppealRequestDto appealRequestDto = new AppealRequestDtoBuilder(AppealType.Values.CALL, 500, Currency.USD)
                .callAppealRequestDto(true, CustomerFixture.phoneNumber)
                .build();

        Mockito.doReturn((float) 78.00)
                .when(cbrExchangeRateClient)
                .getTodayRateByCurrency(Currency.USD.getCurrency());

        Appeal appeal = appealService.register(appealRequestDto, customer);

        CallAppeal callAppeal = (CallAppeal) appeal;
        assertEquals(appealRequestDto.getType(), callAppeal.getDecriminatorValue());
        assertEquals(appealRequestDto.getSum(), callAppeal.getSum());
        assertEquals(appealRequestDto.getCurrency(), callAppeal.getCurrency());
        assertEquals((float) 78.00, callAppeal.getRate());
        assertEquals(appealRequestDto.getCallAppealRequestDto().isVerifiedSmsConfirmation(),
                callAppeal.isVerifiedSmsConfirmation());
        assertEquals(appealRequestDto.getCallAppealRequestDto().getCallerPhoneNumber(),
                callAppeal.getCallerPhoneNumber());

        Mockito.verify(appealRepository, Mockito.times(1)).save(callAppeal);
    }

    @Test
    void registerFeedbackAppeal() {
        AppealRequestDto appealRequestDto = new AppealRequestDtoBuilder(
                AppealType.Values.FEEDBACK, 500, Currency.CHF)
                .feedbackAppealRequestDto(true, 144)
                .build();

        Mockito.doReturn((float) 78.00)
                .when(cbrExchangeRateClient)
                .getTodayRateByCurrency(Currency.CHF.getCurrency());

        Appeal appeal = appealService.register(appealRequestDto, customer);

        FeedbackAppeal feedbackAppeal = (FeedbackAppeal) appeal;
        assertEquals(appealRequestDto.getType(), feedbackAppeal.getDecriminatorValue());
        assertEquals(appealRequestDto.getSum(), feedbackAppeal.getSum());
        assertEquals(appealRequestDto.getCurrency(), feedbackAppeal.getCurrency());
        assertEquals((float) 78.00, feedbackAppeal.getRate());
        assertEquals(appealRequestDto.getFeedbackAppealRequestDto().isVerifiedCodeWord(),
                feedbackAppeal.isVerifiedCodeWord());
        assertEquals(appealRequestDto.getFeedbackAppealRequestDto().getAccountId(), feedbackAppeal.getAccountId());

        Mockito.verify(appealRepository, Mockito.times(1)).save(feedbackAppeal);
    }

    @Test
    void registerOfflineAppeal() {
        AppealRequestDto appealRequestDto = new AppealRequestDtoBuilder(
                AppealType.Values.OFFLINE, 500, Currency.GBP)
                .offlineAppealRequestDto(true, 9877784)
                .build();

        Mockito.doReturn((float) 78.00)
                .when(cbrExchangeRateClient)
                .getTodayRateByCurrency(Currency.GBP.getCurrency());

        Appeal appeal = appealService.register(appealRequestDto, customer);

        OfflineAppeal offlineAppeal = (OfflineAppeal) appeal;
        assertEquals(appealRequestDto.getType(), offlineAppeal.getDecriminatorValue());
        assertEquals(appealRequestDto.getSum(), offlineAppeal.getSum());
        assertEquals(appealRequestDto.getCurrency(), offlineAppeal.getCurrency());
        assertEquals((float) 78.00, offlineAppeal.getRate());
        assertEquals(appealRequestDto.getOfflineAppealRequestDto().isVerifiedDocumentProvided(),
                offlineAppeal.isVerifiedDocumentProvided());
        assertEquals(appealRequestDto.getOfflineAppealRequestDto().getNumberDocument(),
                offlineAppeal.getNumberDocument());

        Mockito.verify(appealRepository, Mockito.times(1)).save(offlineAppeal);
    }

    @Test
    void undefinedTypeAppeal() {
        AppealRequestDto appealRequestDto = new AppealRequestDtoBuilder("Undefine type", 500, Currency.GBP)
                .build();

        UndefinedTypeAppealException exception = assertThrows(UndefinedTypeAppealException.class, () -> {
            appealService.register(appealRequestDto, customer);
        });

        assertTrue(exception.getMessage().contains("Failed to define type appeal."));
    }
}
