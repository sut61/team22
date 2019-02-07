package com.team22.backend.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="BookingCancle")
public class BookingCancle {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="bookingcancle_seq",sequenceName="bookingcancle_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bookingcancle_seq")
    @Column(name="BookingCancleID",unique = true, nullable = false)

    @NotNull(message="bookingCancleDate must not be null to be valid")
    private Long bookingCancleID;

    // @NotNull(message="bookingCancleDate must not be null to be valid")
    private String bookingCancleIDs;

    @NotNull(message="bookingCancleDate must not be null to be valid")
    private Date bookingCancleDate; 

    @NotNull(message="bookingCancleStatus must not be null to be valid")
    private String bookingCancleStatus; 

    @NotNull(message="bookingCancleReason must not be null to be valid")
    @Pattern(regexp ="(เพราะ).+")
    @Size(min=3 ,max=20)
    private String bookingCancleReason; 

    @NotNull(message="bookingId must not be null to be valid")
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Booking.class)
    @JoinColumn(name = "bookingId", insertable = true)
    private Booking Booking;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TypeReason.class)
    @JoinColumn(name = "typeReasonID", insertable = true)
    private TypeReason typeReason;

}