package com.team22.backend.Entity;
import lombok.*;
import javax.persistence.*;
import java.time.*;
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
    private @NonNull Long cusId;
    private String customerIDs;
    private String customerPassword;
    private String customerName;
    private String customerPhone;
    private String customerGender;
    private LocalDate customerBirthday;
    private String customerAddress;


}