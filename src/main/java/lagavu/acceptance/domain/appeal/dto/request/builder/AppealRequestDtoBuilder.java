package lagavu.acceptance.domain.appeal.dto.request.builder;

import lagavu.acceptance.domain.appeal.dto.request.AppealRequestDto;
import lagavu.acceptance.domain.appeal.dto.request.CallAppealRequestDto;
import lagavu.acceptance.domain.appeal.dto.request.FeedbackAppealRequestDto;
import lagavu.acceptance.domain.appeal.dto.request.OfflineAppealRequestDto;
import lagavu.acceptance.domain.appeal.entity.value_object.Currency;

public class AppealRequestDtoBuilder {
    private final String type;
    private final int sum;
    private final Currency currency;
    private CallAppealRequestDto callAppealRequestDto;
    private FeedbackAppealRequestDto feedbackAppealRequestDto;
    private OfflineAppealRequestDto offlineAppealRequestDto;

    public AppealRequestDtoBuilder(String type, int sum, Currency currency) {
        this.type = type;
        this.sum = sum;
        this.currency = currency;
    }

    public AppealRequestDtoBuilder callAppealRequestDto(boolean isVerifiedSmsConfirmation, String callerPhoneNumber) {
        this.callAppealRequestDto = new CallAppealRequestDto(isVerifiedSmsConfirmation, callerPhoneNumber);;
        return this;
    }

    public AppealRequestDtoBuilder feedbackAppealRequestDto(boolean isVerifiedCodeWord, int accountId) {
        this.feedbackAppealRequestDto = new FeedbackAppealRequestDto(isVerifiedCodeWord, accountId);;
        return this;
    }

    public AppealRequestDtoBuilder offlineAppealRequestDto(boolean isVerifiedDocumentProvided, int numberDocument) {
        this.offlineAppealRequestDto = new OfflineAppealRequestDto(isVerifiedDocumentProvided, numberDocument);;
        return this;
    }

    public AppealRequestDto build() {
        return new AppealRequestDto(
                type,
                sum,
                currency,
                callAppealRequestDto,
                feedbackAppealRequestDto,
                offlineAppealRequestDto
        );
    }
}