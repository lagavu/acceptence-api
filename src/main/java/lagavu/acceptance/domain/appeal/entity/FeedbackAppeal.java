package lagavu.acceptance.domain.appeal.entity;

import lagavu.acceptance.domain.appeal.dto.request.AppealRequestDto;
import lagavu.acceptance.domain.appeal.entity.value_object.AppealType;
import lagavu.acceptance.domain.appeal.entity.value_object.Currency;
import lagavu.acceptance.domain.customer.entity.Customer;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@DiscriminatorValue(value= AppealType.Values.FEEDBACK)
@NoArgsConstructor
public class FeedbackAppeal extends Appeal {

    @NotNull
    @Column
    private Boolean isVerifiedCodeWord;

    @NotNull
    @Column
    private Integer accountId;

    public FeedbackAppeal(
            int sum,
            Currency currency,
            float rate,
            Customer customer,
            boolean isVerifiedCodeWord,
            int accountId
    ) {
        super(sum, currency, rate, customer);
        this.isVerifiedCodeWord = isVerifiedCodeWord;
        this.accountId = accountId;
    }

    public Boolean isVerifiedCodeWord() {
        return isVerifiedCodeWord;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void update(AppealRequestDto appealRequestDto) {
        super.updateCommonData(appealRequestDto);

        if (appealRequestDto.getFeedbackAppealRequestDto() != null) {
            if (appealRequestDto.getFeedbackAppealRequestDto().isVerifiedCodeWord() != null) {
                isVerifiedCodeWord = appealRequestDto.getFeedbackAppealRequestDto().isVerifiedCodeWord();
            }

            if (appealRequestDto.getCallAppealRequestDto().getCallerPhoneNumber() != null) {
                accountId = appealRequestDto.getFeedbackAppealRequestDto().getAccountId();
            }
        }
    }

    @Override
    public boolean isVerify(Customer customer) {
        return Objects.equals(this.accountId, customer.getAccountId())
                && isVerifiedCodeWord;
    }
}
