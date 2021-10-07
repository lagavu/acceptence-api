package lagavu.acceptance.domain.appeal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lagavu.acceptance.domain.appeal.entity.value_object.Currency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CallAppealDto extends AppealDto {

    private Long id;

    private String type;

    private Integer sum;

    private Currency currency;

    @JsonProperty("output_sum")
    private Float outputSum;

    private Float rate;

    @JsonProperty("is_verified_sms_confirmation")
    private Boolean isVerifiedSmsConfirmation;

    @JsonProperty("caller_phone_number")
    private String callerPhoneNumber;

    @JsonProperty("created_at")
    private String createdDate;
}
