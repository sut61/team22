package com.team22.backend.Entity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name=" CheckProduct") //ชื่อตาราง
public class CheckProduct {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="checkproduct_seq",sequenceName="checkproduct_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="checkproduct_seq")
    @Column(name="CheckProduct_ID",unique = true, nullable = false)
  
    private @NonNull Long checkId;
    private @NonNull String checkComment;
    private LocalDate checkDate;
    private @NonNull Integer checkLevel;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Product.class)
    @JoinColumn(name = "Product_ID", insertable = true)
    private  Product product;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Checking.class)
    @JoinColumn(name = "CheckingId", insertable = true)
    private Checking checking;
}