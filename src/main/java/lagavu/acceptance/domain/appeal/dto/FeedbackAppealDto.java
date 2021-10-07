package lagavu.acceptance.domain.appeal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lagavu.acceptance.domain.appeal.entity.value_object.Currency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackAppealDto extends AppealDto {

    private Long id;

    private String type;

    private Integer sum;

    private Currency currency;

    @JsonProperty("output_sum")
    private Float outputSum;

    private Float rate;

    @JsonProperty("is_verified_code_word")
    private Boolean isVerifiedCodeWord;

    @JsonProperty("account_id")
    private Integer accountId;

    @JsonProperty("created_at")
    private String createdDate;
}
