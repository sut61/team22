package com.team22.backend.Entity;
import lombok.*;
import javax.persistence.*;
import java.time.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    private String customerIDs;
    private String customerPassword;
    private String customerName;
    private String customerPhone;
    private String customerGender;
    private LocalDate customerBirthday;
    private String customerAddress;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "Province_ID", insertable = true)
    private Province province;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "Career_ID", insertable = true)
    private Career career;

}