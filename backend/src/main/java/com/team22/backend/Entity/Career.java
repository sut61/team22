package com.team22.backend.Entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Career")
public class Career {
    @Id
    @SequenceGenerator(name = "career_seq", sequenceName = "career_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "career_seq")
    @Column(name = "Career_ID", unique = true, nullable = false)
    @NotNull
    private Long careerId;
    @NotNull
    private String careerName;

}