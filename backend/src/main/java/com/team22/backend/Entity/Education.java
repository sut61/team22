package com.team22.backend.Entity;

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
@Table(name = "Education")

public class Education {
    @Id
    @SequenceGenerator(name = "education_seq", sequenceName = "education_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "education_seq")
    @Column(name = "educationId", unique = true, nullable = false)
    @NotNull
    private Long educationId;

    @NotNull(message = "educationIds not be null")
    private String educationIds;

    @NotNull(message = "educationName not be null")
    private String educationName;

}