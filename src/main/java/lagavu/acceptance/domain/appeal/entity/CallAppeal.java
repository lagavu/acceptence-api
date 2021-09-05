package lagavu.acceptance.domain.appeal.entity;

import lagavu.acceptance.domain.appeal.entity.valueObject.Currency;
import lagavu.acceptance.domain.customer.entity.Customer;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "appeal_calls")
@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CallAppeal extends Appeal {

    @NotNull
    @Column
    Boolean isVerifiedSmsConfirmation;

    @NotNull
    @Column
    String callerPhoneNumber;

    public CallAppeal(
            int sum,
            Currency currency,
            Boolean isVerifiedSmsConfirmation,
            String callerPhoneNumber
    ) {
        super(sum, currency);

        this.isVerifiedSmsConfirmation = isVerifiedSmsConfirmation;
        this.callerPhoneNumber = callerPhoneNumber;
    }

    @Override
    public boolean isVerify(Customer customer) {
        return Objects.equals(this.callerPhoneNumber, customer.getPhoneNumber())
                && isVerifiedSmsConfirmation;
    }
}