package com.team22.backend.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;


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
    @SequenceGenerator(name = "lease_seq", sequenceName = "lease_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lease_seq")
    @Column(name = "Lease_ID", unique = true, nullable = false)
    private Long leaseId;

    private LocalDate dateStart;
    private LocalDate dateEnd;
    private @NonNull String status;
    private @NonNull String leaseStatus;

    @NotNull(message = "comment must not be null to be valid")
    @Pattern(regexp = "[ชุด].+")
    @Size(min=5,max=50)
    private    String commentRenting;

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

