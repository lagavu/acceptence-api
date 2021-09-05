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
@Table(name = "appeal_offline")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OfflineAppeal extends Appeal {

    @NotNull
    @Column
    Boolean isVerifiedDocumentProvided;

    @NotNull
    @Column
    Integer numberDocument;

    public OfflineAppeal(
            int sum,
            Currency currency,
            Boolean isVerifiedDocumentProvided,
            int numberDocument
    ) {
        super(sum, currency);
        this.isVerifiedDocumentProvided = isVerifiedDocumentProvided;
        this.numberDocument = numberDocument;
    }

    @Override
    public boolean isVerify(Customer customer) {
        return Objects.equals(this.numberDocument, customer.getNumberDocument())
                && isVerifiedDocumentProvided;
    }
}
