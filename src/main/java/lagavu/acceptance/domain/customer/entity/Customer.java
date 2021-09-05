package lagavu.acceptance.domain.customer.entity;

import lagavu.acceptance.domain.customer.entity.value_object.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @Column
    String name;

    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    Gender gender;

    @NotNull
    @Column(columnDefinition = "integer default 100000")
    Integer availableAmountOfDollars;

    @NotNull
    @Column
    String phoneNumber;

    @NotNull
    @Column
    Integer accountId;

    @NotNull
    @Column
    Integer numberDocument;

    public Customer(
            String name,
            Gender gender,
            Integer availableAmountOfDollars,
            String phoneNumber,
            Integer accountId,
            Integer numberDocument
    ) {
        this.name = name;
        this.gender = gender;
        this.availableAmountOfDollars = availableAmountOfDollars;
        this.phoneNumber = phoneNumber;
        this.accountId = accountId;
        this.numberDocument = numberDocument;
    }
}
