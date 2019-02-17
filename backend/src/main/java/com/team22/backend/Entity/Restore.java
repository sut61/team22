package com.team22.backend.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.Date;
import java.time.LocalDate;


@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Restore") //ชื่อตาราง
public class Restore {
    @Id
    @SequenceGenerator(name="Restore_seq",sequenceName="Restore_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Restore_seq")
    @Column(name="Restore_ID",unique = true, nullable = false)

    private  Long restoreId;

    @NotNull
    private Date dateRestore;

    @NotNull
    private String statusRestore;

    @NotNull
    @Size(min=5,max=20)
    @Pattern(regexp = "(ชุด).+")
    @Column(unique = true)
    private String commentRestore;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "leaseId")
    private Lease lease;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RestoreType.class)
    @JoinColumn(name = "restoreTypeId", insertable = true)
    private RestoreType restoreType;


}