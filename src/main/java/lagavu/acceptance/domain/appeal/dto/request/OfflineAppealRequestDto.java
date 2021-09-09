package lagavu.acceptance.domain.appeal.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfflineAppealRequestDto extends AppealRequestDto {

    @JsonProperty("is_verified_document_provided")
    Boolean isVerifiedDocumentProvided;

    @JsonProperty("number_document")
    Integer numberDocument;

    public Boolean isVerifiedDocumentProvided() {
        return isVerifiedDocumentProvided;
    }

    public Integer getNumberDocument() {
        return numberDocument;
    }
}
