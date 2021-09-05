package lagavu.acceptance.data_fixture.appeal;

import lagavu.acceptance.domain.appeal.entity.CallAppeal;
import lagavu.acceptance.domain.appeal.entity.FeedbackAppeal;
import lagavu.acceptance.domain.appeal.entity.OfflineAppeal;
import lagavu.acceptance.domain.appeal.entity.valueObject.Currency;
import lagavu.acceptance.domain.customer.entity.Customer;

public class AppealFixture {

    private static final String phoneNumber = "+7965121333";
    private static final int accountId = 1;
    private static final int numberDocument = 222;

    public static CallAppeal createCallAppeal(Customer customer) {
        return new CallAppeal(
                100,
                Currency.USD,
                true,
                customer != null ? customer.getPhoneNumber() : phoneNumber
        );
    }

    public static CallAppeal createCallAppealWithNotVerifiedSmsConfirmation(Customer customer) {
        return new CallAppeal(
                100,
                Currency.USD,
                false,
                customer != null ? customer.getPhoneNumber() : phoneNumber
        );
    }

    public static FeedbackAppeal createFeedbackAppeal(Customer customer) {
        return new FeedbackAppeal(
                1000,
                Currency.EUR,
                true,
                customer != null ? customer.getAccountId() : accountId
        );
    }

    public static FeedbackAppeal createFeedbackAppealWithNotVerifiedCodeWord(Customer customer) {
        return new FeedbackAppeal(
                100,
                Currency.USD,
                false,
                customer != null ? customer.getAccountId() : accountId
        );
    }

    public static OfflineAppeal createOfflineAppeal(Customer customer) {
        return new OfflineAppeal(
                10000,
                Currency.CHF,
                true,
                customer != null ? customer.getNumberDocument() : numberDocument
        );
    }

    public static OfflineAppeal createOfflineAppealWithNotVerifiedDocumentProvided(Customer customer) {
        return new OfflineAppeal(
                10000,
                Currency.CHF,
                false,
                customer != null ? customer.getNumberDocument() : numberDocument
        );
    }
}
