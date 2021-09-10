package lagavu.acceptance.domain.appeal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lagavu.acceptance.domain.appeal.entity.value_object.Currency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfflineAppealDto extends AppealDto {

    private long id;

    private String type;

    private int sum;

    private Currency currency;

    @JsonProperty("output_sum")
    private float outputSum;

    private float rate;

    @JsonProperty("is_verified_document_provided")
    private boolean isVerifiedDocumentProvided;

    @JsonProperty("number_document")
    private int numberDocument;

    @JsonProperty("created_at")
    private String createdDate;
}
