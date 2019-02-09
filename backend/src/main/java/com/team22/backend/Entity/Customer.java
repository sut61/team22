package com.team22.backend.Entity;

import lombok.*;
import javax.persistence.*;
import java.time.*;
import javax.validation.constraints.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @Column(name = "Customer_ID", unique = true)

    @NotNull(message = "cusId Null")
    private Long cusId;

    @NotNull(message = "customerIDs Null")
    @Column(unique = true)
    private String customerIDs;

    @NotNull(message = "CustomerName Null")
    private String customerName;

    @NotNull(message = "CustomerPassword Null")
    private String customerPassword;

    @NotNull(message = "CustomerPhone Null")
    @Pattern(regexp = "([0]\\d{9})")
    @Size(max = 10)
    private String customerPhone;

    @NotNull(message = "CustomerGender Null")
    private String customerGender;

    @NotNull(message = "CustomerBirthday Null")
    private LocalDate customerBirthday;

    @NotNull(message = "CustomerAddress Null")
    @Size(min = 2, max = 100)
    private String customerAddress;

    @NotNull(message = "Province Null")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "Province_ID", insertable = true)
    private Province province;

    @NotNull(message = "Career Null")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Career.class)
    @JoinColumn(name = "Career_ID", insertable = true)
    private Career career;

}