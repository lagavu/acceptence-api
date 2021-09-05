package lagavu.acceptance.domain.appeal.service;

import lagavu.acceptance.data_fixture.appeal.AppealFixture;
import lagavu.acceptance.data_fixture.customer.CustomerFixture;
import lagavu.acceptance.domain.appeal.entity.CallAppeal;
import lagavu.acceptance.domain.appeal.entity.FeedbackAppeal;
import lagavu.acceptance.domain.appeal.entity.OfflineAppeal;
import lagavu.acceptance.domain.appeal.exception.RegistrationAppealException;
import lagavu.acceptance.domain.appeal.repository.AppealRepository;
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
class RegistrationOfflineAppealServiceTest {

    @Autowired
    private RegistrationAppealService registrationAppealService;

    @MockBean
    private AppealRepository appealRepository;

    @Test
    void phoneNumberFromCallOfAppealDoesNotMatchCustomerPhoneNumber() {
        Customer customer = CustomerFixture.create();
        CallAppeal callAppeal = AppealFixture.createCallAppeal(null);

        RegistrationAppealException exception = assertThrows(RegistrationAppealException.class, () -> {
            registrationAppealService.register(callAppeal, customer);
        });

        assertTrue(exception.getMessage().contains("Failed to verify appeal."));
    }

    @Test
    void notVerifiedSmsConfirmationForCall() {
        Customer customer = CustomerFixture.create();
        CallAppeal callAppeal = AppealFixture.createCallAppealWithNotVerifiedSmsConfirmation(customer);

        RegistrationAppealException exception = assertThrows(RegistrationAppealException.class, () -> {
            registrationAppealService.register(callAppeal, customer);
        });

        assertTrue(exception.getMessage().contains("Failed to verify appeal."));
    }

    @Test
    void notEnoughMoneyInAccount() {
        Customer customer = CustomerFixture.createWithZeroAmountDollars();
        CallAppeal callAppeal = AppealFixture.createCallAppeal(customer);

        RegistrationAppealException exception = assertThrows(RegistrationAppealException.class, () -> {
            registrationAppealService.register(callAppeal, customer);
        });

        assertTrue(exception.getMessage().contains("Not enough money in the account."));
    }

    @Test
    void registerCallAppeal() {
        Customer customer = CustomerFixture.create();
        CallAppeal callAppeal = AppealFixture.createCallAppeal(customer);

        registrationAppealService.register(callAppeal, customer);

        Mockito.verify(appealRepository, Mockito.times(1)).save(callAppeal);
    }

    @Test
    void accountIdFromFeedbackOfAppealDoesNotMatchCustomerAccountId() {
        Customer customer = CustomerFixture.create();
        FeedbackAppeal feedbackAppeal = AppealFixture.createFeedbackAppeal(null);

        RegistrationAppealException exception = assertThrows(RegistrationAppealException.class, () -> {
            registrationAppealService.register(feedbackAppeal, customer);
        });

        assertTrue(exception.getMessage().contains("Failed to verify appeal."));
    }

    @Test
    void notVerifiedCodeWordForFeedback() {
        Customer customer = CustomerFixture.create();
        FeedbackAppeal feedbackAppeal = AppealFixture.createFeedbackAppealWithNotVerifiedCodeWord(customer);

        RegistrationAppealException exception = assertThrows(RegistrationAppealException.class, () -> {
            registrationAppealService.register(feedbackAppeal, customer);
        });

        assertTrue(exception.getMessage().contains("Failed to verify appeal."));
    }

    @Test
    void registerFeedbackAppeal() {
        Customer customer = CustomerFixture.create();
        FeedbackAppeal feedbackAppeal = AppealFixture.createFeedbackAppeal(customer);

        registrationAppealService.register(feedbackAppeal, customer);

        Mockito.verify(appealRepository, Mockito.times(1)).save(feedbackAppeal);
    }

    @Test
    void numberDocumentFromOfflineAppealDoesNotMatchCustomerNumberDocument() {
        Customer customer = CustomerFixture.create();
        OfflineAppeal offlineAppeal = AppealFixture.createOfflineAppeal(null);

        RegistrationAppealException exception = assertThrows(RegistrationAppealException.class, () -> {
            registrationAppealService.register(offlineAppeal, customer);
        });

        assertTrue(exception.getMessage().contains("Failed to verify appeal."));
    }

    @Test
    void notVerifiedCodeWordForOfflineAppeal() {
        Customer customer = CustomerFixture.create();
        OfflineAppeal offlineAppeal = AppealFixture.createOfflineAppealWithNotVerifiedDocumentProvided(customer);

        RegistrationAppealException exception = assertThrows(RegistrationAppealException.class, () -> {
            registrationAppealService.register(offlineAppeal, customer);
        });

        assertTrue(exception.getMessage().contains("Failed to verify appeal."));
    }

    @Test
    void registerOffline() {
        Customer customer = CustomerFixture.create();
        OfflineAppeal offlineAppeal = AppealFixture.createOfflineAppeal(customer);

        registrationAppealService.register(offlineAppeal, customer);

        Mockito.verify(appealRepository, Mockito.times(1)).save(offlineAppeal);
    }
}