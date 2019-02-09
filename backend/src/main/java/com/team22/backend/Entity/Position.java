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
@Table(name = "Position")

public class Position {
    @Id
    @SequenceGenerator(name = "position_seq", sequenceName = "position_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "position_seq")
    @Column(name = "positionId", unique = true, nullable = false)
    @NotNull
    private Long positionId;

    @NotNull(message = "positionIds not be null")
    private String positionIds;

    @NotNull(message = "positionName not be null")
    private String positionName;

}