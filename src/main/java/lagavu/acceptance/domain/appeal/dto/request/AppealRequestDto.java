package lagavu.acceptance.domain.appeal.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lagavu.acceptance.domain.appeal.entity.value_object.AppealType;
import lagavu.acceptance.domain.appeal.entity.value_object.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
public class AppealRequestDto {

    @NonNull
    private String type;

    private Integer sum;

    private Currency currency;

    private Float rate;

    @JsonProperty("call_data")
    private CallAppealRequestDto callAppealRequestDto;

    @JsonProperty("feedback_data")
    private FeedbackAppealRequestDto feedbackAppealRequestDto;

    @JsonProperty("offline_data")
    private OfflineAppealRequestDto offlineAppealRequestDto;

    public AppealRequestDto(
            @NonNull String type,
            Integer sum,
            Currency currency,
            CallAppealRequestDto callAppealRequestDto,
            FeedbackAppealRequestDto feedbackAppealRequestDto,
            OfflineAppealRequestDto offlineAppealRequestDto
    ) {
        this.type = type;
        this.sum = sum;
        this.currency = currency;
        this.callAppealRequestDto = callAppealRequestDto;
        this.feedbackAppealRequestDto = feedbackAppealRequestDto;
        this.offlineAppealRequestDto = offlineAppealRequestDto;
    }

    public boolean isCallAppealType() {
        return type.equals(AppealType.CALL.getValue());
    }

    public boolean isFeedbackAppealType() {
        return type.equals(AppealType.FEEDBACK.getValue());
    }

    public boolean isOfflineAppealType() {
        return type.equals(AppealType.OFFLINE.getValue());
    }
}
