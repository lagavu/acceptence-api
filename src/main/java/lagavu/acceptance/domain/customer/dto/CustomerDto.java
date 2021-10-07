package lagavu.acceptance.domain.customer.dto;

import lagavu.acceptance.domain.appeal.dto.AppealDto;
import lagavu.acceptance.domain.customer.entity.value_object.Gender;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerDto {

    private Long id;

    private String name;

    private Gender gender;

    private Integer availableAmountOfDollars;

    private String phoneNumber;

    private Integer accountId;

    private Integer numberDocument;

    private Integer countAppeal;

    private List<AppealDto> appealsDto;
}
