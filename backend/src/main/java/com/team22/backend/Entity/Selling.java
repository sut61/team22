package com.team22.backend.Entity;
import javax.persistence.*;
import lombok.*;
import java.time.LocalDate;
import javax.validation.constraints.*;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Selling")
public class Selling {
    @Id
    @SequenceGenerator(name="selling_seq",sequenceName="selling_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="selling_seq")
    @Column(name="Selling_ID",unique = true, nullable = false)

    private Long sellingId;

    @NotNull
    private LocalDate sellingDate;

    @NotNull
    private  String status;

    @NotNull
    @Size(min=5,max=20)
    @Pattern(regexp = "(ร้าน).+")
    @Column(unique = true)
    private String commentSelling;


    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "customerId", insertable = true)
    private Customer customer;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Staff.class)
    @JoinColumn(name = "staffId", insertable = true)
    private Staff staff;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Product.class)
    @JoinColumn(name = "ProdId", insertable = true)
    private Product product;
    
}
