package lagavu.acceptance.domain.appeal.entity;

import lagavu.acceptance.domain.appeal.dto.request.AppealRequestDto;
import lagavu.acceptance.domain.appeal.entity.value_object.AppealType;
import lagavu.acceptance.domain.appeal.entity.value_object.Currency;
import lagavu.acceptance.domain.customer.entity.Customer;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@DiscriminatorValue(value= AppealType.Values.CALL)
@NoArgsConstructor
public class CallAppeal extends Appeal {

    @NotNull
    @Column
    private Boolean isVerifiedSmsConfirmation;

    @NotNull
    @Column
    private String callerPhoneNumber;

    public CallAppeal(
            int sum,
            Currency currency,
            float rate,
            Customer customer,
            Boolean isVerifiedSmsConfirmation,
            String callerPhoneNumber
    ) {
        super(sum, currency, rate, customer);

        this.isVerifiedSmsConfirmation = isVerifiedSmsConfirmation;
        this.callerPhoneNumber = callerPhoneNumber;
    }

    public Boolean isVerifiedSmsConfirmation() {

        return isVerifiedSmsConfirmation;
    }

    public String getCallerPhoneNumber() {
        return callerPhoneNumber;
    }

    @Override
    public boolean isVerify(Customer customer) {
        return Objects.equals(this.callerPhoneNumber, customer.getPhoneNumber())
                && isVerifiedSmsConfirmation;
    }

    public void update(AppealRequestDto appealRequestDto) {
        super.update(appealRequestDto);

        if (appealRequestDto.getCallAppealRequestDto() != null) {
            if (appealRequestDto.getCallAppealRequestDto().isVerifiedSmsConfirmation() != null) {
                isVerifiedSmsConfirmation = appealRequestDto.getCallAppealRequestDto().isVerifiedSmsConfirmation();
            }

            if (appealRequestDto.getCallAppealRequestDto().getCallerPhoneNumber() != null) {
                callerPhoneNumber = appealRequestDto.getCallAppealRequestDto().getCallerPhoneNumber();
            }
        }
    }
}