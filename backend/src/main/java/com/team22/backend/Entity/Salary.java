package com.team22.backend.Entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import io.micrometer.core.annotation.Timed;

import java.time.LocalDate;
import java.util.*;
import java.util.Collection;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Salary")
public class Salary {
    @Id
    @SequenceGenerator(name = "salary_seq", sequenceName = "salary_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salary_seq")
    @Column(name = "Salary_ID", unique = true, nullable = false)

    @NotNull
    private Long salaryId;

    @NotNull(message = "SalaryIds not null")
    private String salaryIds;

    @NotNull(message = "salaryBankId not null")
    @Pattern(regexp = "[B]\\w{2,12}")
    @Size(min = 13, max = 13)
    private String salaryBankId;

    @JsonFormat(pattern = "yyyy-MM-dd || hh:mm a", timezone = "GMT+7")
    @NotNull(message = "salaryDate not null")
    private Date salaryDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Staff.class)
    @JoinColumn(name = "staffId", insertable = true)
    private Staff staff;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Payer.class)
    @JoinColumn(name = "payerId", insertable = true)
    private Payer payer;
}