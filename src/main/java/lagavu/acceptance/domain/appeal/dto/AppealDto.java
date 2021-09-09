package lagavu.acceptance.domain.appeal.dto;

import lagavu.acceptance.domain.appeal.entity.Appeal;
import lagavu.acceptance.domain.appeal.entity.CallAppeal;
import lagavu.acceptance.domain.appeal.entity.FeedbackAppeal;
import lagavu.acceptance.domain.appeal.entity.OfflineAppeal;
import lagavu.acceptance.domain.appeal.mapper.IAppealMapper;

public class AppealDto {

    public static AppealDto create(Appeal appeal) {
        if (appeal instanceof CallAppeal) {
            CallAppeal callAppeal = (CallAppeal) appeal;
            return IAppealMapper.INSTANCE.toDto(callAppeal);
        }

        if (appeal instanceof FeedbackAppeal ) {
            FeedbackAppeal feedbackAppeal = (FeedbackAppeal) appeal;
            return IAppealMapper.INSTANCE.toDto(feedbackAppeal);
        }

        if (appeal instanceof OfflineAppeal) {
            OfflineAppeal offlineAppeal = (OfflineAppeal) appeal;
            return IAppealMapper.INSTANCE.toDto(offlineAppeal);
        }

        return null;
    }
}
