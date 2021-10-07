package lagavu.acceptance.domain.customer.entity;

import lagavu.acceptance.domain.appeal.entity.Appeal;
import lagavu.acceptance.domain.customer.entity.value_object.Gender;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Column(columnDefinition = "integer default 100000")
    private Integer availableAmountOfDollars;

    @NotNull
    @Column
    private String phoneNumber;

    @NotNull
    @Column
    private Integer accountId;

    @NotNull
    @Column
    private Integer numberDocument;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Appeal> appeals = new ArrayList<>();

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
