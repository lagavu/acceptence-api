package lagavu.acceptance.domain.customer.service;

import lagavu.acceptance.domain.appeal.dto.request.AppealRequestDto;
import lagavu.acceptance.domain.customer.entity.Customer;
import lagavu.acceptance.domain.customer.exception.CustomerNotFoundException;
import lagavu.acceptance.domain.customer.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

import static lagavu.acceptance.domain.customer.specification.CustomerSpecification.*;
import static org.springframework.data.jpa.domain.Specification.*;

@Service
public class CustomerService {

    private final ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findByAppealData(AppealRequestDto appealRequestDto) {
        if (appealRequestDto.isCallAppealType()) {
            String phone = appealRequestDto.getCallAppealRequestDto().getCallerPhoneNumber();
            return customerRepository
                    .findOne(where(hasPhoneNumber(phone)))
                    .orElseThrow(() -> new CustomerNotFoundException(
                            "Customer not found with number: " + phone));
        }

        if (appealRequestDto.isFeedbackAppealType()) {
            Integer accountId = appealRequestDto.getFeedbackAppealRequestDto().getAccountId();
            return customerRepository
                    .findOne(where(hasAccountId(accountId)))
                    .orElseThrow(() -> new CustomerNotFoundException(
                            "Customer not found with account id: " + accountId));
        }

        if (appealRequestDto.isOfflineAppealType()) {
            Integer numberDocument = appealRequestDto.getOfflineAppealRequestDto().getNumberDocument();
            return customerRepository
                    .findOne(where(hasNumberDocument(numberDocument)))
                    .orElseThrow(() -> new CustomerNotFoundException(
                            "Customer not found with number document: " + numberDocument));
        }

        throw new CustomerNotFoundException("Customer not found.");
    }
}
