package com.team22.backend.Entity;

import javax.persistence.*;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;
@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="PayMent")
public class PayMent {
    @Id
    @SequenceGenerator(name="payMent_seq",sequenceName="payMent_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="payMent_seq")
    @Column(name="PayMent_ID",unique = true, nullable = false)
    @NotNull(message="PayMent_ID Null")    
    private Long pmId;

    @Column(unique = true)
    @NotNull(message="BillPayment Null")    
    private String billPayment;

    @JsonFormat(pattern = "yyyy-MM-dd : HH-mm" ,timezone = "GMT+7")
    @NotNull(message="DatePay Null")    
    private Date datePay;

    @NotNull(message="TypePay Null")      
    @Pattern(regexp = "[RSB]\\w{6}")
    @Size(min = 7 , max = 7)
    private String typePay;

    @NotNull(message="StatusPay Null")
    private String statusPay;

    @NotNull(message="Class Customer Null")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cusId")
    private Customer customer;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookingId")
    private Booking booking;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "leaseId")
    private Lease lease;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sellingId")
     private Selling selling;

     
}