package lagavu.acceptance.domain.appeal.entity;

import lagavu.acceptance.domain.appeal.dto.request.AppealRequestDto;
import lagavu.acceptance.domain.appeal.entity.value_object.AppealType;
import lagavu.acceptance.domain.appeal.entity.value_object.Currency;
import lagavu.acceptance.domain.customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@DiscriminatorValue(value= AppealType.Values.OFFLINE)
@NoArgsConstructor
@AllArgsConstructor
public class OfflineAppeal extends Appeal {

    @NotNull
    @Column
    private Boolean isVerifiedDocumentProvided;

    @NotNull
    @Column
    private Integer numberDocument;

    public OfflineAppeal(
            int sum,
            Currency currency,
            float rate,
            Customer customer,
            Boolean isVerifiedDocumentProvided,
            int numberDocument
    ) {
        super(sum, currency, rate, customer);
        this.isVerifiedDocumentProvided = isVerifiedDocumentProvided;
        this.numberDocument = numberDocument;
    }

    public Boolean isVerifiedDocumentProvided() {
        return isVerifiedDocumentProvided;
    }

    public Integer getNumberDocument() {
        return numberDocument;
    }

    public void update(AppealRequestDto appealRequestDto) {
        super.update(appealRequestDto);

        if (appealRequestDto.getOfflineAppealRequestDto() != null) {
            if (appealRequestDto.getOfflineAppealRequestDto().isVerifiedDocumentProvided() != null) {
                isVerifiedDocumentProvided = appealRequestDto.getOfflineAppealRequestDto().isVerifiedDocumentProvided();
            }

            if (appealRequestDto.getOfflineAppealRequestDto().getNumberDocument() != null) {
                numberDocument = appealRequestDto.getOfflineAppealRequestDto().getNumberDocument();
            }
        }
    }

    @Override
    public boolean isVerify(Customer customer) {
        return Objects.equals(this.numberDocument, customer.getNumberDocument())
                && isVerifiedDocumentProvided;
    }
}
