package lagavu.acceptance.controller;

import lagavu.acceptance.domain.customer.dto.CustomerDto;
import lagavu.acceptance.domain.customer.entity.Customer;
import lagavu.acceptance.domain.customer.mapper.CustomerMapper;
import lagavu.acceptance.domain.customer.repository.ICustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private final ICustomerRepository customerRepository;

    public CustomerController(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable Long id) {
        Customer customer = customerRepository.getById(id);

        return new ResponseEntity<CustomerDto>(CustomerMapper.INSTANCE.toDto(customer), HttpStatus.OK);
    }
}
