package com.okta.developer.demo.Entity;
import javax.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Lease") //ชื่อตาราง
public class Lease {
    @Id
    @SequenceGenerator(name="lease_seq",sequenceName="lease_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="lease_seq")
    @Column(name="Lease_ID",unique = true, nullable = false)
    private @NonNull Long leaseID;
    private  LocalDate dateStart;
    private  LocalDate   dateEnd;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "customerId", insertable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Staff.class)
    @JoinColumn(name = "staffId", insertable = true)
    private Staff staff;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Product.class)
    @JoinColumn(name = "productId", insertable = true)
    private Product product;

}

