package com.team22.backend.Entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.*;

import org.hibernate.annotations.*;
import java.util.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Staff")
public class Staff {
    @Id
    @SequenceGenerator(name = "staff_seq", sequenceName = "staff_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_seq")
    @Column(name = "Staff_ID", unique = true, nullable = false)

    @NotNull
    private Long staffId;

    @NotNull(message = "staffIds not be null")
    private String staffIds;

    @NotNull(message = "staffName not be null")
    @Column(unique = true)
    private String staffName;

    @NotNull(message = "staffPassword not be null")
    private String staffPassword;

    @NotNull(message = "staffPhone not be null")
    @Size(min = 12, max = 12)
    @Pattern(regexp = "([0][0-9]{2}-[0-9]{3}-[0-9]{4})")
    private String staffPhone;

    @NotNull(message = "staffSalary not be null")
    private Integer staffSalary;

    @NotNull(message = "staffStatus not be null")
    private String staffStatus;

    @NotNull(message = "staffJobtype not be null")
    private String staffJobtype;

    @NotNull(message = "staffGender not be null")
    private String staffGender;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Position.class)
    @JoinColumn(name = "positionId", insertable = true)
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Experience.class)
    @JoinColumn(name = "experienceId", insertable = true)
    private Experience experience;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Education.class)
    @JoinColumn(name = "educationId", insertable = true)
    private Education education;

}