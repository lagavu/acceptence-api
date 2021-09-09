package lagavu.acceptance.domain.appeal.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CallAppealRequestDto extends AppealRequestDto {

    @JsonProperty("is_verified_sms_confirmation")
    Boolean isVerifiedSmsConfirmation;

    @JsonProperty("caller_phone_number")
    String callerPhoneNumber;

    public Boolean isVerifiedSmsConfirmation() {
        return isVerifiedSmsConfirmation;
    }

    public String getCallerPhoneNumber() {
        return callerPhoneNumber;
    }
}
