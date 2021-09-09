package lagavu.acceptance.domain.appeal.dto;

import lagavu.acceptance.domain.appeal.entity.valueObject.Currency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfflineAppealDto extends AppealDto {

    private long id;

    private String type;

    private int sum;

    private Currency currency;

    private float rate;

    private String createdDate;

    private boolean isVerifiedDocumentProvided;

    private int numberDocument;
}
