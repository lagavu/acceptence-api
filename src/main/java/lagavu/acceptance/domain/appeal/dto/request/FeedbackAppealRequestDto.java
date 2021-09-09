package lagavu.acceptance.domain.appeal.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackAppealRequestDto extends AppealRequestDto {

    @JsonProperty("is_verified_code_word")
    Boolean isVerifiedCodeWord;

    @JsonProperty("account_id")
    Integer accountId;

    public Boolean isVerifiedCodeWord() {
        return isVerifiedCodeWord;
    }

    public Integer getAccountId() {
        return accountId;
    }
}
