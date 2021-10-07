package lagavu.acceptance.domain.appeal.mapper;

import lagavu.acceptance.domain.appeal.dto.AppealDto;
import lagavu.acceptance.domain.appeal.dto.CallAppealDto;
import lagavu.acceptance.domain.appeal.dto.FeedbackAppealDto;
import lagavu.acceptance.domain.appeal.dto.OfflineAppealDto;
import lagavu.acceptance.domain.appeal.entity.*;
import lagavu.acceptance.domain.appeal.entity.value_object.AppealType;
import lagavu.acceptance.component.cbr.cbr_exchange_rate_client.util.date_formatter.CbrDateFormatter;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IAppealMapper {

    IAppealMapper INSTANCE = Mappers.getMapper(IAppealMapper.class);

    default AppealDto toDto(Appeal appeal) {
        if(appeal instanceof CallAppeal) {
            CallAppealDto callAppealDto = toCallAppealDto((CallAppeal) appeal);
            callAppealDto.setType(AppealType.Values.CALL);
            callAppealDto.setOutputSum(appeal.getOutputSum());
            return callAppealDto;
        }

        if(appeal instanceof FeedbackAppeal) {
            FeedbackAppealDto feedbackAppealDto = toFeedbackAppealDto((FeedbackAppeal) appeal);
            feedbackAppealDto.setType(AppealType.Values.FEEDBACK);
            feedbackAppealDto.setOutputSum(appeal.getOutputSum());
            return feedbackAppealDto;
        }

        if(appeal instanceof OfflineAppeal) {
            OfflineAppealDto offlineAppealDto = toOfflineAppealDto((OfflineAppeal) appeal);
            offlineAppealDto.setType(AppealType.Values.OFFLINE);
            offlineAppealDto.setOutputSum(appeal.getOutputSum());
            return offlineAppealDto;
        }

        return null;
    }

    List<AppealDto> mapAppealsToAppealDtoList(List<Appeal> appeals);

    @Mapping(source = "verifiedSmsConfirmation", target = "isVerifiedSmsConfirmation")
    @Mapping(source = "createdDate", target = "createdDate", dateFormat = CbrDateFormatter.DATE_FORMAT_dd_MM_yyyy)
    CallAppealDto toCallAppealDto(CallAppeal callAppeal);

    @Mapping(source = "verifiedCodeWord", target = "isVerifiedCodeWord")
    @Mapping(source = "createdDate", target = "createdDate", dateFormat = CbrDateFormatter.DATE_FORMAT_dd_MM_yyyy)
    FeedbackAppealDto toFeedbackAppealDto(FeedbackAppeal feedbackAppeal);

    @Mapping(source = "verifiedDocumentProvided", target = "isVerifiedDocumentProvided")
    @Mapping(source = "createdDate", target = "createdDate", dateFormat = CbrDateFormatter.DATE_FORMAT_dd_MM_yyyy)
    OfflineAppealDto toOfflineAppealDto(OfflineAppeal offlineAppeal);
}
