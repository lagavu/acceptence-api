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
@Table(name = "appeal_feedbacks")
@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeedbackAppeal extends Appeal {

    @NotNull
    @Column
    Boolean isVerifiedCodeWord;

    @NotNull
    @Column
    Integer accountId;

    public FeedbackAppeal(
            int sum,
            Currency currency,
            boolean isVerifiedCodeWord,
            int accountId
    ) {
        super(sum, currency);
        this.isVerifiedCodeWord = isVerifiedCodeWord;
        this.accountId = accountId;
    }

    @Override
    public boolean isVerify(Customer customer) {
        return Objects.equals(this.accountId, customer.getAccountId())
                && isVerifiedCodeWord;
    }
}
