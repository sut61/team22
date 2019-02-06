package com.team22.backend.Entity;
import lombok.*;
import javax.persistence.*;
import java.time.*;
import javax.validation.constraints.*;
@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Customer")
public class Customer {
    @Id
    @SequenceGenerator(name="customer_seq",sequenceName="customer_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="customer_seq")
    @Column(name="Customer_ID",unique = true, nullable = false)

    @NotNull
    private Long cusId;

    @NotNull(message="customerIDs Null")
    @Column(unique = true)
    private String customerIDs;
    
    @NotNull(message="CustomerName Null")
    private String customerName;

    @NotNull(message="CustomerPassword Null")
    private String customerPassword;

    @NotNull(message="CustomerPhone Null")
    @Pattern(regexp = "([0]\\d{9})")
    @Size(min = 10, max = 10)
    private String customerPhone;

    private String customerGender;
    private LocalDate customerBirthday;
    private String customerAddress;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "Province_ID", insertable = true)
    private Province province;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Career.class)
    @JoinColumn(name = "Career_ID", insertable = true)
    private Career career;

}