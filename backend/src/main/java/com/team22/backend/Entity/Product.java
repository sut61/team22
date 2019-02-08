package com.team22.backend.Entity;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.*;
import javax.persistence.FetchType;


@Entity  //บอกว่าเป็น class entity class ที่เก็บขอมูล
@Data  // lombox จะสร้าง method getter setter ให้เอง
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Product") //ชื่อตาราง
public class Product {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="product_seq",sequenceName="product_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="product_seq")
    @Column(name="Products_ID",unique = true, nullable = false)

    private @NonNull Long prodId;
    
    @NotNull 
    @Pattern(regexp = "[P]\\d{1,5}")
    @Column(unique = true)
    private  String productIds;
    
    @NotNull 
    @Size(min=3 ,max=30)
    private  String productName;

    @NotNull 
    private  Integer productQuantity;

    @NotNull 
    private LocalDate  productDate;
    
    @NotNull 
    private  Integer productPrice ;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Type.class)
    @JoinColumn(name = "typeIds", insertable = true)
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
    @JoinColumn(name = "stateId", insertable = true)
    private Status status;

}