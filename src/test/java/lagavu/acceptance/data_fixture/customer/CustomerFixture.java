package lagavu.acceptance.data_fixture.customer;

import lagavu.acceptance.domain.customer.entity.Customer;
import lagavu.acceptance.domain.customer.entity.value_object.Gender;

public class CustomerFixture {

    public static String phoneNumber = "+799645412";

    public static Customer create() {
        return new Customer(
                "Ilon",
                Gender.MALE,
                10000,
                phoneNumber,
                144,
                9877784
        );
    }
}
