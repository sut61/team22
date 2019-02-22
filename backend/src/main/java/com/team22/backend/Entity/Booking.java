package com.team22.backend.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import javax.validation.constraints.*;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Booking")
public class Booking {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="booking_seq",sequenceName="booking_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="booking_seq")
    @Column(name="BookingID",unique = true, nullable = false)
   
    @NotNull(message="bookingId must not be null to be valid")
    private Long bookingId;

    @NotNull(message="bookingDate must not be null to be valid")
    private  LocalDate bookingDate;

    @NotNull(message="status must not be null to be valid")
    private String status;

    @NotNull(message="statusBooking must not be null to be valid")
    private String statusBooking;

    @NotNull(message="category must not be null to be valid")
    @Pattern(regexp ="(งาน).+")
    @Size(min=5 ,max=30)
    @Column(unique = true)
    private String category;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Staff.class)
    @JoinColumn(name = "stID", insertable = true)
    private Staff staff;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "cusId", insertable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Style.class)
    @JoinColumn(name = "styleID", insertable = true)
    private Style style;


}