package lagavu.acceptance.domain.appeal.factory;

import lagavu.acceptance.domain.appeal.dto.request.AppealRequestDto;
import lagavu.acceptance.domain.appeal.entity.Appeal;
import lagavu.acceptance.domain.appeal.entity.CallAppeal;
import lagavu.acceptance.domain.appeal.entity.FeedbackAppeal;
import lagavu.acceptance.domain.appeal.entity.OfflineAppeal;
import lagavu.acceptance.domain.appeal.exception.UndefinedTypeAppealException;
import lagavu.acceptance.domain.customer.entity.Customer;

public class AppealFactory {

    public static Appeal create(AppealRequestDto appealRequestDto, Customer customer) {
        if (appealRequestDto.isCallAppealType()) {
            return new CallAppeal(
                    appealRequestDto.getSum(),
                    appealRequestDto.getCurrency(),
                    appealRequestDto.getRate(),
                    customer,
                    appealRequestDto.getCallAppealRequestDto().isVerifiedSmsConfirmation(),
                    appealRequestDto.getCallAppealRequestDto().getCallerPhoneNumber()
            );
        }

        if (appealRequestDto.isFeedbackAppealType()) {
            return new FeedbackAppeal(
                    appealRequestDto.getSum(),
                    appealRequestDto.getCurrency(),
                    appealRequestDto.getRate(),
                    customer,
                    appealRequestDto.getFeedbackAppealRequestDto().isVerifiedCodeWord(),
                    appealRequestDto.getFeedbackAppealRequestDto().getAccountId()
            );
        }

        if (appealRequestDto.isOfflineAppealType()) {
            return new OfflineAppeal(
                    appealRequestDto.getSum(),
                    appealRequestDto.getCurrency(),
                    appealRequestDto.getRate(),
                    customer,
                    appealRequestDto.getOfflineAppealRequestDto().isVerifiedDocumentProvided(),
                    appealRequestDto.getOfflineAppealRequestDto().getNumberDocument()
            );
        }

        throw new UndefinedTypeAppealException("Failed to define type appeal.");
    }
}
