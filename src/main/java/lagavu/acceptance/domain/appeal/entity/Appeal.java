package lagavu.acceptance.domain.appeal.entity;

import lagavu.acceptance.domain.appeal.entity.valueObject.Currency;
import lagavu.acceptance.domain.customer.entity.Customer;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class Appeal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column
    int sum;

    @Column
    Currency currency;

    @CreatedDate
    Date createdDate;

    public Appeal(int sum, Currency currency) {
        this.sum = sum;
        this.currency = currency;
        this.createdDate = new Date();
    }

    public abstract boolean isVerify(Customer customer);
}
