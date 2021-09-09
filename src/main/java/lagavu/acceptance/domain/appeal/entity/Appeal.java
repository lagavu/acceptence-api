package lagavu.acceptance.domain.appeal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lagavu.acceptance.domain.appeal.dto.request.AppealRequestDto;
import lagavu.acceptance.domain.appeal.entity.valueObject.Currency;
import lagavu.acceptance.domain.customer.entity.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appeals")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "appeal",
        discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@Getter
public abstract class Appeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    protected Integer sum;

    @Column
    protected Currency currency;

    @Column
    protected Float rate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    protected Customer customer;

    @CreatedDate
    protected Date createdDate;

    public Appeal(int sum, Currency currency, float rate, Customer customer) {
        this.sum = sum;
        this.currency = currency;
        this.rate = rate;
        this.customer = customer;
        this.createdDate = new Date();
    }

    public abstract boolean isVerify(Customer customer);

    public void update(AppealRequestDto appealRequestDto) {
        if (appealRequestDto.getSum() != null) {
            sum = appealRequestDto.getSum();
        }

        if (appealRequestDto.getCurrency() != null) {
            currency = appealRequestDto.getCurrency();
        }

        if (appealRequestDto.getRate() != null) {
            rate = appealRequestDto.getRate();
        }
    };

    @Transient
    public String getDecriminatorValue() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }
}
