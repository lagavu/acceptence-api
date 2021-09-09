package lagavu.acceptance.domain.customer.mapper;

import lagavu.acceptance.domain.appeal.mapper.AppealMapper;
import lagavu.acceptance.domain.customer.dto.CustomerDto;
import lagavu.acceptance.domain.customer.entity.Customer;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    default CustomerDto toDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setGender(customer.getGender());
        customerDto.setAvailableAmountOfDollars(customer.getAvailableAmountOfDollars());
        customerDto.setPhoneNumber(customer.getPhoneNumber());
        customerDto.setAccountId(customer.getAccountId());
        customerDto.setNumberDocument(customer.getNumberDocument());
        customerDto.setCountAppeal(customer.getAppeals().size());
        customerDto.setAppealsDto(AppealMapper.INSTANCE.mapAppealsToAppealDtoList(customer.getAppeals()));

        return customerDto;
    }
}
