package lagavu.acceptance.domain.appeal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lagavu.acceptance.domain.appeal.entity.value_object.Currency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfflineAppealDto extends AppealDto {

    private Long id;

    private String type;

    private Integer sum;

    private Currency currency;

    @JsonProperty("output_sum")
    private Float outputSum;

    private Float rate;

    @JsonProperty("is_verified_document_provided")
    private Boolean isVerifiedDocumentProvided;

    @JsonProperty("number_document")
    private Integer numberDocument;

    @JsonProperty("created_at")
    private String createdDate;
}
