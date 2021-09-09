package lagavu.acceptance.domain.customer.specification;

import lagavu.acceptance.domain.customer.entity.Customer;
import lagavu.acceptance.domain.customer.entity.Customer_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CustomerSpecification {

    public static Specification<Customer> hasPhoneNumber(String phoneNumber){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Customer_.PHONE_NUMBER), phoneNumber);
        });
    }

    public static Specification<Customer> hasAccountId(Integer accountId){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Customer_.ACCOUNT_ID), accountId);
        });
    }

    public static Specification<Customer> hasNumberDocument(Integer numberDocument){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Customer_.NUMBER_DOCUMENT), numberDocument);
        });
    }
}
