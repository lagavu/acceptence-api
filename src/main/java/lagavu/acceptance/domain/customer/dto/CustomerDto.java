package lagavu.acceptance.domain.customer.dto;

import lagavu.acceptance.domain.appeal.dto.AppealDto;
import lagavu.acceptance.domain.customer.entity.value_object.Gender;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerDto {

    private long id;

    private String name;

    private Gender gender;

    private int availableAmountOfDollars;

    private String phoneNumber;

    private int accountId;

    private int numberDocument;

    private int countAppeal;

    private List<AppealDto> appealsDto;
}
