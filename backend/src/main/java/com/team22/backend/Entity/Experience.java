package com.team22.backend.Entity;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Experience")

public class Experience {
    @Id
    @SequenceGenerator(name = "experience_seq", sequenceName = "experience_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "experience_seq")
    @Column(name = "experienceId", unique = true, nullable = false)
    @NotNull
    private Long experienceId;

    @NotNull(message = "experienceIds not be null")
    private String experienceIds;

    @NotNull(message = "experienceName not be null")
    private String experienceName;

}