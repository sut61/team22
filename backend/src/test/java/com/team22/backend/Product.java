package com.okta.developer.demo.Entity;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import java.util.*;
import java.util.Collection;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class Product {
    @Id
    @SequenceGenerator(name="product_seq",sequenceName="product_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="product_seq")
    @Column(name="Product_ID",unique = true, nullable = false)
    private @NonNull Long prodId;
    private  String productName;
    private  Integer productQuantity;
    private Integer productPrice;
    private  @NonNull String productID;
    private  String statusProduct;




}
