package lagavu.acceptance.domain.customer.repository;

import lagavu.acceptance.domain.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
}
