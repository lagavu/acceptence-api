package lagavu.acceptance.data_fixture.customer;

import lagavu.acceptance.domain.customer.entity.Customer;
import lagavu.acceptance.domain.customer.entity.value_object.Gender;

public class CustomerFixture {

    public static String phoneNumber = "+799645412";

    public static Customer create() {
        return new Customer(
                "Ilon",
                Gender.MALE,
                1000000,
                phoneNumber,
                414,
                4124
        );
    }

    public static Customer createWithZeroAmountDollars() {
        return new Customer(
                "Ilon",
                Gender.MALE,
                0,
                phoneNumber,
                414,
                4124
        );
    }
}
